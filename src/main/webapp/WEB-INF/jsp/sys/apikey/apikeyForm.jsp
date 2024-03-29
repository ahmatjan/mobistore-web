<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
	<title>外部接入管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/apikey/">API Key列表</a></li>
		<li class="active"><a href="${ctx}/apikey/form?id=${license.id}">API Key${not empty license.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="apikey" action="${ctx}/apikey/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">停车场：</label>
			<div class="controls">
				<form:select path="parkingLotId" items="${parkinglots}" itemLabel="name" itemValue="id" class="input-xlarge required"></form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问地址：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="255" class="input-xlarge required url"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">端口号：</label>
			<div class="controls">
				<form:input path="port" htmlEscape="false" maxlength="255" class="input-xlarge required digits" range="0,65535"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">根目录：</label>
			<div class="controls">
				<form:input path="rootPath" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>