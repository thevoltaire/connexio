package com.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.service.DivisionServiceImpl;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {DivisionController.class, DivisionServiceImpl.class})
@WebMvcTest
class DivisionControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	void testGetAllinventory() throws Exception {
		String dow = "Test String";
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/inventory")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andReturn();

	        String resultDOW = result.getResponse().getContentAsString();
	        assertNotNull(resultDOW);
			assertEquals(dow, resultDOW);
	}

	/*
	 * @Test void testCreateDivisionInputForDivision() {
	 * fail("Not yet implemented"); }
	 * 
	 * @Test void testCreateDivisionInputForEmployee() {
	 * fail("Not yet implemented"); }
	 * 
	 * @Test void testFindEmployeesbyManager() { fail("Not yet implemented"); }
	 * 
	 * @Test void testFindEmployeesbyDivision() { fail("Not yet implemented"); }
	 * 
	 * @Test void testGetAllQuotes() { fail("Not yet implemented"); }
	 */

}
