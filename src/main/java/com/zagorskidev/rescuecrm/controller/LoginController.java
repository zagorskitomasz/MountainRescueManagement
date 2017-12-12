package com.zagorskidev.rescuecrm.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zagorskidev.rescuecrm.entity.security.User;
import com.zagorskidev.rescuecrm.service.SessionService;
import com.zagorskidev.rescuecrm.service.security.UserService;

/**
 * Handles requests related to app security
 * @author tomek
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private SessionService sessionService;

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

	/**
	 * Validates registration form
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already user registered with this email");
		}
		if (bindingResult.hasErrors()) {
			return "security/registration-form";
		} else {
			userService.saveUser(user);
			return "security/registration-success";
		}
	}

	@GetMapping("/accessDenied")
	public String accessDenied(Principal principal, Model model) {

		String userName = "";

		model.addAttribute("userName", userName);
		return "/security/access-denied";
	}

	@GetMapping("/logoutSuccess")
	public String showLogoutPage(Principal principal) {

		addNameToSession(principal);

		return "/security/logout";
	}

	@GetMapping("/loginSuccess")
	public String showLoginPage(Principal principal) {

		addNameToSession(principal);

		return "/security/login-success";
	}

	private void addNameToSession(Principal principal) {
		
		sessionService.addUserToSession(principal);
	}
}
