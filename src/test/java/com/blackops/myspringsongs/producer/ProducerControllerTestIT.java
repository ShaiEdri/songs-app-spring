package com.blackops.myspringsongs.producer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        List<Producer> producers = new ObjectMapper().readValue(responseContent,
                new TypeReference<List<Producer>>() {});
        assertEquals(producers.size(), 2);
    }

//    @Test
//    void deleteById() {
//        producerService.save(producer1);
//        //producerService.getProducers().stream().forEach(System.out::println);
//        producerController.deleteById(2L);
//        Optional<Producer> producerOptional = producerService.findById(2L);
//        assertTrue(producerOptional.isEmpty());
//    }
//
//    @Test
//    void getProducerById() {
//        producerService.save(producer1);
//        Producer producer = producerController.getProducerById(2L).getBody();
//        assertNotNull(producer);
//    }
//
//    @Test
//    void findByLastNameAndFirstNameAllIgnoreCase() {
//        producerService.save(producer1);
//        List<Producer> producersFound = producerController.findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME.toLowerCase(), FIRST_NAME.toUpperCase()).getBody();
//        assertEquals(producersFound.size(), 1);
//    }
//
//    @Test
//    void addProducer() {
//        producerController.addProducer(producer1);
//        //List<Producer> producers = producerService.getProducers();
//        List<Producer> producers = producerService
//                .findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME, FIRST_NAME);
//        assertEquals(producers.size(), 1);
//    }
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