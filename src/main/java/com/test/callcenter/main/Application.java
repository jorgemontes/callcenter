package com.test.callcenter.main;

import com.test.callcenter.dispatcher.Dispatcher;
import com.test.callcenter.entity.Call;
import com.test.callcenter.entity.CallcenterSingletonQueue;
import com.test.callcenter.entity.Director;
import com.test.callcenter.entity.EmployeesSingletonQueue;
import com.test.callcenter.entity.Operator;
import com.test.callcenter.entity.Supervisor;

import lombok.extern.java.Log;

@Log
public class Application {

	public static void main(String[] args) throws InterruptedException {
		Integer numberOfCalls = Integer.valueOf(args[0]);
		log.info("Creating " + numberOfCalls + " calls");
		CallcenterSingletonQueue singleton = CallcenterSingletonQueue.INSTANCE;
		for (int i = 0; i < numberOfCalls; i++) {
			singleton.getQueue().add(new Call());
		}
		int numberOfEmployees = Integer.valueOf(args[1]);
		EmployeesSingletonQueue employeesSingletonQueue = EmployeesSingletonQueue.INSTANCE;
		for (int i = 0; i < numberOfEmployees; i++) {
			employeesSingletonQueue.addEmployee(Operator.builder().build());
			employeesSingletonQueue.addEmployee(Supervisor.builder().build());
			employeesSingletonQueue.addEmployee(Director.builder().build());

		}

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.dispatchCall();

	}
}
