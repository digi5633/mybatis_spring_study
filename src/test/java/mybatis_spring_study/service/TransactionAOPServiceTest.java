package mybatis_spring_study.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.config.ContextRoot;
import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionAOPServiceTest {
	
	protected static final Log log = LogFactory.getLog(TransactionAOPServiceTest.class);

	@Autowired
	private TransactionAOPService service;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test(expected = DuplicateKeyException.class)
	public void testATrRegisterTransaction_Dept_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(1, "인사", 10); // DuplicateKeyException
		Employee employee = new Employee(1006, "박규영", "과장", new Employee(4377), 4100000, department);

		service.trRegisterTransaction(department, employee);
	}

	@Test(expected = DuplicateKeyException.class)
	public void testBTrRegisterTransaction_Emp_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7, "인사", 10);
		Employee employee = new Employee(4377, "박규영", "과장", new Employee(4377), 4100000, department);

		service.trRegisterTransaction(department, employee);
	}

	@Test
	public void testCTrRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7, "인사", 10);
		Employee employee = new Employee(1006, "박규영", "과장", new Employee(4377), 4100000, department);

		service.trRegisterTransaction(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testDTrUnRegisterTransaction_Dept_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(100); // RuntimeException -> roll back
		Employee employee = new Employee(1006); // roll back 되므로 삭제 되면 안됨

		service.trUnRegisterTransaction(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testETrUnRegisterTransaction_Emp_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7); // 삭제
		Employee employee = new Employee(9999); // RuntimeException -> roll back

		service.trUnRegisterTransaction(department, employee);
	}

	@Test
	public void testFTrUnRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7);
		Employee employee = new Employee(1006);

		service.trUnRegisterTransaction(department, employee);
	}

}
