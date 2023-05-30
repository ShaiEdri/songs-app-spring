package com.blackops.myspringsongs.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProducerController.class)
class ProducerControllerTestIT {
    @MockBean
    private ProducerService producerService;

    @Autowired
    private MockMvc mockMvc;

    Producer producer1;
    Producer producer2;
    private final Long ID = 15L;
    private final String FIRST_NAME = "Shai";
    private final String LAST_NAME = "Edri";
    private final String ADDRESS = "rose";
    private final String CITY = "telaviv";
    private final String STATE = "Israel";
    @BeforeEach
    void setUp() {
        producer1 = Producer.builder().firstName(FIRST_NAME).lastName(LAST_NAME).address(ADDRESS)
                .city(CITY).state(STATE).build();
        producer2 = Producer.builder().firstName(FIRST_NAME).lastName(LAST_NAME).address(ADDRESS)
                .city(CITY).state(STATE).build();
    }

    @Test
    void getProducers() throws Exception {
        // given
        List<Producer> mockProducers = new ArrayList<>();
        mockProducers.add(producer1);
        mockProducers.add(producer2);
        // when
        when(producerService.getProducers()).thenReturn(mockProducers);
        // then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/producer/"))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        List<Producer> producers = new ObjectMapper().readValue(responseContent,
                new TypeReference<List<Producer>>() {});
        assertEquals(producers.size(), 2);
        //assertNotEquals(producers.size(), 2);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 2L;
        doNothing().when(producerService).deleteById(anyLong());
        mockMvc.perform(delete("/producer/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        verify(producerService, times(1)).deleteById(id);
    }

    @Test
    void getProducerById() throws Exception {
        Long id = 2L;
        Optional<Producer> producerGetById = Optional.of(producer1);
        // when
        when(producerService.findById(anyLong())).thenReturn(producerGetById);
        MvcResult mvcResult = mockMvc.perform(get("/producer/" + id))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Producer found = new ObjectMapper().readValue(content,
                new TypeReference<Producer>() {});

        verify(producerService, times(1)).findById(id);
    }
//
//    @Test
//    void findByLastNameAndFirstNameAllIgnoreCase() {
//        producerService.save(producer1);
//        List<Producer> producersFound = producerController.findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME.toLowerCase(), FIRST_NAME.toUpperCase()).getBody();
//        assertEquals(producersFound.size(), 1);
//    }
//
    @Test
    void addProducer() throws Exception {
        // given
        // when
        when(producerService.save(any())).thenReturn(producer1);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestContent = objectMapper.writeValueAsString(producer1);
        //then
        MvcResult result = mockMvc.perform(post("/producer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        Producer savedProducer = objectMapper.readValue(responseContent,
                new TypeReference<Producer>() {});
        assertNotNull(savedProducer);
        assertEquals(savedProducer, producer1);
    }
//
//    @Test
//    void updateProducerByIdLoadObject() {
//        producerService.save(producer1);
//        List<Producer> producers = producerService.findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME, FIRST_NAME);
//        Long id = producers.get(producers.size()-1).getId();
//        producer1.setLastName("ForTest");
//        Producer producerUpdated = producerController.updateProducerByIdLoadObject(id, producer1).getBody();
//        assertTrue(producerUpdated.getLastName().equals("ForTest"));
//    }
//
//    @Test
//    void updateProducerByIdNoLoadObject() {
//        producerService.save(producer1);
//        producer1.setLastName("ForTest");
//        HttpStatus updated = producerController.updateProducerByIdNoLoadObject(2L, producer1).getBody();
//        assertEquals(updated, HttpStatus.OK);
//    }
}