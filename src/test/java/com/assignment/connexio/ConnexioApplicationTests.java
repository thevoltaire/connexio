package com.assignment.connexio;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.assignment.entities.Division;
import com.assignment.entities.InputForDivision;
import com.assignment.entities.InputForEmployee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class ConnexioApplicationTests {

	@LocalServerPort
    int randomServerPort;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testcreateDivision() throws Exception {
		 RestTemplate restTemplate = new RestTemplate();
		 InputForDivision testName = new InputForDivision("test", (long) 100, "test");
		    HttpHeaders headers = new HttpHeaders();
		    HttpEntity<InputForDivision> request = new HttpEntity<>(testName, headers);
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/division";
	    URI uri = new URI(baseUrl);
	    ResponseEntity<Division> result = restTemplate.postForEntity(uri, request, Division.class);
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, result.getBody().getDivisionHead().equals(Long.valueOf(100)));
	}
	
	@Test()
	void testcreateEmployee() throws Exception {

		 RestTemplate restTemplate = new RestTemplate();
		 InputForEmployee testEmployee = new InputForEmployee(Long.valueOf(99), "Test", Long.valueOf(1), Float.valueOf(1000));
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<InputForEmployee> request = new HttpEntity<>(testEmployee, headers);
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee";
	    URI uri = new URI(baseUrl);
	    
		/*
		 * Assert.assertEquals(417, result.getStatusCodeValue()); Assert.(false,
		 * result.getBody().getEmployeeName().equals("Test"))
		 * 
		 */;
		/*
		 * ResponseEntity<Object> response = restTemplate.postForEntity(uri, request,
		 * Object.class); System.out.println("testcreateEmployee - "+request +
		 * " - "+response)
		 */;
		 
		 Assertions.assertThrows(org.springframework.web.client.HttpClientErrorException.class
				 			,  () -> restTemplate.postForEntity(uri, request, Object.class) );
		/*
		 * Assertions.assertThrows(org.springframework.web.client.
		 * HttpClientErrorException.class, () -> restTemplate.postForEntity(uri,
		 * request, Employee.class));
		 */
	}
	@Test()
	void testfindEmployeesbyManager() throws Exception {
		 RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee/{manangerName}";
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

	    Map<String,Object> map = new HashMap<String, Object>();
	    map.put("manangerName", "S");
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
	            .uriVariables(map);
	    
	    System.out.println("URI - > "+builder.toUriString());

	    HttpEntity<?> entity = new HttpEntity<>(headers);

	    HttpEntity<List> response = restTemplate.exchange(
	            builder.toUriString(), 
	            HttpMethod.GET, 
	            entity, 
	            List.class);
	    
	    Assert.assertEquals(true, response.getBody().size() > 0 );
	}
	
	@Test()
	void testfindEmployeesbyDivision() throws Exception {
		 RestTemplate restTemplate = new RestTemplate();
			final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee/";
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
		            .queryParam("divisionID", Long.valueOf(1));

		    HttpEntity<?> entity = new HttpEntity<>(headers);

		    HttpEntity<List> response = restTemplate.exchange(
		            builder.toUriString(), 
		            HttpMethod.GET, 
		            entity, 
		            List.class);
		    
		    Assert.assertEquals(true, response.getBody().size() > 0 );
	}

	 
	
		@Test()
		void testgetEmpStats() throws Exception {

			 RestTemplate restTemplate = new RestTemplate();
				final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee/employeeStatsByManager/";
				
			    HttpHeaders headers = new HttpHeaders();
			    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
			          

			    HttpEntity<?> entity = new HttpEntity<>(headers);

			    HttpEntity<List> response = restTemplate.exchange(
			            builder.toUriString(), 
			            HttpMethod.GET, 
			            entity, 
			            List.class);
			    
			    Assert.assertEquals(true, response.getBody().size() > 0 );
		
		}
	
		@Test()
		void testgetManagersInDiv() throws Exception {

			 RestTemplate restTemplate = new RestTemplate();
				final String baseUrl = "http://localhost:" + randomServerPort + "/api/employee/managerStatsByDivison/";
				
			    HttpHeaders headers = new HttpHeaders();
			    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("divisionID", Long.valueOf(1));;
			          

			    HttpEntity<?> entity = new HttpEntity<>(headers);

			    HttpEntity<List> response = restTemplate.exchange(
			            builder.toUriString(), 
			            HttpMethod.GET, 
			            entity, 
			            List.class);
			    
			    Assert.assertEquals(true, response.getBody().size() > 0 );
		
		}
		
		@Test()
		void testgetAllQuotes() throws Exception {

			 RestTemplate restTemplate = new RestTemplate();
				final String baseUrl = "http://localhost:" + randomServerPort + "/api/pullQuotes";
				
			    HttpHeaders headers = new HttpHeaders();
			    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
			          

			    HttpEntity<?> entity = new HttpEntity<>(headers);

			    HttpEntity<List> response = restTemplate.exchange(
			            builder.toUriString(), 
			            HttpMethod.GET, 
			            entity, 
			            List.class);
			    
			    Assert.assertEquals(true, response.getBody().size() > 0 );
		
		}

	/*
	 
	 * 
	 * @ApiOperation(value = "Employees Count under Each division")
	 * 
	 * @GetMapping("/employee/employeeStatsByDivison") public
	 * ResponseEntity<List<Object[]>> getEmployeesInDiv(){ return
	 * ResponseEntity.ok(divisionService.findEmployeeStatsByDivision());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @ApiOperation(value = "Pull all the availiable Quotes from Public API")
	 * 
	 * @GetMapping("/pullQuotes") public ResponseEntity<List<Quote>> getAllQuotes(){
	 * List<Quote> convertedlQuotes = null; try { String allQuotes =
	 * restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/",
	 * String.class); convertedlQuotes = mapper.readValue(allQuotes, new
	 * TypeReference<List<Quote>>(){});
	 * divisionService.saveAllquotes(convertedlQuotes); return
	 * ResponseEntity.ok(convertedlQuotes); } catch (JsonProcessingException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); return
	 * (ResponseEntity<List<Quote>>) ResponseEntity.notFound(); } }
	 * 
	 * @GetMapping("/quotes/") public ResponseEntity<Value> findQuoteBuID(
	 * 
	 * @ApiParam(value = "Any Quote ID to Search", required = true)
	 * 
	 * @RequestParam Long quoteID ) { Value result =
	 * divisionService.findQuoteByID(quoteID); return ResponseEntity.ok(result); }
	 * 
	 */
		
}
