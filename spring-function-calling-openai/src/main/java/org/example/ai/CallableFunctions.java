package org.example.ai;

import org.example.db.Person;
import org.example.db.Repository;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;

//@Configuration
public class CallableFunctions {

//    @Autowired
    private Repository repository;

//    @Description("Return unchanged input")
    public Function<String, String> callableFunction() {
        return a-> a;
    }

//    @Bean
    public FunctionCallback getAllPersons() {
        return FunctionCallbackWrapper.builder(__ -> repository.findAll())
                .withName("getAllPersons")
                .withDescription("Get all persons in local database")
                .build();
    }
}
