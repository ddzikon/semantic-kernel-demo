package org.example.controller;

import org.example.ai.ChatService;
import org.example.ai.CurrentWeatherFunction;
import org.example.db.Person;
import org.example.db.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repository repository;

    @Autowired
    private ChatService chatService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello there!";
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    @PostMapping("/ai/chat")
    public String askGpt(@RequestBody String message) {
        return chatService.askGpt(message);
    }

    @GetMapping("/weather")
    public @ResponseBody CurrentWeatherFunction.Response getCurrentWeather(@RequestParam String location, @RequestParam CurrentWeatherFunction.Unit unit) {
        return new CurrentWeatherFunction().apply(new CurrentWeatherFunction.Request(location, unit));
    }
}
