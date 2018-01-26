package com.test.callcenter.entity;

import lombok.Builder;

@Builder
public class Supervisor extends Employee {

	@Override
	public String toString() {
		return "Supervisor " + this.getEmployeeId();
	}

}
