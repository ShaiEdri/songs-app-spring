package com.blackops.myspringsongs.song;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        //songRepository.findAll().forEach(e-> songs.add(e));
        return songRepository.findAll();
    }

    @Override
    public void deleteById() {

    }
}
