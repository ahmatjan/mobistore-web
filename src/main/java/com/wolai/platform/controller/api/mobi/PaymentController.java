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
import com.wolai.platform.service.UserService;
import com.wolai.platform.util.BeanUtilEx;
import com.wolai.platform.util.FileUtils;
import com.wolai.platform.vo.AlipayVo;
import com.wolai.platform.vo.BillVo;
import com.wolai.platform.vo.ParkingLotVo;
import com.wolai.platform.vo.ParkingVo;

@Controller
@RequestMapping(Constant.API_MOBI + "payment/")
public class PaymentController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	PaymentService paymentService;
	
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
		AlipayVo alipayVo = new AlipayVo();
		alipayVo.setWolaiTradeNo(bill.getId());
		alipayVo.setAmount(bill.getMoney());
		
		ret.put("code", RespCode.SUCCESS.Code());
		ret.put("data", alipayVo);
		return ret;
	}

	@AuthPassport(validate=false)
	@RequestMapping(value="alipayCallback")
	@ResponseBody
	public String alipayCallback(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap(); 
		
		String[] wolaiTradeNo = params.get("out_trade_no"); // 订单交易号
		String[] alipayTradeNo = params.get("trade_no"); // 支付宝交易号
		String[] alipayTradeStatus = params.get("trade_status"); // 支付宝交易状态
		
		if (wolaiTradeNo.length == 0 || alipayTradeNo.length == 0 || alipayTradeStatus.length == 0) {
			log.error("支付宝异步接口参数错误");
			return "error";
		}
		
		if (!"TRADE_SUCCESS".equals(alipayTradeStatus[0]) && !"TRADE_FINISHED".equals(alipayTradeStatus[0])) {
			log.error("收到支付宝异步接口'" + alipayTradeStatus[0] + "'类型的请求，不处理！");
			return "success";
		}
		
		Object obj = billService.get(Bill.class, wolaiTradeNo[0]);
		if (obj == null) {
			log.error("未找到out_trade_no对应的Bill对象");
			return "error";
		}
		
		Bill bill = (Bill) obj;
		if (alipayTradeNo[0].equals(bill.getTradeNo())) {
			log.error("trade_no参数跟Bill对象的TradeNo不匹配");
			return "error";
		}
		
		paymentService.successPers(bill, alipayTradeNo[0], alipayTradeStatus[0]);
		log.info("支付宝交易返回：" + alipayTradeNo[0] + "-" + alipayTradeStatus[0]);
		
		return "success";
	}
}
