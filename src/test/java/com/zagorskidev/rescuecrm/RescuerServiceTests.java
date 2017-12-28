package com.zagorskidev.rescuecrm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.entity.Rescuer;
import com.zagorskidev.rescuecrm.service.RescuerService;
import com.zagorskidev.rescuecrm.service.RescuerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RescuerServiceTests {

	@TestConfiguration
	static class RescuerServiceImplTestContextConfiguration {

		@Bean
		public RescuerService rescuerService() {

			return new RescuerServiceImpl();
		}
	}
	
	@Autowired
	private RescuerService rescuerService;
	
	@MockBean
	private RescuerDAO rescuerDAO;
	
	@Before
	public void setUp() {
		
		Rescuer testRescuer = new Rescuer("Test", "Rescuer1");
		testRescuer.setId(10);
		
		Rescuer testRescuer2 = new Rescuer("Test", "Rescuer2");
		testRescuer2.setId(12);
		
		List<Rescuer> allRescuers = new ArrayList<>();
		allRescuers.add(testRescuer);
		allRescuers.add(testRescuer2);
		
		Mockito.when(rescuerDAO.get(10)).thenReturn(testRescuer);
		Mockito.when(rescuerDAO.get(12)).thenReturn(testRescuer2);
		Mockito.when(rescuerDAO.getAll()).thenReturn(allRescuers);
	}
	
	@Test
	public void testIfRescuerGot() {

		int id = 10;
		Rescuer found = rescuerService.getRescuerById(id);
		
		assertThat(found.getId()).isEqualTo(id);
	}
	
	@Test
	public void testIfAllRescuersGot() {
		
		Rescuer first = rescuerService.getRescuerById(10);
		Rescuer second = rescuerService.getRescuerById(12);
		
		assertThat(rescuerService.getAllRescuers()).contains(first, second);
	}
	
	@Test
	public void testEmptyRescuer() {

		Rescuer empty = rescuerService.createEmptyRescuer();
		
		assertThat(empty.getFirstName()).isEqualTo(null);
		assertThat(empty.getRescuerDetail().getAddress()).isEqualTo(null);
	}
}
