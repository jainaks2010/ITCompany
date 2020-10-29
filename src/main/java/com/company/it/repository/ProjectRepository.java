package com.company.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.it.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
