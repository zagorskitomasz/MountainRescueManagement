package com.zagorskidev.rescuecrm.service;

import java.security.Principal;

public interface SessionService {

	public void addUserToSession(Principal principal);
	public String getUserName(Principal principal);
}
