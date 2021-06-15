package com.rochdi.ppmtool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rochdi.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findByProjectIdentifier(String projectIdentifier);
	
}
