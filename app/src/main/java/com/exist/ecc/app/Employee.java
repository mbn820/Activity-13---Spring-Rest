package com.exist.ecc.app;

public class Employee {
	private Name name;
	private String address;
	private Integer age;

	public Employee() {

	}

	public Employee(Name name, String address, Integer age) {
		this.name = name;
		this.address = address;
		this.age = age;
	}

	public Name getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
