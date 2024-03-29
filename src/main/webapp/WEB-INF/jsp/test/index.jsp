<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default1" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>喔来停车系统测试</title>
	
	 <style type="text/css">
		.controls select {
			width: 400px;
		}
		.controls input {
			width: 395px;
		}
	</style>
</head>

<body>
	<div style="padding: 20px;">
		<H3>接口绑定</H3>
		<form action="#" class="form-inline">
			<div class="control-group">
				<!-- <label class="control-label"></label> -->
				<div class="controls" >
					<input id="token" value="b1d4163f9829453d9aeed855b02b4cbc" /><br/>
					<input id="url" value="${webPath}" /><br/>
					<input id="thirdPartPath" value="${thirdPartPath}" />
					<button id="boundToken" type="submit" class="btn">绑定</button>
				</div>
			</div>
		
		<H3>用户</H3>

			<div class="control-group">
				<!-- <label class="control-label"></label> -->
				<div class="controls">
					<select id="user">
						<c:forEach items="${users}" var="usr"> 
							<option value ="${usr.mobile}-${usr.password}" 
								<c:if test="${user == usr.id}">selected="selected"</c:if>
								>${usr.name} :   
								<c:if test="${usr.payType == 'PERPAID'}">现付费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
								<c:if test="${usr.payType == 'CONFIRM_POSTPAID'}">确认后付费</c:if>
								<c:if test="${usr.payType == 'POSTPAID'}">后付费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
								 : ${usr.mobile} / ${usr.password} 
								</option>
						</c:forEach>
					</select>
					<button id="login" type="submit" class="btn">模拟登录</button>&nbsp;&nbsp;&nbsp;
					<span id="userToken" ></span>
				</div>
			</div>
		<H3>临时车</H3>
			<div class="control-group">
				<!-- <label class="control-label"></label> -->
				<div class="controls" >
					<input id="carToInTemp" />
					<button id="tempEnter" type="submit" class="btn">模拟入库</button>
				</div>
			</div>
		
		<H3>场外车</H3>

			<div class="control-group">
				<!-- <label class="control-label"></label> -->
				<div class="controls" >
					<select id="carToIn">
						<c:forEach items="${carsOutList}" var="car">
							<option value ="${car.carNo}">${car.carNo}</option>
						</c:forEach>
					</select>
					<button id="enter" type="submit" class="btn">模拟入库</button>
				</div>
			</div>

		
		<H3>场内车</H3>

			<div class="control-group">
				<!-- <label class="control-label"></label> -->
				<div class="controls" >
					<select id="carToOut">
						<c:forEach items="${carsInList}" var="car">
							<option value ="${car.carNo}">${car.carNo}
								<c:if test="${car.isPaid == true}"> - 已支付</c:if>
							</option>
						</c:forEach>
					</select>
					<button id="payCheck" type="submit" class="btn">支付检查</button>
					<button id="leaveConfirm" type="submit" class="btn leave">确认出库</button>
					<button id="leaveCancel" type="submit" class="btn leave">放弃出库</button>
				</div>
			</div>
	</form>
	</div>
	
	<script>
	var userToken = "";
	
	$(document).ready(function(){
		$("#boundToken").click(function(){
			var sysToken = $("#token").val();
			var url = $("#url").val();
			var thirdPartPath = $("#thirdPartPath").val();
			var data1 = {
				"url":url,
				"token":sysToken,
				"thirdPartPath": thirdPartPath
			};
			$.ajax({
	            type: "POST",
	            url: "/wolai-web/test/bound",
	            data: JSON.stringify(data1),
				contentType: "application/json",
	            dataType: "json",
	            success: function(json){
	            	console.log(json);
					if (json.code == '-1') {
						alert("错误 " + json.msg);
					} else {
						$("#url").val(url);
						console.log("绑定成功");
					}
	           }
	        });
			return false;
		});
		
		$("#login").click(function(){
			
			var v = $("#user").val();
			var phone = v.split('-')[0];
			var password = v.split('-')[1];
			
 			var data2 = {
            	"phone": phone, 
				"password":password
			};
			$.ajax({
	             type: "POST",
	             url: "/wolai-web/test/login",
	             data: JSON.stringify(data2),
				 contentType: "application/json",
	             dataType: "json",
	             success: function(json){
					if (json.code == '-1') {
						alert("错误 " + json.msg);
					} else {
						userToken = json.token;  
						refresh(); 
					}
	            }
	         });
			
			return false;
		});
		
		$("#tempEnter").click(function(){
			var carToIn = $("#carToInTemp").val();
			
			if (!carToIn) {
				alert("请填写临时车牌号！");
				return false;
			}
			
			var now = new Date().getTime();
			var data3 = {"carNo": carToIn};
			
			$.ajax({
	             type: "POST",
	             url: "/wolai-web/test/enter",
	             data: JSON.stringify(data3),
				 contentType: "application/json",
	             dataType: "json",
	             success: function(json){
					if (json.code == '-1') {
						alert("错误 " + json.msg);
					} else {
						refresh();    	
					}
	            }
	         });
			return false;
		});
		
		$("#enter").click(function(){
			var carToIn = $("#carToIn").val();
			
			var now = new Date().getTime();
			var data3 = {"carNo": carToIn};
			
			$.ajax({
	             type: "POST",
	             url: "/wolai-web/test/enter",
	             data: JSON.stringify(data3),
				 contentType: "application/json",
	             dataType: "json",
	             success: function(json){
	            	if (json.code < 0 || json.code == 2) {
	            		alert("错误 " + json.msg);
					} else {
						refresh();
					}
	            	
	            }
	         });
			return false;
		});
		
		$("#payCheck").click(function(){
			var carToOut = $("#carToOut").val();
			
			var now = new Date().getTime();
			var data3 = {"carNo": carToOut};
			
			$.ajax({
	             type: "POST",
	             url: "/wolai-web/test/payCheck",
	             data: JSON.stringify(data3),
				 contentType: "application/json",
	             dataType: "json",
	             success: function(json){
	            	if (json.code == '-1') {
	            		alert("错误 " + json.msg);
					} else {
						refresh(); 
					}
	            	
	            }
	         });
			return false;
		});
		
		$(".leave").click(function(){
			var action;
			if ( $(this).attr('id') == 'leaveConfirm') {
				action = 'sure';
			} else {
				action = 'cancel';
			}
			
			var carToOut = $("#carToOut").val();
			
			var now = new Date().getTime();
			var data3 = {"carNo": carToOut, "action": action};
			
			$.ajax({
	             type: "POST",
	             url: "/wolai-web/test/leave",
	             data: JSON.stringify(data3),
				 contentType: "application/json",
	             dataType: "json",
	             success: function(json){
	            	if (json.code == '-1') {
	            		alert("错误 " + json.msg);
					} else {
						refresh(); 
					}
	            	
	            }
	         });
			return false;
		});
		
		var refresh = function() {
			if (!userToken) {
				userToken = getParam("token");
			}
			
			$('#carToIn option').remove();
			$('#carToOut option').remove();
			
			var url = window.location.href.split("?")[0] + "?r=" + new Date().getTime() + "&token=" + userToken;
			window.location.href = url;
		}
		var getParam = function(name) {
			var reg = new RegExp("(^|&|\\?)" + name + "=([^&]*)(&|$)", "i"); 
			var r = window.location.href.substr(1).match(reg);
			if (r != null) {
				return unescape(r[2]); 
			}
			return null; 
		}
		
		var token = getParam("token");
		$('#userToken').text(token);
	});
  </script>

</body>
</html>