package com.test.callcenter.entity;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
