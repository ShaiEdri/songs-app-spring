package com.blackops.myspringsongs.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProducerControllerTest {
    @Autowired
    private ProducerService producerService;
    private ProducerController producerController;
    Producer testedProducer;
    private final Long ID = 15L;
    private final String FIRST_NAME = "Shai";
    private final String LAST_NAME = "Edri";
    private final String ADDRESS = "rose";
    private final String CITY = "telaviv";
    private final String STATE = "Israel";
    @BeforeEach
    void setUp() {
        producerController = new ProducerController(producerService);
        testedProducer = Producer.builder().firstName(FIRST_NAME).lastName(LAST_NAME).address(ADDRESS)
                .city(CITY).state(STATE).build();
    }

//    @Test
//    void getProducers() {
//        producerService.save(producer1);
//        //producerService.getProducers().stream().forEach(System.out::println);
//        List<Producer> producers = producerController.getProducers();
//        assertTrue(producers.size() > 0);
//    }

    @Test
    void deleteById() {
        producerService.save(testedProducer);
        //producerService.getProducers().stream().forEach(System.out::println);
        producerController.deleteById(2L);
        Optional<Producer> producerOptional = producerService.findById(2L);
        assertTrue(producerOptional.isEmpty());
    }

    @Test
    void getProducerById() {
        producerService.save(testedProducer);
        Producer producer = producerController.getProducerById(2L).getBody();
        assertNotNull(producer);
    }

    @Test
    void findByLastNameAndFirstNameAllIgnoreCase() {
        producerService.save(testedProducer);
        List<Producer> producersFound = producerController.findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME.toLowerCase(), FIRST_NAME.toUpperCase()).getBody();
        assertEquals(producersFound.size(), 1);
    }

    @Test
    void addProducer() {
        producerController.addProducer(testedProducer);
        //List<Producer> producers = producerService.getProducers();
        List<Producer> producers = producerService
                .findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME, FIRST_NAME);
        assertEquals(producers.size(), 1);
    }

    @Test
    void updateProducerByIdLoadObject() {
        producerService.save(testedProducer);
        List<Producer> producers = producerService.findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME, FIRST_NAME);
        Long id = producers.get(producers.size()-1).getId();
        testedProducer.setLastName("ForTest");
        Producer producerUpdated = producerController.updateProducerByIdLoadObject(id, testedProducer).getBody();
        assertTrue(producerUpdated.getLastName().equals("ForTest"));
    }

    @Test
    void updateProducerByIdNoLoadObject() {
        producerService.save(testedProducer);
        List<Producer> allproducers = producerService.getProducers();
        List<Producer> producers = producerService.findByLastNameAndFirstNameAllIgnoreCase(LAST_NAME, FIRST_NAME);
        Long id = producers.get(producers.size()-1).getId();
        Producer producerToUpdate = new Producer(testedProducer);
        producerToUpdate.setLastName("ForTest");
        ResponseEntity<HttpStatus> updated = producerController.updateProducerByIdNoLoadObject(id, producerToUpdate);
        assertEquals(updated.getStatusCode(), HttpStatus.OK);
        //assertNotEquals(updated.getStatusCode(), HttpStatus.OK);
    }
}