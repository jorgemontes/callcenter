package com.test.callcenter.entity;

import java.util.concurrent.SynchronousQueue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public enum CallcenterSingletonQueue {

	INSTANCE;

	SynchronousQueue<Call> queue = new SynchronousQueue<>();

}
