package com.test.callcenter.entity;

import lombok.Builder;

@Builder
public class Director extends Employee {

	@Override
	public String toString() {
		return "Director " + this.getEmployeeId();
	}

}
