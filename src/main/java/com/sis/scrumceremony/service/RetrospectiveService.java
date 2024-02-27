package com.sis.scrumceremony.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sis.scrumceremony.entity.Feedback;
import com.sis.scrumceremony.entity.Retrospective;

@Component
public interface RetrospectiveService {

    public Retrospective saveRetrospective(Retrospective book);

    public Retrospective findByRetrospectiveId(int retrospectiveId);
    
    public  Page<Retrospective> getRetrospectiveByPageAndSize(int page,int size);
    
    public List<Retrospective> findRetrospectiveByDate(String creationDate);
    
    public Feedback updateFeedbackDetails(int storyId,Feedback story);
    
    public Feedback addFeedbackDetails(int bookId,Feedback story);
    
}