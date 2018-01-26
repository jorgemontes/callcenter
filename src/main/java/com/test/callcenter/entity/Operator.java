package com.test.callcenter.entity;

import lombok.Builder;

@Builder
public class Operator extends Employee {

	@Override
	public String toString() {
		return "Operator " + this.getEmployeeId();
	}

}
