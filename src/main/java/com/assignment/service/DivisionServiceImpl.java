package com.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.assignment.dao.DivisionDao;
import com.assignment.dao.DivisionDaoImpl;
import com.assignment.dao.EmployeeDao;
import com.assignment.dao.ValuesDao;
import com.assignment.entities.Division;
import com.assignment.entities.Employee;
import com.assignment.entities.InputForDivision;
import com.assignment.entities.InputForEmployee;
import com.assignment.entities.Quote;
import com.assignment.entities.Value;

@Service
public class DivisionServiceImpl implements DivisionService{
	
	@Autowired
	public DivisionDao divisionDao;
	
	@Autowired
	public ValuesDao valuesDao;
	
	@Autowired
	public EmployeeDao employeeDao;
	
	
	@Autowired
	public DivisionDaoImpl daoImpl;

	@Override
	public Division createNewDivison(InputForDivision name) {
		// TODO Auto-generated method stub
		System.out.println("Inventory Input - " + name);
		return divisionDao.save(new Division(name));
		
	}

	@Override
	public void saveAllquotes(List<Quote> allQuotes) {

		List <Value> allValues = allQuotes.stream().map(x->x.getValue()).collect(Collectors.toList());
		System.out.println("All Values - "+allQuotes);
		valuesDao.saveAll(allValues);
	
		
	}

	@Override
	public Employee createNewEmployee(InputForEmployee name) throws DataIntegrityViolationException{
		return employeeDao.save(new Employee(name,divisionDao.getOne(name.getDivisionId())));
	}

	@Override
	public List<Employee> findEmployeesbyManager(String manangerName) {
		List<Employee> allEmploy = employeeDao.findAll();
		Set<Long> emplWithFiletredName = allEmploy.stream().
				filter(x-> StringUtils.startsWithIgnoreCase(x.getEmployeeName(), manangerName)).map(x->x.getEmpId()).collect(Collectors.toSet());
		List<Employee> finalList =  allEmploy.stream().filter(x->
								emplWithFiletredName.contains(x.getManagerId())).collect(Collectors.toList());
		return finalList;
		
	}

	@Override
	public List<Employee> findEmployeesbyDivision(Long divisionID) {
		// TODO Auto-generated method stub
		return daoImpl.findEmployeesbyDivision(divisionID);
	}

	@Override
	public List<Object[]> findEmployeeStats() {
		// TODO Auto-generated method stub
		return daoImpl.findNumberOfEmployeesByManager();
	}

	@Override
	public List<Employee> getManagersInDiv(Long divisionID) {
		// TODO Auto-generated method stub
		return daoImpl.findManagersInDiv(divisionID);
	}

	@Override
	public List<Object[]> findEmployeeStatsByDivision() {
		// TODO Auto-generated method stub
		return daoImpl.findEmployeeStatsByDivision();
	}

	@Override
	public Value findQuoteByID(Long quoteID) {
		// TODO Auto-generated method stub
		Optional<Value> result = valuesDao.findById(quoteID);
		return result.orElse(new Value());
	}

	

}
