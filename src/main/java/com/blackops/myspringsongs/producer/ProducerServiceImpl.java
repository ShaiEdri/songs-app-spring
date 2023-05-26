package com.blackops.myspringsongs.producer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public List<Producer> getSongs() {
        return producerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public List<Producer> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname) {
        return producerRepository.findByLastnameAndFirstnameAllIgnoreCase(
                firstname,lastname);
    }
}
