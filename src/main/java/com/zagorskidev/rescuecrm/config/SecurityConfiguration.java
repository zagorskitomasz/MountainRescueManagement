package com.zagorskidev.rescuecrm.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration of Spring Security
 * @author tomek
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	private String[] anyoneZone = {
			"/", 
			"/home", 
			"/rescuer/all", 
			"/rescuer/available", 
			"/rescuer/busy", 
			"/operation/all", 
			"/operation/running", 
			"/operation/finished", 
			"/login", 
			"/registration",
			"/register",
			"/logoutSuccess"};
	
	private String[] userZone = {
			"/rescuer/details", 
			"/rescuer/operations", 
			"/rescuer/add",
			"/rescuer/save",
			"/rescuer/update",
			"/rescuer/delete",
			"/rescuer/deleteConfirmation",
			"/operation/details",
			"/operation/create",
			"/operation/save",
			"/operation/update",
			"/operation/delete",
			"/operation/deleteConfirmation",
			"/operation/finish",
			"/operation/addRescuers",
			"/loginSuccess"};
	
	private String[] adminZone = {};
	
	/**
	 * Configuration of auth manager
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception{
		
		auth
				.jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}
	
	/**
	 * Configuring Spring auto login/logout and permissions to URLs
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http
				.authorizeRequests()
				.antMatchers(anyoneZone)
				.permitAll()
				.antMatchers(userZone)
				.hasAuthority("USER")
				.antMatchers(adminZone)
				.hasAuthority("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.csrf()
				.disable()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/loginSuccess", true)
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logoutSuccess")
				.and()
				.exceptionHandling()
				.accessDeniedPage("/accessDenied");
	}
	
	/**
	 * Static resources will be ignored
	 */
	@Override
	public void configure(WebSecurity web) throws Exception{
		
		web
				.ignoring()
				.antMatchers("/resources/**");
	}
}
