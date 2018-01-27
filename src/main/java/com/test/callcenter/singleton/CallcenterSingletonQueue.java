package com.test.callcenter.singleton;

import java.util.LinkedList;
import java.util.Queue;

import com.test.callcenter.entity.Call;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum CallcenterSingletonQueue {

	INSTANCE;

	Queue<Call> callQueue = new LinkedList<>();

}
