CREATE TABLE department (
	deptno NUMBER NOT NULL,
	deptname VARCHAR2(50) NOT NULL,
	floor NUMBER NULL,
	PRIMARY KEY(deptno)
);

CREATE TABLE employee (
	empno NUMBER NOT NULL PRIMARY KEY,
	empname VARCHAR2(50) NOT NULL,
	title VARCHAR2(50) null,
	manager NUMBER NULL,
	salary NUMBER NULL,
	dno NUMBER NULL,
	FOREIGN KEY(manager) REFERENCES employee(empno),
	FOREIGN KEY(dno) REFERENCES department(deptno)
);

SELECT *
  FROM USER_TABLES;