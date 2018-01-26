package com.test.callcenter.entity;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public enum CallcenterSingletonQueue {

	INSTANCE;

	Queue<Call> queue = new LinkedList<>();

}
