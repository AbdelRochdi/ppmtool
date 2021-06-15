package com.rochdi.ppmtool.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.rochdi.ppmtool.domain.Project;

@DataJpaTest
class ProjectRepositoryTest {
	
	@Autowired
	private ProjectRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void testFindByProjectIdentifier() {

		Project project = new Project("test Project", "TEST", "this is a test project");
		
		underTest.save(project);
		
		Project project2 = underTest.findByProjectIdentifier("TEST");
		
		assertThat(project2.getProjectName()).isEqualTo(project.getProjectName());
		
	}

}
