package com.test.callcenter.dispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.test.callcenter.entity.Call;
import com.test.callcenter.entity.CallcenterSingletonQueue;
import com.test.callcenter.entity.Employee;
import com.test.callcenter.entity.EmployeesSingletonQueue;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@AllArgsConstructor
public class Dispatcher {

	public void dispatchCall() throws InterruptedException {
		CallcenterSingletonQueue callcenterSingletonQueue = CallcenterSingletonQueue.INSTANCE;
		EmployeesSingletonQueue employeesSingletonQueue = EmployeesSingletonQueue.INSTANCE;
		ExecutorService threadPoolExecutor = Executors
				.newFixedThreadPool(employeesSingletonQueue.getTotalNumberOfEmployees());
		log.info("Started serving calls");
		while (!callcenterSingletonQueue.getQueue().isEmpty()) {
			Call call = callcenterSingletonQueue.getQueue().poll();
			Employee employee;
			if (!employeesSingletonQueue.getOperatorQueue().isEmpty())
				employee = employeesSingletonQueue.getOperatorQueue().poll();
			else if (!employeesSingletonQueue.getSupervisorQueue().isEmpty()) {
				employee = employeesSingletonQueue.getSupervisorQueue().poll();
			} else if (!employeesSingletonQueue.getDirectorQueue().isEmpty()) {
				employee = employeesSingletonQueue.getDirectorQueue().poll();
			} else {
				log.info("Call " + call.getId() + " should be requeued");
				callcenterSingletonQueue.getQueue().add(call);
				log.info(callcenterSingletonQueue.getQueue().size() + " still queued calls");
				TimeUnit.SECONDS.sleep(1);
				continue;
			}
			call.setEmployee(employee);
			threadPoolExecutor.execute(call);
		}
		threadPoolExecutor.shutdown();
		log.info("All Calls served");
	}
}
