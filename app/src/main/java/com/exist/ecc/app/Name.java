package com.exist.ecc.app;

public class Name {
	private String first;
	private String last;

	public Name() {

	}

	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String toString() {
		return "NAME: " + first + " " + last;
	}
}
