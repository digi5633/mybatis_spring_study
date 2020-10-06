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
public class TransactionServiceTest {

	protected static final Log log = LogFactory.getLog(TransactionServiceTest.class);

	@Autowired
	private TransactionService service;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test(expected = DuplicateKeyException.class)
	public void testARegisterTransaction_Dept_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(1, "태스크포스", 10); // DuplicateKeyException
		Employee employee = new Employee(1004, "박신혜", "과장", new Employee(4377), 4100000, department);

		service.registerTransaction(department, employee);
	}

	@Test(expected = DuplicateKeyException.class)
	public void testBRegisterTransaction_Emp_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "태스크포스", 10);
		Employee employee = new Employee(4377, "박신혜", "과장", new Employee(4377), 4100000, department);

		service.registerTransaction(department, employee);
	}

	@Test
	public void testCRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "태스크포스", 10);
		Employee employee = new Employee(1005, "박신혜", "과장", new Employee(4377), 4100000, department);

		service.registerTransaction(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testDUnRegisterTransaction_Dept_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(100); // RuntimeException -> roll back
		Employee employee = new Employee(1005); // roll back 되므로 삭제 되면 안됨

		service.unRegisterTransaction(department, employee);
	}

	@Test(expected = RuntimeException.class)
	public void testEUnRegisterTransaction_Emp_Fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6); // 삭제
		Employee employee = new Employee(9999); // RuntimeException -> roll back

		service.unRegisterTransaction(department, employee);
	}

	@Test
	public void testFUnRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6);
		Employee employee = new Employee(1005);

		service.unRegisterTransaction(department, employee);
	}

}
