package com.blackops.myspringsongs.singer;

import com.blackops.myspringsongs.MyspringSongsApplication;
import com.blackops.myspringsongs.song.Song;
import com.blackops.myspringsongs.song.SongService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = MyspringSongsApplication.class)
class SingerServiceImplTest {
    @Autowired
    SingerRepository singerRepository;
    private SingerService singerService;
    Singer singer;
    static Singer user1;
    static Singer user2;
    @BeforeAll
    static void startUp(){
        user1 = new Singer(1L,"John", "Doe", "rose 5", "Dimone", "Israel");
        user2 = new Singer(2L, "Jane", "Smith", "black 5", "Jerikho", "Israel");
    }
    @BeforeEach
    void setUp() {
        singer = Singer.builder().firstName("shai").build();
        //MockitoAnnotations.openMocks(this);
        singerService = new SingerServiceImpl(singerRepository);
    }

    @Test
    void findByLastName() {
        singerService.saveSingers(List.of(user1, user2));
        List<Singer>singers = singerService.findByLastName("Doe");
        assertEquals(1, singers.size());
        assertEquals("Doe", singers.get(0).getLastName());
    }

    @Test
    void getSingers() {
        singerRepository.saveAll(List.of(user1, user2));
        List<Singer> singers = singerService.getSingers();
        assertTrue(singers.size() > 1);
    }


    @Test
    void deleteById() {
        singerRepository.saveAll(List.of(user1, user2));
        singerService.deleteById(1L);
        List<Singer> singers = singerService.getSingers();
        assertTrue(singers.size() == 1);
    }
}