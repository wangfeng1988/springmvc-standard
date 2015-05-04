package com.vincent.standard.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vincent.standard.entity.User;

@Controller
@RequestMapping("user")
public class UserController {
	private List<User> userList = Collections.synchronizedList(new ArrayList<User>());
	
	@RequestMapping("add")
	public String addUser(HttpServletRequest request, @Valid User user, BindingResult result, ModelMap model){
		userList.add(user);
		return listUser(request, model);
	}
	
	@RequestMapping("get")
	public String listUser(HttpServletRequest request, ModelMap model){
		model.put("userList", userList);
		return "user/allUsers";
	}
	
	
	// 添加日期的类型自动转换器 String-->Date
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// 注册自定义的属性编辑器
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		// 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
	}
}
