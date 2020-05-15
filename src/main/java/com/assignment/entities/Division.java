package com.assignment.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Division")
public class Division implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2768885389735356436L;
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @Column
	 private String divisionName;
	 @Column
	 private Long divisionHead;
	 @Column
	 private String businessUnit;
	 
	/*
	 * @OneToMany(mappedBy="division", cascade = CascadeType.ALL) Set<Employee>
	 * employes = new HashSet();
	 * 
	 * public Set<Employee> getEmployes() { return employes; } public void
	 * setEmployes(Set<Employee> employes) { this.employes = employes; }
	 */
	public Division(InputForDivision input) {
		super();
		this.divisionName = input.getDivisionName();
		this.divisionHead = input.getDivisionHead();
		this.businessUnit = input.getBusinessUnit();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Division() {};
	
	
}
