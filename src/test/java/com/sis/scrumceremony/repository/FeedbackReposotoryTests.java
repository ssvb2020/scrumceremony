package com.sis.scrumceremony.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sis.scrumceremony.entity.Feedback;
import com.sis.scrumceremony.entity.Retrospective;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FeedbackReposotoryTests {
	
	
	    @Autowired
	    private FeedbackRepository feedbackRepository;
	    
	    @Autowired
	    private RetrospectiveRepository retrospectiveRepository;

	    @Test
	    public void FeedbackRepository_SaveAll_ReturnSavedFeedback() {

	        //Arrange
	    	Retrospective retrospective = new Retrospective();
	    	retrospective.setName("Retrospective1");
	    	retrospective.setSummary("Post release retrospective");
	    	retrospective.setParticipants("Viktor");
	    	//Act
	    	Retrospective savedRetrospective = retrospectiveRepository.save(retrospective);
	    	
	    	 Feedback feedbackSave = new Feedback();
			 feedbackSave.setFeedbackParticipantName("Gareth");
			 feedbackSave.setFeedbackType("Positive");
			 feedbackSave.setFeedbackComment("Sprint objective met");
			 feedbackSave.setRetrospective(savedRetrospective);
			 feedbackRepository.save(feedbackSave);
			 
			 
	        //Assert
	        Assertions.assertThat(feedbackSave).isNotNull();
	        System.out.println(feedbackSave.getFeedbackParticipantName());
	        Assertions.assertThat(feedbackSave.getFeedbackParticipantName().toString()).isEqualTo("Gareth");
	    }
}
