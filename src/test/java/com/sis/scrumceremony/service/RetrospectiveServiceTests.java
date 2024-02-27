package com.sis.scrumceremony.service;


import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sis.scrumceremony.entity.Retrospective;
import com.sis.scrumceremony.repository.RetrospectiveRepository;
import com.sis.scrumceremony.serviceimpl.RetrospectiveServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RetrospectiveServiceTests {

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @InjectMocks
    private RetrospectiveServiceImpl retrospectiveService;

    @Test
    public void RetrospectiveService_Retrospective_ReturnsRetrospectiveDto() {
       Retrospective retrospective = new Retrospective();
    	retrospective.setName("Retrospective1");
    	retrospective.setSummary("Post release retrospective");
    	retrospective.setParticipants("Viktor");
        when(retrospectiveRepository.save(Mockito.any(Retrospective.class))).thenReturn(retrospective);
    	Retrospective savedRetrospective = retrospectiveService.saveRetrospective(retrospective);
        Assertions.assertThat(savedRetrospective).isNotNull();
        
       
    }

    
	@Test
	public void PokemonService_FindById_ReturnPokemonDto() {
		int retrospectiveId = 2;
		Retrospective retrospective = new Retrospective();
		retrospective.setRetrospectiveId(retrospectiveId);
		retrospective.setName("Retrospective2");
		retrospective.setSummary("Post release retrospective");
		retrospective.setParticipants("Viktor");
		when(retrospectiveRepository.findByRetrospectiveId(retrospectiveId)).thenReturn(retrospective);
		Retrospective pokemonReturn = retrospectiveService.findByRetrospectiveId(retrospectiveId);
		Assertions.assertThat(pokemonReturn).isNotNull();
	}
 }
