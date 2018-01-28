package com.test.callcenter.dispatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.test.callcenter.entity.Call;
import com.test.callcenter.entity.Director;
import com.test.callcenter.entity.Operator;
import com.test.callcenter.entity.Supervisor;
import com.test.callcenter.singleton.CallcenterSingletonQueue;
import com.test.callcenter.singleton.EmployeesSingletonQueue;

public class DispatcherTest {

	private static final int NUMBER_OF_EMPLOYEES = 10;
	private static final int NUMBER_OF_CALLS = 10;
	private Dispatcher dispatcher;

	@Before
	public void setUp() throws Exception {
		setUpEmployees();
		setUpCalls();
		dispatcher = new Dispatcher();
	}

	private void setUpCalls() {
		CallcenterSingletonQueue callsInstance = CallcenterSingletonQueue.INSTANCE;
		for (int i = 0; i < NUMBER_OF_CALLS; i++)
			callsInstance.getCallQueue().add(Call.builder().build());
	}

	private void setUpEmployees() {
		EmployeesSingletonQueue employeeInstance = EmployeesSingletonQueue.INSTANCE;
		for (int i = 0; i < NUMBER_OF_EMPLOYEES; i++) {
			employeeInstance.addEmployee(Operator.builder().build());
			employeeInstance.addEmployee(Supervisor.builder().build());
			employeeInstance.addEmployee(Director.builder().build());
		}
	}

	@Test
	public void testDispatchCall() {
		try {
			dispatcher.dispatchCall();
			// all calls should have been attended
			assertThat(CallcenterSingletonQueue.INSTANCE.getCallQueue()).isEmpty();
			// all calls should have been attendend by Operators
			assertThat(EmployeesSingletonQueue.INSTANCE.getOperatorQueue()).isEmpty();
			// No supervisor should be unqueued
			assertThat(EmployeesSingletonQueue.INSTANCE.getSupervisorQueue()).isNotEmpty()
					.hasOnlyElementsOfType(Supervisor.class).hasSize(NUMBER_OF_EMPLOYEES);
			// No directors should be unqueued
			assertThat(EmployeesSingletonQueue.INSTANCE.getDirectorQueue()).isNotEmpty()
					.hasOnlyElementsOfType(Director.class).hasSize(NUMBER_OF_EMPLOYEES);
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail();
		}
	}

}
