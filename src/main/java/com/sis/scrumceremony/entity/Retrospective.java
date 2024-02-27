package com.sis.scrumceremony.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "retrospective")
public class Retrospective {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int retrospectiveId;

    
    @Column(name = "name")
    private String name;
    
    @Column(name = "summary")
    private String summary;

    @PrePersist
    protected void onCreate() {
    	creationDate = new Date();
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "retrospective", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("retrospective")
    private List<Feedback> feedbackList = new ArrayList<>();
    
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    
    @Column(name = "participants")
    private String participants ;
    
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	// new
	public int getRetrospectiveId() {
		return retrospectiveId;
	}

	public void setRetrospectiveId(int retrospectiveId) {
		this.retrospectiveId = retrospectiveId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}