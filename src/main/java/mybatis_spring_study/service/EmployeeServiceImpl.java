package mybatis_spring_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mybatis_spring_study.dto.Employee;
import mybatis_spring_study.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper mapper;

	public int addEmployee(Employee employee) {
		return mapper.insertEmployee(employee);
	}

	public int delEmployee(Employee employee) {
		return mapper.deleteEmployee(employee);
	}

}
