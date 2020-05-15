package com.assignment.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4701441778842855029L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	@Column
	private String employeeName;
	@Column
	private Long managerId;
	@Column
	private Float payroleData;
	
	@ManyToOne
	@JoinColumn(name = "division_id",updatable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Division division;
	
	public Employee() {}
	
	public Employee(InputForEmployee name, Division division) {
		super();
		this.employeeName = name.getEmployeeName();
		this.managerId = name.getManagerName();
		this.payroleData = name.getPayroleData();
		this.division = division;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getManagerId() {
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

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	
	
}
