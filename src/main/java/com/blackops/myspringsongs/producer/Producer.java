package com.blackops.myspringsongs.producer;

import com.blackops.myspringsongs.shared.Person;
import com.blackops.myspringsongs.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Producer extends Person {
    @OneToMany
    @JoinColumn(name = "producer_id") //instead of table, try without it and see
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Song> songs = new ArrayList<>();

    public Producer() {}
    @Builder
    public Producer(Long id, String firstName
            , String lastName, String address, String city, String state) {
        super(id, firstName, lastName, address, city, state);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
