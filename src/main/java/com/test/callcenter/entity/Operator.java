package com.test.callcenter.entity;

import com.test.callcenter.entity.Employee;

import lombok.Builder;

@Builder
public class Operator extends Employee {

	@Override
	public String toString() {
		return "Operator " + this.getEmployeeId();
	}

}
