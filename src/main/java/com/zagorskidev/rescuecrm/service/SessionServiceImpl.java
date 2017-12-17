package com.zagorskidev.rescuecrm.service;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.entity.security.User;
import com.zagorskidev.rescuecrm.service.security.UserService;

/**
 * Implements operations related to HTTP session.
 * @author tomek
 *
 */
@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void addUserToSession(Principal principal) {

		String userName = getUserName(principal);
		session.setAttribute("loggedUserName", userName);
	}

	public String getUserName(Principal principal) {
		
		String userName;

		if (principal != null) {
			userName = extractUserName(principal);
		} else
			userName = "anonymous";

		return userName;
	}

	private String extractUserName(Principal principal) {

		String userName;
		User user = userService.findUserByEmail(principal.getName());
		userName = user.getName();

		return userName;
	}
}
