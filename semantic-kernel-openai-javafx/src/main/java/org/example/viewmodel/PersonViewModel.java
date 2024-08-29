package org.example.viewmodel;

import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entities.Person;
import org.example.model.repositories.PersonRepository;

import java.util.stream.Collectors;

@Slf4j
public class PersonViewModel {

    @Getter
    private final ObservableList<Person> peopleStateProperty;

    private final PersonRepository personRepository;

    @Inject
    public PersonViewModel(PersonRepository personRepository) {
        this.personRepository = personRepository;

        final var initialPeople = personRepository.findAll();

        this.peopleStateProperty = FXCollections.observableArrayList(initialPeople);
    }

    public String getPersonPreferredWeather(String personName) {
        return personRepository.findByFullName(personName).stream()
                .map(person -> String.format("%s -> %s", person.getName(), person.getPreferredWeather()))
                .collect(Collectors.joining(";\n"));
    }

    public String getPersonPreferredWeatherByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName).stream()
                .map(person -> String.format("%s -> %s", person.getName(), person.getPreferredWeather()))
                .collect(Collectors.joining(";\n"));
    }

    public String getAllPeople() {
        return personRepository.findAll().stream().map(Person::toString).collect(Collectors.joining(", "));
    }

    public String insertNewPerson(String name, String preferredWeather) {
        final var newPerson = Person.builder().name(name).preferredWeather(preferredWeather).build();
        final var insertedPerson = personRepository.save(newPerson);

        peopleStateProperty.add(insertedPerson);

        return insertedPerson.toString();
    }
}
