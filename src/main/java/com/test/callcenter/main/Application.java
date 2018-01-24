package com.test.callcenter.main;

import com.test.callcenter.dispatcher.Dispatcher;
import com.test.callcenter.entity.Call;
import com.test.callcenter.entity.CallcenterSingletonQueue;

import lombok.extern.java.Log;

@Log
public class Application {

	public static void main(String[] args) throws InterruptedException {
		Integer numberOfCalls = Integer.valueOf(args[0]);
		log.info("Creating " + numberOfCalls + " calls");
		CallcenterSingletonQueue singleton = CallcenterSingletonQueue.INSTANCE;
		for (int i = 0; i < numberOfCalls; i++) {
			singleton.getQueue().put(new Call());
		}
		int numberOfOperator = Integer.valueOf(args[1]);
		int numberOfSupervisor = Integer.valueOf(args[2]);
		int numberOfDirector = Integer.valueOf(args[3]);
		Dispatcher dispatcher = new Dispatcher(numberOfOperator, numberOfSupervisor, numberOfDirector);
		log.info(singleton.toString());

	}
}
