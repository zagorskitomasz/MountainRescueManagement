package com.zagorskidev.rescuecrm;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.zagorskidev.rescuecrm.service.SessionService;
import com.zagorskidev.rescuecrm.service.SessionServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionServiceTests {

	@TestConfiguration
	static class SessionServiceImplTestContextConfiguration {

		@Bean
		public SessionService sessionService() {

			return new SessionServiceImpl();
		}
	}

	@Autowired
	private HttpSession session;
	
	@MockBean
	private Principal principal;
	
	@Autowired
	private SessionService sessionService;
	
	@Test
	public void testIfUserAdded() {

		String principalName = principal.getName();
		sessionService.addUserToSession(principal);
		
		assertThat(session.getAttribute("loggedUserName")).isEqualTo(principalName);
	}
}
