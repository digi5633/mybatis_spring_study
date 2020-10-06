package mybatis_spring_study.service;

import mybatis_spring_study.dto.Department;

public interface DepartmentService {
	
	public int delDepartment(Department department);

	public int addDepartment(Department department);

}
