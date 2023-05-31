package com.blackops.myspringsongs.shared;

import com.blackops.myspringsongs.producer.Producer;
import com.blackops.myspringsongs.producer.ProducerRepository;
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
    private final ProducerRepository producerRepository;

    public LoadData(SingerRepository singerRepository, SongRepository songRepository, ProducerRepository producerRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
        this.producerRepository = producerRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //saveData();
    }
    private void saveData() {
        Producer producer1 = Producer.builder().firstName("Nile")
                .lastName("Rogers")
                .city("New-york")
                .address("Azza")
                .state("Israel")
                .build();

        Singer singer1 = Singer.builder().firstName("Bob")
                .lastName("Dylan")
                .city("Migdal")
                .address("Azza")
                .state("Israel")
                .build();

        Singer singer2 = new Singer();
        singer2.setFirstName("bob");
        singer2.setLastName("Marley");


        Singer singer3 = new Singer();
        singer3.setFirstName("Josh");
        singer3.setLastName("Dylan");


        // song1
        Song song1 = new Song();
        song1.setName("the dylan song");
        song1.setLength(2.3);

        song1.getSingers().add(singer1);
        song1.getSingers().add(singer3);
        song1.setProducer(producer1);

        singer1.getSongs().add(song1);
        singer3.getSongs().add(song1);
        producer1.getSongs().add(song1);

        singerRepository.save(singer1);
        singerRepository.save(singer3);
        songRepository.save(song1);
        producerRepository.save(producer1);
        // song1

        Song song2 = new Song();
        song2.setName("Big brown eyes");
        song2.setLength(2.3);
        song2.getSingers().add(singer2);
        singer2.getSongs().add(song2);
        singerRepository.save(singer2);
        songRepository.save(song2);
    }
}
