package dev.guisandroni.store.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productsDevTools")
@AllArgsConstructor
@Log4j2
public class DevToolsProduct {
    @GetMapping("Test Dev Tools")
    void test(){
       log.info("Test");
    }
}
