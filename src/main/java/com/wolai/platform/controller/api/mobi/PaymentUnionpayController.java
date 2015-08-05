package com.wolai.platform.controller.api.mobi;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wolai.platform.annotation.AuthPassport;
import com.wolai.platform.constant.Constant;
import com.wolai.platform.constant.Constant.RespCode;
import com.wolai.platform.controller.api.BaseController;
import com.wolai.platform.entity.Bill;
import com.wolai.platform.entity.ParkingRecord;
import com.wolai.platform.service.AssetService;
import com.wolai.platform.service.BillService;
import com.wolai.platform.service.ParkingLotService;
import com.wolai.platform.service.ParkingService;
import com.wolai.platform.service.PaymentService;
import com.wolai.platform.service.PaymentUnionpayService;
import com.wolai.platform.service.UserService;
import com.wolai.platform.util.BeanUtilEx;
import com.wolai.platform.util.FileUtils;
import com.wolai.platform.vo.AlipayVo;
import com.wolai.platform.vo.BillVo;
import com.wolai.platform.vo.ParkingLotVo;
import com.wolai.platform.vo.ParkingVo;
import com.wolai.platform.vo.UnionpayVo;

@Controller
@RequestMapping(Constant.API_MOBI + "payment/unionpay/")
public class PaymentUnionpayController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	PaymentUnionpayService paymentUnionpayService;
	
	@Autowired
	ParkingService parkingService;
	
	@Autowired
	ParkingLotService parkingLotService;
	
	@Autowired
	BillService billService;

	@RequestMapping(value="prepare")
	@ResponseBody
	public Map<String,Object> prepare(HttpServletRequest request, @RequestBody Map<String, String> json){
		Map<String,Object> ret = new HashMap<String, Object>();
		
		String parkingId = json.get("parkingId");
		String couponId = json.get("couponId");
		String clientType = json.get("clientType");
		
		if (StringUtils.isEmpty(parkingId)) {
			ret.put("code", RespCode.INTERFACE_FAIL.Code());
			ret.put("msg", "parameters error");
			return ret;
		}
		
		Object obj = parkingService.get(ParkingRecord.class, parkingId);
		if (obj == null) {
			ret.put("code", RespCode.INTERFACE_FAIL.Code());
			ret.put("msg", "not found");
			return ret;
		}
		
		ParkingRecord park = (ParkingRecord) obj;
		Bill bill = paymentService.createBillIfNeededPers(park, couponId);
		String wolaiTradeNo = bill.getId();
		BigDecimal money = bill.getMoney();
		UnionpayVo payVo = new UnionpayVo();
		payVo.setWolaiTradeNo(wolaiTradeNo);
		payVo.setAmount(money);
		
		Map<String, String> resMap = paymentUnionpayService.prepareTrans(money.multiply(new BigDecimal(100)).intValue());
		payVo.setPayTradeNo("");
		
		ret.put("code", RespCode.SUCCESS.Code());
		ret.put("data", payVo);
		return ret;
	}
	
	// 银联消费回调
	@AuthPassport(validate=false)
	@RequestMapping(value="consumeCallback")
	@ResponseBody
	public String unionpayConsumeCallback(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap(); 
		
		return "";
	}
	
	// 银联代扣回调
	@AuthPassport(validate=false)
	@RequestMapping(value="delegateCallback")
	@ResponseBody
	public String unionpayDelegateCallback(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap(); 
		
		return "";
	}
	
	@AuthPassport(validate=false)
	@RequestMapping(value="confirmPostPay")
	@ResponseBody
	public Map<String,Object> confirmPostPay(HttpServletRequest request, @RequestBody Map<String, String> json){
		Map<String,Object> ret = new HashMap<String, Object>();
		
		String parkingId = json.get("parkingId");
		String couponId = json.get("couponId");
		String clientType = json.get("clientType");
		
		if (StringUtils.isEmpty(parkingId)) {
			ret.put("code", RespCode.INTERFACE_FAIL.Code());
			ret.put("msg", "parameters error");
			return ret;
		}
		
		ret.put("code", RespCode.SUCCESS.Code());
		ret.put("data", "");
		return ret;
	}
	
}
