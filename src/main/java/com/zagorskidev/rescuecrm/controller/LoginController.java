package com.zagorskidev.rescuecrm.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zagorskidev.rescuecrm.entity.security.User;
import com.zagorskidev.rescuecrm.service.security.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginForm() {
		
		return "login";
	}
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);
		
		return "security/registration-form";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		User userExists = userService.findUserByEmail(user.getEmail());
		
		if(userExists!=null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already user registered with this email");
		}
		if(bindingResult.hasErrors()) {
			return "security/registration-form";
		}
		else {
			userService.saveUser(user);
			return "security/registration-success";
		}
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Principal principal, Model model) {
	
		String userName;
		
		if(principal!=null) {
			User user = userService.findUserByEmail(principal.getName());
			userName = user.getName();
		}
		else
			userName = "";
		
		model.addAttribute("userName", userName);
		return "access-denied";
	}
	
	@GetMapping("/logoutSuccess")
	public String showLogoutPage(HttpServletRequest request, Principal principal) {
		
		addUserToSession(request, principal);
		
		return "/security/logout";
	}
	
	@GetMapping("/loginSuccess")
	public String showLoginPage(HttpServletRequest request, Principal principal) {
		
		addUserToSession(request, principal);
		
		return "/security/login-success";
	}
	
	private HttpSession addUserToSession(HttpServletRequest request, Principal principal) {
		
		HttpSession session = request.getSession();
		String userName;
		
		if(principal!=null) {
			User user = userService.findUserByEmail(principal.getName());
			userName = user.getName();
		}
		else
			userName = "anonymous";
		
		session.setAttribute("loggedUserName", userName);
		
		return session;
	}
}
