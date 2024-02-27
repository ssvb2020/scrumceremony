package com.sis.scrumceremony.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackId;

    @Column(name = "feedback_participant_name")
    private String feedbackParticipantName ;
    
    @Column(name = "feedback_comment")
    private String feedbackComment ;
    
    @Column(name = "feedback_type")
    private String feedbackType;
    

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "retrospective_Id", referencedColumnName = "retrospectiveId")
    @JsonIgnoreProperties("feedbackList")
    private Retrospective retrospective;


	public int getFeedbackId() {
		return feedbackId;
	}


	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}


	public String getFeedbackParticipantName() {
		return feedbackParticipantName;
	}


	public void setFeedbackParticipantName(String feedbackParticipantName) {
		this.feedbackParticipantName = feedbackParticipantName;
	}


	public String getFeedbackComment() {
		return feedbackComment;
	}


	public void setFeedbackComment(String feedbackComment) {
		this.feedbackComment = feedbackComment;
	}


	public String getFeedbackType() {
		return feedbackType;
	}


	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}


	public Retrospective getRetrospective() {
		return retrospective;
	}


	public void setRetrospective(Retrospective retrospective) {
		this.retrospective = retrospective;
	}

}