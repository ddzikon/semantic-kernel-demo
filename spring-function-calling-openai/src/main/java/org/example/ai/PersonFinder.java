package org.example.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.db.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonFinder implements Function<PersonFinder.Request, PersonFinder.Response> {

    public record Request(String name) {}
    public record Response(List<Pair<String, String>> nameToInfo) {}

    private final Repository personRepository;

    @Override
    public Response apply(Request request) {
        log.info("Looking for person info for name: {}", request.name);

        List<Pair<String, String>> nameToInfo = personRepository.findByName(request.name).stream()
                .map(person -> Pair.of(person.getName(), person.getInfo()))
                .toList();

        log.info("Found:\n{}", nameToInfo);

        return new Response(nameToInfo);
    }

}
