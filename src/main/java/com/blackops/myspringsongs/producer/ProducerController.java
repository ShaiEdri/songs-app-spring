package com.blackops.myspringsongs.producer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping({"", "/"})
    public List<Producer> getProducers(){
        return producerService.getProducers();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
            producerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getbyid")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id){
        Optional<Producer> producerOptional = producerService.findById(id);
        if (producerOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producerOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/getbyfullname")
    public ResponseEntity<List<Producer>> findByLastNameAndFirstNameAllIgnoreCase(@RequestParam String lastName, @RequestParam String firstName){
        List<Producer> producers = producerService.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
        if (producers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Producer> addProducer(@RequestBody Producer producer){
        return new ResponseEntity<>(producerService.save(producer), HttpStatus.OK);
    }
    @PostMapping("/update1/{id}")//Option1 - Load object, modify and save again
    public ResponseEntity<Producer> updateProducerByIdLoadObject(@PathVariable Long id, @RequestBody Producer producer){
        Optional<Producer> producerOptional = producerService.findById(id);
        if (producerOptional.isPresent()){
            Producer producerToUpdate = producerOptional.get();
            producerToUpdate.setFirstName(producer.getFirstName());
            producerToUpdate.setLastName(producer.getLastName());
            producerToUpdate.setAddress(producer.getAddress());
            producerToUpdate.setCity(producer.getCity());
            producerToUpdate.setState(producer.getState());
            producerToUpdate.setSongs(producer.getSongs());
            Producer savedProducer = producerService.save(producerToUpdate);
            return new ResponseEntity<>(savedProducer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/update2/{id}")//Option2 - Create a custom query, withoud Loading the object
    public ResponseEntity<HttpStatus> updateProducerByIdNoLoadObject(@PathVariable Long id, @RequestBody Producer producer){
        if (producerService.updateProducer(id, producer) == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



