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
import com.zagorskidev.rescuecrm.entity.RescuerDetail;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RescuerDaoTests {

	@TestConfiguration
	static class RescuerDAOContextConfiguration {

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
	public void testIfGetAllReturnListContains() {

		Rescuer testRescuer1 = new Rescuer("Test", "Rescuer");
		Rescuer testRescuer2 = new Rescuer("Test", "Rescuer");

		entityManager.persist(testRescuer1);
		entityManager.persist(testRescuer2);

		assertThat(rescuerDAO.getAll()).contains(testRescuer1, testRescuer2);

		entityManager.flush();
	}

	@Test
	public void testIfGetAllSortsList() {

		Rescuer testRescuer1 = new Rescuer("Aaa", "ARescuer");
		Rescuer testRescuer2 = new Rescuer("Bbb", "BRescuer");
		Rescuer testRescuer3 = new Rescuer("Ccc", "BRescuer");

		entityManager.persist(testRescuer2);
		entityManager.persist(testRescuer1);
		entityManager.persist(testRescuer3);

		assertThat(rescuerDAO.getAll()).isSortedAccordingTo((a, b) -> {
			if (!a.getLastName().equals(b.getLastName()))
				return a.getLastName().compareTo(b.getLastName());
			else
				return a.getFirstName().compareTo(b.getFirstName());
		});

		entityManager.flush();
	}

	@Test
	public void testGetByIdReturnItem() {

		Rescuer testRescuer = new Rescuer("Test", "Rescuer");
		int testId = (Integer) entityManager.persistAndGetId(testRescuer);

		assertThat(rescuerDAO.get(testId)).isEqualTo(testRescuer);

		entityManager.flush();
	}

	@Test
	public void testPersisting() {

		Rescuer testRescuer = new Rescuer("Test", "Rescuer");
		RescuerDetail testDetail = new RescuerDetail("TAddress", "T@Ma.il", "123456789");
		testRescuer.setRescuerDetail(testDetail);
		rescuerDAO.persist(testRescuer);

		assertThat(entityManager.find(Rescuer.class, testRescuer.getId())).isEqualTo(testRescuer);
		assertThat(entityManager.find(Rescuer.class, testRescuer.getId()).getRescuerDetail().getAddress())
				.isEqualTo("TAddress");

		entityManager.flush();
	}

	@Test
	public void testMerging() {

		Rescuer testRescuer = new Rescuer("Test", "Rescuer");
		int testId = (Integer) entityManager.persistAndGetId(testRescuer);

		Rescuer newRescuer = new Rescuer("New", "Rescuer");
		newRescuer.setId(testId);
		rescuerDAO.merge(newRescuer);

		assertThat(entityManager.find(Rescuer.class, testId).getFirstName()).isEqualTo("New");

		entityManager.flush();
	}

	@Test
	public void testDeleting() {

		Rescuer testRescuer = new Rescuer("Test", "Rescuer");
		int testId = (Integer) entityManager.persistAndGetId(testRescuer);

		rescuerDAO.delete(testId);

		assertThat(rescuerDAO.getAll()).doesNotContain(testRescuer);

		entityManager.flush();
	}
}
