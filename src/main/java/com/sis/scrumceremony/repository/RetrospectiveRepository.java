package com.sis.scrumceremony.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sis.scrumceremony.entity.Retrospective;

@Repository
public interface RetrospectiveRepository extends JpaRepository<Retrospective, String> {

    public Retrospective findByRetrospectiveId(int retrospectiveId);
    
    public List<Retrospective> findAllByCreationDate(Date creationDate);
    

}