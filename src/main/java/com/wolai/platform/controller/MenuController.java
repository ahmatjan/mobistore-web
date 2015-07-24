package com.wolai.platform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wolai.platform.bean.LoginInfo;
import com.wolai.platform.constant.Constant;
import com.wolai.platform.entity.SysRole;

@Controller
@RequestMapping("${adminPath}/menu")
public class MenuController extends BaseController {

	@RequestMapping("/menu")
	public  String tree(HttpServletRequest request,Model model){
		LoginInfo info= getLoginInfoSession(request);
		List<SysRole> roles =info.getRoles();
		if(roles!=null && roles.size()>0){
			for(SysRole role : roles){
				if(Constant.ROLE_ADMIN.equals(role.getCode())){
					model.addAttribute("admin", true);
				}
			}
		}
		return "/menu/menu";
	}
}
