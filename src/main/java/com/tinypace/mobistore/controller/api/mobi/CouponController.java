package com.tinypace.mobistore.controller.api.mobi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinypace.mobistore.annotation.AuthPassport;
import com.tinypace.mobistore.bean.Page;
import com.tinypace.mobistore.constant.Constant;
import com.tinypace.mobistore.constant.Constant.RespCode;
import com.tinypace.mobistore.controller.api.BaseController;
import com.tinypace.mobistore.entity.Coupon;
import com.tinypace.mobistore.entity.Coupon.CouponType;
import com.tinypace.mobistore.entity.SysUser;
import com.tinypace.mobistore.service.CouponService;
import com.tinypace.mobistore.service.UserService;
import com.tinypace.mobistore.util.BeanUtilEx;
import com.tinypace.mobistore.vo.CouponVo;

@Controller
@RequestMapping(Constant.API_MOBI + "coupon/")
public class CouponController extends BaseController {
	@Autowired
	UserService userService;
	
	@Autowired
	CouponService couponService;

	@RequestMapping(value="listMoney")
	@ResponseBody
	public Map<String, Object> listMoney(HttpServletRequest request, @RequestParam String token, @RequestBody Map<String, String> json){
		if (pagingParamError(json)) {
			return pagingParamError();
		}
		int startIndex = Integer.valueOf(json.get("startIndex"));
		int pageSize = Integer.valueOf(json.get("pageSize"));
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		SysUser uesr = userService.getUserByToken(token);
		String userId = uesr.getId();

		Page couponPage = couponService.listMoneyByUser(userId, startIndex, pageSize);
		List<CouponVo> couponVoList = new ArrayList<CouponVo>();
		
		for (Object obj : couponPage.getItems()) {
			Coupon po = (Coupon) obj;
			CouponVo vo = new CouponVo();
			BeanUtilEx.copyProperties(vo, po);
			couponVoList.add(vo);
		}
		
		ret.put("code", RespCode.SUCCESS.Code());
		ret.put("data", couponVoList);
		ret.put("totalPages", couponPage.getTotalPages());
		return ret;
	}
	
	@AuthPassport(validate=true)
	@RequestMapping(value="listTime")
	@ResponseBody
	public Map<String, Object> listTime(HttpServletRequest request, @RequestParam String token, @RequestBody Map<String, String> json){
		if (pagingParamError(json)) {
			return pagingParamError();
		}
		int startIndex = Integer.valueOf(json.get("startIndex"));
		int pageSize = Integer.valueOf(json.get("pageSize"));
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		SysUser uesr = userService.getUserByToken(token);
		String userId = uesr.getId();

		Page couponPage = couponService.listTimeByUser(userId, startIndex, pageSize);
		List<CouponVo> couponVoList = new ArrayList<CouponVo>();
		
		for (Object obj : couponPage.getItems()) {
			Coupon po = (Coupon) obj;
			CouponVo vo = new CouponVo();
			BeanUtilEx.copyProperties(vo, po);
			couponVoList.add(vo);
		}
		
		ret.put("code", RespCode.SUCCESS.Code());
		ret.put("data", couponVoList);
		ret.put("totalPages", couponPage.getTotalPages());
		return ret;
	}


	@AuthPassport(validate=true)
	@RequestMapping(value="detail")
	@ResponseBody
	public Object detail(HttpServletRequest request, @RequestBody Map<String, String> json, @RequestParam String token){
		Map<String, Object> ret = new HashMap<String, Object>();
		
		String id = json.get("id");
		if (StringUtils.isEmpty(id)) {
			ret.put("code", RespCode.INTERFACE_FAIL.Code());
			ret.put("msg", "parameters error");
			return ret;
		}
		
		Object obj = couponService.get(Coupon.class, id);
		if (obj == null) {
			ret.put("code", RespCode.INTERFACE_FAIL.Code());
			ret.put("msg", "not found");
			return ret;
		}
		Coupon coupon = (Coupon) obj;
		CouponVo vo = new CouponVo();
		BeanUtilEx.copyProperties(vo, coupon);
		return vo;
	}

//	@AuthPassport(validate=true)
//	@RequestMapping(value="use")
//	@ResponseBody
//	public Object use(HttpServletRequest request, @RequestBody Map<String, String> json, @RequestParam String token){
//		Map<String, Object> ret = new HashMap<String, Object>();
//		
//		SysUser user = userService.getUserByToken(token);
//		String userId = user.getId();
//		String couponId = json.get("id");
//		if (StringUtils.isEmpty(couponId)) {
//			ret.put("code", RespCode.INTERFACE_FAIL.Code());
//			ret.put("msg", "parameters error");
//			return ret;
//		}
//		
//		ret = couponService.usePers(couponId, userId);
//
//		return ret;
//	}
}
