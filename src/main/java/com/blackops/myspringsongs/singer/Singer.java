package com.blackops.myspringsongs.singer;

import com.blackops.myspringsongs.shared.Person;
import com.blackops.myspringsongs.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Singer extends Person {
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "singer_song", joinColumns = @JoinColumn(name = "singer_id"),
//            inverseJoinColumns = @JoinColumn(name = "song_id"))
//    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "singers")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Song> songs = new ArrayList<>();

    public Singer(){}
    @Builder
    public Singer(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
