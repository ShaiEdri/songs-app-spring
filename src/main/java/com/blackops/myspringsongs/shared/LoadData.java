package com.blackops.myspringsongs.shared;

import com.blackops.myspringsongs.singer.Singer;
import com.blackops.myspringsongs.singer.SingerRepository;
import com.blackops.myspringsongs.song.Song;
import com.blackops.myspringsongs.song.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {
    private final SingerRepository singerRepository;
    private final SongRepository songRepository;

    public LoadData(SingerRepository singerRepository, SongRepository songRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Singer singer1 = new Singer();
        singer1.setFirstName("bob");
        singer1.setLastName("Dylan");

        Singer singer2 = new Singer();
        singer2.setFirstName("bob");
        singer2.setLastName("Marley");


        Singer singer3 = new Singer();
        singer3.setFirstName("Josh");
        singer3.setLastName("Dylan");


        Song song1 = new Song();
        song1.setName("the dylan song");
        song1.setLength(2.3);
        song1.getSingers().add(singer1);
        //song1.getSingers().add(singer3);
        singer1.getSongs().add(song1);
        //singer3.getSongs().add(song1);
        singerRepository.save(singer1);
        songRepository.save(song1);
        //singerRepository.save(singer3);


        Song song2 = new Song();
        song2.setName("Big brown eyes");
        song2.setLength(2.3);
        song2.getSingers().add(singer2);
        singer2.getSongs().add(song2);
        singerRepository.save(singer2);
        songRepository.save(song2);
    }
}
