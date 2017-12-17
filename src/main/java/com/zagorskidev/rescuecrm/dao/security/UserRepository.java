package com.zagorskidev.rescuecrm.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zagorskidev.rescuecrm.entity.security.User;

/**
 * Grants access to users stored in database.
 * @author tomek
 *
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
