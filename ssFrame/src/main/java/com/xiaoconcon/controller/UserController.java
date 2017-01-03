package com.xiaoconcon.controller;

import java.util.HashMap;
import java.util.Map;

import com.xiaoconcon.annotation.Action;
import com.xiaoconcon.annotation.Controller;
import com.xiaoconcon.http.Data;
import com.xiaoconcon.http.Param;
import com.xiaoconcon.http.View;

@Controller
public class UserController {
    
	@Action(value="get:/user")
	public Data hello(Param param){
		String hello = "hello my samll smart frame";
		Data data =new Data(hello);
		return data;
	}
	@Action(value="get:/hello")
	public View getjsp(Param param){
		View view =new View("hello.jsp");
		view.addModel("test", "test");
		return view;
	}
}
