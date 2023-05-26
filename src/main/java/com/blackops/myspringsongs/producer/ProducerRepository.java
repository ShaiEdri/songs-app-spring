package com.blackops.myspringsongs.producer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    List<Producer> findByLastnameAndFirstnameAllIgnoreCase(String lastName, String firstName);
}
