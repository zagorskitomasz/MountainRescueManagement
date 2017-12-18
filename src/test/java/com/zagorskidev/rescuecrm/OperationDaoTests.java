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

import com.zagorskidev.rescuecrm.dao.OperationDAO;
import com.zagorskidev.rescuecrm.dao.OperationDAOImpl;
import com.zagorskidev.rescuecrm.dao.RescuerDAO;
import com.zagorskidev.rescuecrm.dao.RescuerDAOImpl;
import com.zagorskidev.rescuecrm.entity.Operation;
import com.zagorskidev.rescuecrm.entity.OperationDetail;
import com.zagorskidev.rescuecrm.entity.Rescuer;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OperationDaoTests {

	@TestConfiguration
	static class OperationDAOContextConfiguration {

		@Bean
		public OperationDAO operationDAO() {

			return new OperationDAOImpl();
		}
		
		@Bean
		public RescuerDAO rescuerDAO() {
			
			return new RescuerDAOImpl();
		}
	}

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OperationDAO operationDAO;

	private Operation createOperation(String destination) {
		
		Rescuer testRescuer = new Rescuer("Test", "Rescuer");
		entityManager.persist(testRescuer);
		Operation testOperation = new Operation(destination);
		testOperation.addRescuer(testRescuer);
		
		return testOperation;
	}
	
	@Test
	public void testIfGetAllReturnListContains() {

		Operation testOperation1 = createOperation("Test1");
		Operation testOperation2 = createOperation("Test2");

		entityManager.persist(testOperation1);
		entityManager.persist(testOperation2);

		assertThat(operationDAO.getAll()).contains(testOperation1, testOperation2);

		entityManager.flush();
	}

	@Test
	public void testIfGetAllSortsList() {

		Operation testOperation1 = createOperation("Test1");
		Operation testOperation2 = createOperation("Test2");
		Operation testOperation3 = createOperation("Test3");

		entityManager.persist(testOperation1);
		entityManager.persist(testOperation2);
		entityManager.persist(testOperation3);

		assertThat(operationDAO.getAll()).isSortedAccordingTo((a, b) -> {
			return Integer.compare(b.getId(), a.getId());
		});

		entityManager.flush();
	}

	@Test
	public void testGetByIdReturnItem() {

		Operation testOperation = createOperation("Test");
		int testId = (Integer) entityManager.persistAndGetId(testOperation);

		assertThat(operationDAO.get(testId)).isEqualTo(testOperation);

		entityManager.flush();
	}

	@Test
	public void testPersisting() {

		Operation testOperation = createOperation("Test");
		OperationDetail testDetail = new OperationDetail("TDescription");
		testOperation.setOperationDetail(testDetail);
		Rescuer testRescuer = testOperation.getRescuers().get(0);

		operationDAO.persist(testOperation);

		assertThat(entityManager.find(Operation.class, testOperation.getId())).isEqualTo(testOperation);
		assertThat(entityManager.find(Operation.class, testOperation.getId()).getOperationDetail().getDescription())
				.isEqualTo("TDescription");
		assertThat(entityManager.find(Operation.class, testOperation.getId()).getRescuers()).contains(testRescuer);

		entityManager.flush();
	}

	@Test
	public void testMerging() {

		Operation testOperation = createOperation("Test");
		int testId = (Integer) entityManager.persistAndGetId(testOperation);

		Operation newOperation = createOperation("New");
		newOperation.setId(testId);
		operationDAO.merge(newOperation);

		assertThat(entityManager.find(Operation.class, testId).getDestination()).isEqualTo("New");

		entityManager.flush();
	}

	@Test
	public void testDeleting() {

		Operation testOperation = createOperation("Test");
		int testId = (Integer) entityManager.persistAndGetId(testOperation);

		operationDAO.delete(testId);

		assertThat(operationDAO.getAll()).doesNotContain(testOperation);

		entityManager.flush();
	}
}
