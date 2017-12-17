package com.zagorskidev.rescuecrm.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zagorskidev.rescuecrm.entity.security.Role;

/**
 * Grants access to roles stored in database.
 * @author tomek
 *
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByRole(String role);
}
