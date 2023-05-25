package com.blackops.myspringsongs.song;

import com.blackops.myspringsongs.MyspringSongsApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = MyspringSongsApplication.class)
class SongServiceImplTest {
    @Autowired
    private SongRepository songRepository;
    private SongService songService;

    static Song song1;
    static Song song2;
    static final Double LENGTH = 3D;
    @BeforeAll
    static void startUp() {
        song1 = Song.builder().id(4L).name("best").length(LENGTH).build();
        song2 = Song.builder().id(5L).name("best2").length(1D).build();
    }
    @BeforeEach
    void setUp() {
        songService = new SongServiceImpl(songRepository);
    }
    @Test
    void getSongs() {
    }

    @Test
    void findByLengthGreaterThan() {
        songRepository.saveAll(List.of(song1, song2));
        List<Song> songs = songRepository.findByLengthGreaterThan(2D);
        assertTrue(songs.size() > 0);
        assertEquals(songs.get(songs.size()-1).getLength(), LENGTH);
    }

    @Test
    void deleteById() {
    }
}