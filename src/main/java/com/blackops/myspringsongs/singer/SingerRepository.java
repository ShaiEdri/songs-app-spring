package com.blackops.myspringsongs.singer;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SingerRepository extends JpaRepository<Singer, Long> {
    Set<Singer> findAllByLastName(String lastName);
}
