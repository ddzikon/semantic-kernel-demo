package org.example.controller;

import org.example.ai.AudioChatService;
import org.example.ai.ChatService;
import org.example.audio.AudioFileResolver;
import org.example.db.Person;
import org.example.db.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Repository repository;

    @Autowired
    private AudioFileResolver audioFileResolver;

    @Autowired
    private ChatService chatService;

    @Autowired
    private AudioChatService audioChatService;

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

    @GetMapping("/ai/audio")
    public String askWithAudioFile(@RequestParam String audioFilePath) {
        return audioChatService.askGpt(audioFileResolver.getAudioFileHandler(audioFilePath));
    }
}
