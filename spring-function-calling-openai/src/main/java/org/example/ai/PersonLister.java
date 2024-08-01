package org.example.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.db.Person;
import org.example.db.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonLister implements Function<PersonLister.Request, PersonLister.Response> {

    public record Request(String param) {}
    public record Response(List<Person> personList) {}

    private final Repository personRepository;

    @Override
    public Response apply(Request request) {
        log.info("Finding all persons, received param: {}", request.param);

        List<Person> persons = personRepository.findAll();

        log.info("Found:\n{}", persons);

        return new Response(persons);
    }

}
