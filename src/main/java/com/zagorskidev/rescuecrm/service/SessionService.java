package com.zagorskidev.rescuecrm.service;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

/**
 * Grants services related to HTTP session bean.
 * @author tomek
 *
 */
public interface SessionService {

	public void addUserToSession(Principal principal);
	public String getUserName(Principal principal);
	public void addContextUrl(HttpServletRequest request);
}
