package com.sis.scrumceremony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sis.scrumceremony.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {

	 public Feedback findByFeedbackId(int feedbakId);
	 
}