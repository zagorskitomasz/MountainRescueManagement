package com.zagorskidev.rescuecrm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.dao.RescuerDAOImpl;
import com.zagorskidev.rescuecrm.entity.Rescuer;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RescueCRMTests {
	
	@TestConfiguration
	static class RescuerDAOContextConfiguration{
		
		@Bean
		public RescuerDAO rescuerDAO() {
			
			return new RescuerDAOImpl();
		}
	}
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private RescuerDAO rescuerDAO;
	
	@Test
	public void gettingAll() {
		Rescuer testRescuer = new Rescuer("Test", "Rescuer");
		entityManager.persist(testRescuer);
		entityManager.flush();
		assertThat(rescuerDAO.getAll()).contains(testRescuer);
	}
}
