package com.assignment.entities;

import java.io.Serializable;

public class InputForEmployee implements Serializable{

	public InputForEmployee() {
		
	}
	
	public InputForEmployee(Long divisionId, String employeeName, Long managerId, Float payroleData) {
		super();
		this.divisionId = divisionId;
		this.employeeName = employeeName;
		this.managerId = managerId;
		this.payroleData = payroleData;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -141532152050413264L;
	private Long divisionId;
	private String employeeName;
	private Long managerId;
	private Float payroleData;
	
	public Long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Long getManagerName() {
		return managerId;
	}
	public void setManagerName(Long managerId) {
		this.managerId = managerId;
	}
	public Float getPayroleData() {
		return payroleData;
	}
	public void setPayroleData(Float payroleData) {
		this.payroleData = payroleData;
	}
	

}
