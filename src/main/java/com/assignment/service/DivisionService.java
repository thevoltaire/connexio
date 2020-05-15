package com.assignment.service;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;

import com.assignment.entities.Division;
import com.assignment.entities.Employee;
import com.assignment.entities.InputForDivision;
import com.assignment.entities.InputForEmployee;
import com.assignment.entities.Quote;
import com.assignment.entities.Value;

public interface DivisionService {

	public Division createNewDivison(InputForDivision name);

	public void saveAllquotes(List<Quote> allQuotes);

	public Employee createNewEmployee(InputForEmployee name) throws DataIntegrityViolationException;

	public List<Employee> findEmployeesbyManager(String manangerName);

	public List<Employee> findEmployeesbyDivision(Long divisionID);

	public List<Object[]> findEmployeeStats();

	public List<Employee> getManagersInDiv(Long divisionID);

	public List<Object[]> findEmployeeStatsByDivision();

	public Value findQuoteByID(Long quoteID);

	
	
}
