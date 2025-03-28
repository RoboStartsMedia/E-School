package ru.robo.eschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.robo.eschool.utils.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Creator: Robo_Start
//Creation date & time: 3/28/2025 10:34 PM
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        Config.getInstance().UpdateConfig();
        Config.getInstance().ResetDefault();
        SpringApplication.run(Application.class, "--server.port=" + Config.getInstance().port);
    }

    @GetMapping("/")
    public ModelAndView base(){
        if (!Files.exists(Path.of("data.sqlite"))){
            return new ModelAndView("redirect:firstSet");
        }
        return new ModelAndView("redirect:login");
    }

    @GetMapping("/firstSet")
    public ResponseEntity<String> firstSet(){
        try {
            return ResponseEntity.ok(Files.readString(Path.of("web", "firstSet.html")));
        } catch (IOException e) {
            Config.getInstance().ResetDefault();
            return firstSet();
        }
    }
    @PostMapping("/firstSet")
    public ModelAndView firstSetUpdate(@RequestBody String body){
        System.out.println(body);
        return new ModelAndView("redirect:login");
    }
}
