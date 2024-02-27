package com.sis.scrumceremony.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sis.scrumceremony.entity.Retrospective;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RetrospectiveReposotoryTests {
	
	
	 	@Autowired
	    private RetrospectiveRepository retrospectiveRepository;

	    @Test
	    public void RetrospectiveRepository_SaveAll_ReturnSavedRetrospective() {

	        //Arrange
	    	Retrospective retrospective = new Retrospective();
	    	retrospective.setName("Retrospective1");
	    	retrospective.setSummary("Post release retrospective");
	    	retrospective.setParticipants("Viktor");
	    	//Act
	    	Retrospective savedRetrospective = retrospectiveRepository.save(retrospective);
	        //Assert
	        Assertions.assertThat(savedRetrospective).isNotNull();
	        Assertions.assertThat(savedRetrospective.getRetrospectiveId()).isGreaterThan(0);
	    }
	
	    
	    @Test
	    public void RetrospectiveRepository_FindById_ReturnRetrospective() {
	    	//Arrange
	    	Retrospective retrospective = new Retrospective();
	    	retrospective.setName("Retrospective1");
	    	retrospective.setSummary("Post release retrospective");
	    	retrospective.setParticipants("Viktor");
	    	retrospectiveRepository.save(retrospective);
	    	Retrospective saveRetrospective = retrospectiveRepository.findByRetrospectiveId(retrospective.getRetrospectiveId());
	        Assertions.assertThat(saveRetrospective).isNotNull();
	    }

}
