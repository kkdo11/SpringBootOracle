package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MapController {
    @GetMapping(value = "Map/map")
    public String map() throws Exception{
        log.info(this.getClass().getName() + "Map start!");
        log.info(this.getClass().getName() + "Map end!");
        return "/Map/map";
    }
}
