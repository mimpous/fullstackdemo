package com.evaluation.backend.model.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluation.backend.model.persistence.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>
{
	  List<Tutorial> findByPublished(boolean published);
	  List<Tutorial> findByTitleContaining(String title);
}
