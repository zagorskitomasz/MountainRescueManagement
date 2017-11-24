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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	private String[] anyoneZone = {
			"/", 
			"/home", 
			"/rescuer/all", 
			"/operation/all", 
			"/login", 
			"/registration",
			"/register",
			"/logoutSuccess"};
	
	private String[] userZone = {
			"/rescuer/details", 
			"/rescuer/operations", 
			"/rescuer/add",
			"/rescuer/save",
			"/operation/details",
			"/operation/create",
			"/operation/save",
			"/loginSuccess"};
	
	private String[] adminZone = { 
			"/rescuer/update",
			"/rescuer/delete",
			"/rescuer/deleteConfirmation",
			"/operation/update",
			"/operation/delete",
			"/operation/deleteConfirmation"};
	
	
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
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		
		web
				.ignoring()
				.antMatchers("/resources/**");
	}
}
