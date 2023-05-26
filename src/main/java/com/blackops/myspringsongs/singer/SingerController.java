package com.blackops.myspringsongs.singer;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {
    private final ApplicationContext applicationContext;
    private final SingerService singerService;
    public SingerController(ApplicationContext applicationContext, SingerService singerService) {
        this.applicationContext = applicationContext;
        this.singerService = singerService;
    }

    @GetMapping({"", "/"})
    public List<Singer> getSingers(){
        return singerService.getSingers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        singerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
