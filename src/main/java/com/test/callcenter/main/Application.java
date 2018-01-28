package com.test.callcenter.main;

import com.test.callcenter.dispatcher.Dispatcher;
import com.test.callcenter.entity.Call;
import com.test.callcenter.entity.Director;
import com.test.callcenter.entity.Operator;
import com.test.callcenter.entity.Supervisor;
import com.test.callcenter.singleton.CallcenterSingletonQueue;
import com.test.callcenter.singleton.EmployeesSingletonQueue;

import lombok.extern.java.Log;

@Log
public class Application {

	public static void main(String[] args) throws InterruptedException {
		Integer numberOfCalls = Integer.valueOf(args[0]);
		log.info("Creating " + numberOfCalls + " calls");
		fillCalls(numberOfCalls);
		int numberOfEmployees = Integer.valueOf(args[1]);
		log.info("Creating " + numberOfEmployees + " employees");
		fillEmployees(numberOfEmployees);
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.dispatchCall();
	}

	private static void fillEmployees(int numberOfEmployees) {
		EmployeesSingletonQueue employeesSingletonQueue = EmployeesSingletonQueue.INSTANCE;
		for (int i = 0; i < numberOfEmployees; i++) {
			employeesSingletonQueue.addEmployee(Operator.builder().build());
			employeesSingletonQueue.addEmployee(Supervisor.builder().build());
			employeesSingletonQueue.addEmployee(Director.builder().build());

		}
	}

	private static void fillCalls(Integer numberOfCalls) {
		CallcenterSingletonQueue singleton = CallcenterSingletonQueue.INSTANCE;
		for (int i = 0; i < numberOfCalls; i++) {
			singleton.getCallQueue().add(Call.builder().build());
		}
	}
}
