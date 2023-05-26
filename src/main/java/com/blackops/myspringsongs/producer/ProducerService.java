package com.blackops.myspringsongs.producer;
import java.util.List;

public interface ProducerService {
    List<Producer> getSongs();
    void deleteById(Long id);
    List<Producer> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
}
