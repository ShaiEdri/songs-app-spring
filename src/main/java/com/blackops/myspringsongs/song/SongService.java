package com.blackops.myspringsongs.song;

import java.util.List;

public interface SongService {
    List<Song> getSongs();
    void deleteById();
}
