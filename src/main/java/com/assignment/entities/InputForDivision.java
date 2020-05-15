package com.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;

public class InputForDivision implements Serializable{

	public InputForDivision() {};
	public InputForDivision(String divisionName, Long divisionHead, String businessUnit) {
		super();
		this.divisionName = divisionName;
		this.divisionHead = divisionHead;
		this.businessUnit = businessUnit;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -5795529669839693965L;

	private String divisionName;
	 private Long divisionHead;
	 @Override
	public String toString() {
		return "InputForDivision [divisionName=" + divisionName + ", divisionHead=" + divisionHead + ", businessUnit="
				+ businessUnit + "]";
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public Long getDivisionHead() {
		return divisionHead;
	}
	public void setDivisionHead(Long divisionHead) {
		this.divisionHead = divisionHead;
	}
	public String getBusinessUnit() {
		return businessUnit;
	}
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	private String businessUnit;
}
