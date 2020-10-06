package mybatis_spring_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.mapper.DepartmentMapper;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentMapper mapper;

	public int delDepartment(Department department) {
		return mapper.deleteDepartment(department);
	}

	public int addDepartment(Department department) {
		return mapper.insertDepartment(department);
	}

}
