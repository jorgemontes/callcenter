package com.test.callcenter.dispatcher;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.test.callcenter.entity.CallcenterSingletonQueue;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@AllArgsConstructor
public class Dispatcher {

	private int numberOfOperator;
	private int numberOfSupervisor;
	private int numberOfDirector;

	public void dispatchCall() {
		CallcenterSingletonQueue singleton = CallcenterSingletonQueue.INSTANCE;

		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(numberOfOperator);
		System.out.println(Instant.now());
		for (int i = 0; i <= taskCreated; i++) {
			Runnable runnable = () -> {
				try {
					UUID threadId = UUID.randomUUID();
					System.out.println("Start Thread-" + threadId);
					TimeUnit.SECONDS.sleep((long) Math.random() * 1000);
					System.out.println("End Thread-" + threadId + " at " + Instant.now());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};

			threadPoolExecutor.execute(runnable);
		}
		threadPoolExecutor.shutdown();
	}
}
