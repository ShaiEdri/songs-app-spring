package com.blackops.myspringsongs.singer;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/singers")
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

}
