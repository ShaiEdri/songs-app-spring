package com.blackops.myspringsongs.singer;

import java.util.List;
import java.util.Set;

public interface SingerService {
    List<Singer> getSingers();
    void deleteById();
    Set<Singer> findByLastName(String lastName);
}
