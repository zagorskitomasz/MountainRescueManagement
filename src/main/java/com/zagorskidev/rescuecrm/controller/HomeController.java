package com.zagorskidev.rescuecrm.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zagorskidev.rescuecrm.service.SessionService;

/**
 * Handles requests to home page.
 * @author tomek
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	private SessionService sessionService;

	@RequestMapping({ "/", "/home" })
	public String showHomePage(HttpServletRequest request, Principal principal) {

		sessionService.addUserToSession(principal);
		sessionService.addContextUrl(request);

		return "home";
	}
}
