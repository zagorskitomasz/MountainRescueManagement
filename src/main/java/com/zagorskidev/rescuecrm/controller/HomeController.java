package com.zagorskidev.rescuecrm.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zagorskidev.rescuecrm.entity.security.User;
import com.zagorskidev.rescuecrm.service.security.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping({"/", "/home"})
	public String showHomePage(HttpServletRequest request, Principal principal) {
		
		HttpSession session = request.getSession();
		String userName;
		
		if(principal!=null) {
			User user = userService.findUserByEmail(principal.getName());
			userName = user.getName();
		}
		else
			userName = "anonymous";
		
		session.setAttribute("loggedUserName", userName);
		
		return "home";
	}
}
