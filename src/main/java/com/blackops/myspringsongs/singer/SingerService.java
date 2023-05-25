package com.blackops.myspringsongs.singer;

import java.util.List;
import java.util.Set;

public interface SingerService {
    List<Singer> getSingers();
    void deleteById(Long id);
    List<Singer> findByLastName(String lastName);
}
