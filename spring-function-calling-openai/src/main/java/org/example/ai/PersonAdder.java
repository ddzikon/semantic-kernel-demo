package org.example.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.db.Person;
import org.example.db.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonAdder implements Function<PersonAdder.Request, PersonAdder.Response> {

    public record Request(String name, String info) {}
    public record Response(Integer id, String name, String info) {}

    private final Repository personRepository;

    @Override
    public Response apply(Request request) {
        log.info("Adding new person with name: {}, info: {}", request.name, request.info);

        final var newPerson = Person.builder().name(request.name).info(request.info).build();
        final var addedPerson = personRepository.save(newPerson);

        log.info("Saved person:\n{}", addedPerson.toString());

        return new Response(addedPerson.getId(), addedPerson.getName(), addedPerson.getInfo());
    }

}
