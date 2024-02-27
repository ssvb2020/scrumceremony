package com.sis.scrumceremony.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sis.scrumceremony.entity.Retrospective;
import com.sis.scrumceremony.service.RetrospectiveService;

@WebMvcTest(controllers = RetrospectiveController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class RetrospectiveControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetrospectiveService retrospectiveService;

    @Autowired
    private ObjectMapper objectMapper;
    private Retrospective retrospective;

    @BeforeEach
    public void init() {

        Retrospective retrospective = new Retrospective();
        retrospective.setRetrospectiveId(1);
    	retrospective.setName("Retrospective1");
    	retrospective.setSummary("Post release retrospective");
    	retrospective.setParticipants("Viktor");
    }


    @Test
    public void PokemonController_PokemonDetail_ReturnPokemonDto() throws Exception {
        int retrospectiveId = 1;
        when(retrospectiveService.findByRetrospectiveId(retrospectiveId)).thenReturn(retrospective);
        ResultActions response = mockMvc.perform(get("/retrospective/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(retrospective)));
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    
  
}
