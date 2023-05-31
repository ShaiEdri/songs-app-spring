package com.blackops.myspringsongs.singer;

import com.blackops.myspringsongs.shared.Person;
import com.blackops.myspringsongs.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Singer extends Person {

    @ManyToMany(mappedBy = "singers", cascade = CascadeType.ALL)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Song> songs = new ArrayList<>();

    public Singer(){}
    @Builder
    public Singer(Long id, String firstName, String lastName, String address,
                  String city, String state) {
        super(id, firstName, lastName, address, city, state);
    }


    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
