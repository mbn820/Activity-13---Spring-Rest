package com.exist.ecc.core.model.dto;

public class ContactDto {

	private int id;
	private String type;
	private String detail;

	public ContactDto() {}
	public ContactDto(String type, String detail) {
		this.type = type;
		this.detail = detail;
	}

	public int getId() {
    	return id;
    }

	public String getType() {
		return type;
	}

	public String getDetail() {
		return detail;
	}

 	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String toString() {
		return String.format("Contact_%s : %s", type, detail);
	}
}
