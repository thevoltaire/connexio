package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.assignment.entities.Division;
import com.assignment.entities.Employee;
import com.assignment.entities.InputForDivision;
import com.assignment.entities.InputForEmployee;
import com.assignment.entities.Quote;
import com.assignment.entities.Value;
import com.assignment.service.DivisionService;
import com.assignment.statics.Statics;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class DivisionController {
	
	@Autowired
	private DivisionService divisionService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper mapper;
	 
	 @ApiOperation(value = "Create Division")
	 @PostMapping("/division")
	public ResponseEntity<Division> createDivision(
			@ApiParam(value = "Division to name be added", required = true) 
			@RequestBody InputForDivision name
	) {

		return ResponseEntity.ok(divisionService.createNewDivison(name));
	}
	 
	
	 
	@ApiOperation(value = "Create Employee")
	@PostMapping("/employee")
	public ResponseEntity<Object> createEmployee(
			@ApiParam(value = "Employee be added", required = true) 
			@RequestBody InputForEmployee name
	) {
		try {
			Employee newEmployee = divisionService.createNewEmployee(name);
			return ResponseEntity.ok(newEmployee);
			
		}catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			return new ResponseEntity<Object>(
			          "Divison ID is invalid", 
			          HttpStatus.BAD_REQUEST);
		}
		
		
	}
	 
	
	  @ApiOperation(value = "Search Employee with manager name wild card")
	  @GetMapping("/employee/{manangerName}") public
	  ResponseEntity<List<Employee>>findEmployeesbyManager(
	  @ApiParam(value = "Manager name starts with", required = true)
	  @PathVariable String manangerName ) {
		  return ResponseEntity.ok(divisionService.findEmployeesbyManager(manangerName)); 
	  }
	 
	   @GetMapping("/employee/") 
	   public ResponseEntity<List<Employee>> findEmployeesbyDivision(
		  @ApiParam(value = "Any Division ID", required = true)
		  @RequestParam Long divisionID ) {
		   List<Employee> result =  divisionService.findEmployeesbyDivision(divisionID);
		   return ResponseEntity.ok(result); 
		  }

	   
		 @ApiOperation(value = "Employee Count under Each ManagerID")
		 @GetMapping("/employee/employeeStatsByManager")
		 public ResponseEntity<List<Object[]>> getEmpStats(){
			return ResponseEntity.ok(divisionService.findEmployeeStats());
			 
		 }
		 
		 @ApiOperation(value = "Manger Count under Each division")
		 @GetMapping("/employee/managerStatsByDivison")
		 public ResponseEntity<List<Employee>> getManagersInDiv(
				@ApiParam(value = "Any Division ID", required = true)
				@RequestParam Long divisionID ){
			return ResponseEntity.ok(divisionService.getManagersInDiv(divisionID));
			 
		 }
		 
		 @ApiOperation(value = "Employees Count under Each division")
		 @GetMapping("/employee/employeeStatsByDivison")
		 public ResponseEntity<List<Object[]>> getEmployeesInDiv(){
			return ResponseEntity.ok(divisionService.findEmployeeStatsByDivision());
			 
		 }
		 
		 
	
	 @ApiOperation(value = "Pull all the availiable Quotes from Public API")
	 @GetMapping("/pullQuotes")
	 public ResponseEntity<List<Quote>> getAllQuotes(){
				List<Quote> convertedlQuotes = null;
				try {
					String allQuotes =  restTemplate.getForObject(Statics.ExternalRESTURL, String.class);
					convertedlQuotes = mapper.readValue(allQuotes, new TypeReference<List<Quote>>(){});
					 divisionService.saveAllquotes(convertedlQuotes);
					 return ResponseEntity.ok(convertedlQuotes);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return (ResponseEntity<List<Quote>>) ResponseEntity.notFound();
				}
		}
	 	@GetMapping("/quotes/") 
	   public ResponseEntity<Value> findQuoteBuID(
		  @ApiParam(value = "Any Quote ID to Search", required = true)
		  @RequestParam Long quoteID ) {
		   Value result =  divisionService.findQuoteByID(quoteID);
		   return ResponseEntity.ok(result); 
		  }
	 
}
