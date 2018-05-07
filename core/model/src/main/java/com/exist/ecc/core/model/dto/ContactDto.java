package com.exist.ecc.core.model.dto;

import java.util.Objects;

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

	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!this.getClass().equals(obj.getClass())) return false;

		ContactDto otherRole = (ContactDto)obj;
		if( this.type.toLowerCase().equals(otherRole.getType().toLowerCase()) ) {
			if( this.detail.toLowerCase().equals(otherRole.getDetail().toLowerCase()) ) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return Objects.hash(type + detail);
	}

	public String toString() {
		return String.format("Contact_%s : %s", type, detail);
	}
}
