package org.example.ai.functions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entities.Person;
import org.example.model.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class PersonFunctions {

    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class Lister implements Function<Lister.Request, Lister.Response> {

        public static String FUNCTION_NAME = "getAllPeople";
        public static String FUNCTION_DESCRIPTION = "Get all data for every person.";

        public record Request(String param) {}
        public record Response(List<Person> personList) {}

        private final PersonRepository personRepository;

        @Override
        public Response apply(Request request) {
            log.info("Finding all persons, received param: {}", request.param);

            List<Person> persons = personRepository.findAll();

            log.info("Found:\n{}", persons);

            return new Response(persons);
        }
    }

    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class Finder implements Function<Finder.Request, Finder.Response> {

        public static String FUNCTION_NAME = "findPersonByName";
        public static String FUNCTION_DESCRIPTION = "Get preferred weather for a person by person's full name.";

        public record Request(String name) {}
        public record Response(String name, String preferredWeather) {}

        private final PersonRepository personRepository;

        @Override
        public Response apply(Request request) {
            log.info("Looking for person's preferred weather for name: {}", request.name);

            Person person = personRepository.findByName(request.name);

            log.info("Found:\n{}", person);

            return new Response(person.getName(), person.getPreferredWeather());
        }
    }

    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class Adder implements Function<Adder.Request, Adder.Response> {

        public static String FUNCTION_NAME = "insertNewPerson";
        public static String FUNCTION_DESCRIPTION = "Insert new person into the database.";

        public record Request(String name, String preferredWeather) {}
        public record Response(Integer id, String name, String preferredWeather) {}

        private final PersonRepository personRepository;

        @Override
        public Response apply(Request request) {
            log.info("Adding new person with name: {}, preferredWeather: {}", request.name, request.preferredWeather);

            final var newPerson = Person.builder().name(request.name).preferredWeather(request.preferredWeather).build();
            final var addedPerson = personRepository.save(newPerson);

            log.info("Saved person:\n{}", addedPerson);

            return new Response(addedPerson.getId(), addedPerson.getName(), addedPerson.getPreferredWeather());
        }
    }
}
