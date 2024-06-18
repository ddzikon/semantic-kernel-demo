package org.example.app;

import com.azure.core.annotation.QueryParam;
import org.example.agent.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Repository repository;

    @Autowired
    private Agent agent;

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello there!";
    }

    @GetMapping(path = "/person")
    public String getPersonInfo(@QueryParam("name") String name) {
        return repository.findByName(name).getInfo();
    }

    @PostMapping(path = "/askGpt")
    public String askGpt(@RequestBody String question) {
        return agent.askGpt(question);
    }
}
