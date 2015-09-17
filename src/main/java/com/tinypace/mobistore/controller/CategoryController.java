package com.tinypace.mobistore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tinypace.mobistore.entity.SysUser;

@Controller
@RequestMapping("/category/")
public class CategoryController extends BaseController {
	
	@RequestMapping("list")
	public String list(HttpServletRequest request,SysUser user,@RequestParam(required=false)Integer pageNo,@RequestParam(required=false)Integer pageSize,Model model){

//		user.setCustomerType(UserType.INDIVIDUAL);
//		
//		page = userService.findAllByPage(user, (pageNo-1)*pageSize, pageSize);
//		model.addAttribute("page", page);
//		model.addAttribute("user", user);
		return "category/list";
	}
}
