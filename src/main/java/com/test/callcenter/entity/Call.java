package com.test.callcenter.entity;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.test.callcenter.singleton.EmployeesSingletonQueue;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import lombok.extern.java.Log;

@Log
@Setter
@Getter
@Builder
public class Call implements Runnable {

	private static final int RANDOM_MAX_TIME = 5;
	private static final int MIN_CALLTIME = 5;
	private Employee employee;
	private UUID id = UUID.randomUUID();

	@Synchronized
	public void run() {
		try {
			Random random = new Random();
			int callTime = MIN_CALLTIME + random.nextInt(RANDOM_MAX_TIME);
			log.info("Start Call id : " + this.id + " time for this call: " + callTime + "s employee will service "
					+ this.employee);
			TimeUnit.SECONDS.sleep(callTime);
			log.info("End Call " + this.id + " served by " + this.employee.toString());
			EmployeesSingletonQueue.INSTANCE.addEmployee(getEmployee());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
