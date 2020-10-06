package mybatis_spring_study.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.dto.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context-root.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentServiceTest {

	protected static final Log log = LogFactory.getLog(DepartmentServiceTest.class);

	@Autowired
	private DepartmentService service;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01AddDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5, "마케팅", 20);
		int res = service.addDepartment(department);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test02DelDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(5);
		int res = service.delDepartment(department);
		Assert.assertEquals(1, res);
	}

}
