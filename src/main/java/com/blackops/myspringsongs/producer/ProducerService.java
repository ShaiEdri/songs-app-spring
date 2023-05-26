package com.blackops.myspringsongs.producer;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProducerService {
    List<Producer> getProducers();
    Optional<Producer> findById(Long id);
    void deleteById(Long id );
    Producer save(Producer producer);
    int updateProducer(Long id, Producer producer);
    List<Producer> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
}
