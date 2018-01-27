package com.test.callcenter.singleton;

import java.util.LinkedList;
import java.util.Queue;

import com.test.callcenter.entity.Employee;
import com.test.callcenter.entity.Operator;
import com.test.callcenter.entity.Supervisor;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum EmployeesSingletonQueue {

	INSTANCE;

	private Queue<Employee> operatorQueue = new LinkedList<>();
	private Queue<Employee> supervisorQueue = new LinkedList<>();
	private Queue<Employee> directorQueue = new LinkedList<>();

	public int getTotalNumberOfEmployees() {
		return operatorQueue.size() + supervisorQueue.size() + directorQueue.size();
	}

	public void addEmployee(Employee employee) {
		if (employee instanceof Operator) {
			this.operatorQueue.add(employee);
		} else if (employee instanceof Supervisor) {
			this.supervisorQueue.add(employee);
		} else {
			this.directorQueue.add(employee);
		}
	}

}
