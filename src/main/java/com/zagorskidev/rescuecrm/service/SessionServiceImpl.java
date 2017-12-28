package com.zagorskidev.rescuecrm.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.entity.security.User;
import com.zagorskidev.rescuecrm.service.security.UserService;

/**
 * Implements operations related to HTTP session.
 * 
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
		String userEmail = getUserEmail(principal);
		
		session.setAttribute("loggedUserName", userName);
		session.setAttribute("userEmail", userEmail);
	}

	private String getUserEmail(Principal principal) {
		
		return principal != null ? principal.getName() : "";
	}

	@Override
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

		if (user == null)
			return null;

		userName = user.getName();

		return userName;
	}

	@Override
	public void addContextUrl(HttpServletRequest request) {

		try {
			URI requestUri = new URI(request.getRequestURL().toString());

			URI contextUri = new URI(requestUri.getScheme(), requestUri.getAuthority(), request.getContextPath(), null,
					null);
			
			session.setAttribute("contextUrl", contextUri.toString());

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
}
