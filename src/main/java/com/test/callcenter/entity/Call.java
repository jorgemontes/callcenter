package com.test.callcenter.entity;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import lombok.Synchronized;
import lombok.extern.java.Log;

@Log
public class Call implements Runnable {

	@Synchronized
	public void run() {
		try {
			UUID threadId = UUID.randomUUID();
			long callTime = 5 + ((long) Math.random() * 5000);
			log.info("Start Call id : " + threadId + " time for this call: " + callTime);
			TimeUnit.SECONDS.sleep(callTime);
			System.out.println("End Call-" + threadId + " at " + Instant.now());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
