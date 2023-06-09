package com.blackops.myspringsongs.singer;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SingerServiceImpl implements SingerService {
    private final SingerRepository singerRepository;

    public SingerServiceImpl(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
    public List<Singer> findByLastName(String lastName) {
        return singerRepository.findAllByLastName(lastName);
    }

    @Override
    public List<Singer> getSingers() {
        List<Singer> singers = new ArrayList<>();
        singerRepository.findAll().forEach(e-> singers.add(e));
        return singers;
    }

    @Override
    public List<Singer> saveSingers(List<Singer> singers) {
        return singerRepository.saveAll(singers);
    }

    @Override
    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }
}
