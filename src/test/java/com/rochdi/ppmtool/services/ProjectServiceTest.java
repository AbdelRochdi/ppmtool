package com.rochdi.ppmtool.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.rochdi.ppmtool.domain.Project;
import com.rochdi.ppmtool.exceptions.ProjectIdException;
import com.rochdi.ppmtool.repositories.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepository;
	private ProjectService underTest;

	@BeforeEach
	void setUp() {
		underTest = new ProjectService(projectRepository);
	}

	@Test
	void testSaveOrUpdateProject() {
		Project project = new Project("test Project", "TEST", "this is a test project");

		underTest.saveOrUpdateProject(project);

		ArgumentCaptor<Project> projectArgumentCaptor = ArgumentCaptor.forClass(Project.class);

		verify(projectRepository).save(projectArgumentCaptor.capture());

		Project capturedProject = projectArgumentCaptor.getValue();

		assertThat(capturedProject).isEqualTo(project);
	}

	@Test
	void willThrowWhenIdentifierIsTaken() {
		Project project = new Project("test Project", "TEST", "this is a test project");
		
		BDDMockito.given(projectRepository);

		assertThatThrownBy(() -> underTest.saveOrUpdateProject(project)).isInstanceOf(ProjectIdException.class)
				.hasMessageContaining(
						"Project id: " + project.getProjectIdentifier().toUpperCase() + " already exists ");
	}

	@Test
	@Disabled
	void testFindProjectById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAllProjects() {
		underTest.findAllProjects();

		verify(projectRepository).findAll();
	}

	@Test
	@Disabled
	void testDeleteProjectByIdentifier() {
		fail("Not yet implemented");
	}

}
