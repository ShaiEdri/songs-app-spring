package com.blackops.myspringsongs.song;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping({"", "/"})
    public List<Song> getSongs(){
        return songService.getSongs();
    }

    @GetMapping("/length")
    public List<Song> findByLengthGreaterThan(
            @RequestParam Double length){
        return songService.findByLengthGreaterThan(length);
    }
//    @DeleteMapping("/{id}")
//    void deleteById(@PathVariable Long id){
//        songService.deleteById(id);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        songService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
