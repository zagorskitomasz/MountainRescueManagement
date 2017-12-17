package com.zagorskidev.rescuecrm.service.security;

import com.zagorskidev.rescuecrm.entity.security.User;

/**
 * Grants operations related to application user bean.
 * @author tomek
 *
 */
public interface UserService {

	public User findUserByEmail(String email);
	public void saveUser(User user);
}
