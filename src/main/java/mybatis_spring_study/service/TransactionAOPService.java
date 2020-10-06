package mybatis_spring_study.service;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

public interface TransactionAOPService {

	public void trRegisterTransaction(Department department, Employee employee);

	public void trUnRegisterTransaction(Department department, Employee employee);

}
