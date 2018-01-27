package com.test.callcenter.dispatcher;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.test.callcenter.entity.Call;
import com.test.callcenter.entity.Employee;
import com.test.callcenter.singleton.CallcenterSingletonQueue;
import com.test.callcenter.singleton.EmployeesSingletonQueue;

import lombok.extern.java.Log;

@Log
public class Dispatcher {

	CallcenterSingletonQueue callcenterSingletonQueue = CallcenterSingletonQueue.INSTANCE;
	EmployeesSingletonQueue employeesSingletonQueue = EmployeesSingletonQueue.INSTANCE;

	public void dispatchCall() throws InterruptedException {
		ExecutorService threadPoolExecutor = Executors
				.newFixedThreadPool(employeesSingletonQueue.getTotalNumberOfEmployees());
		log.info("Started serving calls");
		while (!callcenterSingletonQueue.getCallQueue().isEmpty()) {
			Call call = callcenterSingletonQueue.getCallQueue().poll();
			Employee employee = findFreeEmployee();
			if (!Optional.ofNullable(employee).isPresent()) {
				log.info("Call " + call.getId() + " should be requeued");
				callcenterSingletonQueue.getCallQueue().add(call);
				log.info(callcenterSingletonQueue.getCallQueue().size() + " still queued calls");
				continue;
			}
			call.setEmployee(employee);
			threadPoolExecutor.execute(call);
		}
		threadPoolExecutor.shutdown();
		log.info("All Calls served");

	}

	private Employee findFreeEmployee() {
		Employee employee = null;
		if (!employeesSingletonQueue.getOperatorQueue().isEmpty())
			employee = employeesSingletonQueue.getOperatorQueue().poll();
		else if (!employeesSingletonQueue.getSupervisorQueue().isEmpty()) {
			employee = employeesSingletonQueue.getSupervisorQueue().poll();
		} else if (!employeesSingletonQueue.getDirectorQueue().isEmpty()) {
			employee = employeesSingletonQueue.getDirectorQueue().poll();
		}
		return employee;
	}
}
