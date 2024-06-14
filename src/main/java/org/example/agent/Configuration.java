package org.example.agent;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Agent getAgent() {
        return new Agent();
    }

    @Bean
    public Plugin getPlugin() {
        return new Plugin();
    }
}
