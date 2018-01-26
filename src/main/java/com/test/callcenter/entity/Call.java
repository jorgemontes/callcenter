package com.test.callcenter.entity;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.java.Log;

@Log
@Setter
@Getter
public class Call implements Runnable {

	private Employee employee;
	private UUID id = UUID.randomUUID();

	@Synchronized
	public void run() {
		try {
			Random random = new Random();
			int callTime = 15 + random.nextInt(5);
			log.info("Start Call id : " + this.id + " time for this call: " + callTime + "s employee will service "
					+ this.employee);
			TimeUnit.SECONDS.sleep(callTime);
			log.info("End Call-" + this.id + " served by " + this.employee.toString());
			EmployeesSingletonQueue.INSTANCE.addEmployee(getEmployee());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
