package com.sb.model;

public class Customer {

	private String name;
	private CustomerType customerType;

	public Customer(String name, CustomerType customerType) {
		super();
		this.name = name;
		this.customerType = customerType;
	}

	public String getName() {
		return name;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

}
