package com.blackops.myspringsongs.singer;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SingerRepository extends JpaRepository<Singer, Long> {
    List<Singer> findAllByLastName(String lastName);
}
