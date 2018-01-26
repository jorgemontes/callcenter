package com.test.callcenter.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

	private UUID employeeId;

	public Employee() {
		this.employeeId = UUID.randomUUID();
	}

}
