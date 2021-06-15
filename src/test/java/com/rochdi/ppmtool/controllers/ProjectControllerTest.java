package com.rochdi.ppmtool.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rochdi.ppmtool.domain.Project;
import com.rochdi.ppmtool.services.MapValidationErrorService;
import com.rochdi.ppmtool.services.ProjectService;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ProjectService projectService;
	
	@MockBean
	private MapValidationErrorService mapValidationErrorService;

	@Test
	public void testProjectList() throws Exception {
		
		ArrayList<Project> projectList = new ArrayList<Project>();
		
		Project project = new Project("test Project", "TEST", "this is a test project");

		projectList.add(project);

		Mockito.when(projectService.findAllProjects()).thenReturn(projectList);
		
		MvcResult mvcResult = mockMvc.perform(get("/api/project/all")).andExpect(status().isOk()).andReturn();
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		
		String excpectedResponse = objectMapper.writeValueAsString(projectList);
		
		assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(excpectedResponse);
	
	}

	@Test
	@Disabled
	void testGetProjectById() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testGetAllProjects() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testDeleteProjectById() {
		fail("Not yet implemented");
	}

}
