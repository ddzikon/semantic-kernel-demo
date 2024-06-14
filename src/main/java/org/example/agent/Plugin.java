package org.example.agent;

import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import org.example.app.Person;
import org.example.app.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class Plugin {

    @Autowired
    private Repository repository;

    @DefineKernelFunction(
            name = "getPersonInfo",
            description = "Get info for the person by person's name.",
            returnType = "string")
    public String getPersonInfo(String name) {
        return repository.findByName(name).getInfo();
    }

    @DefineKernelFunction(
            name = "getAllPeople",
            description = "Get all data for every person.",
            returnType = "string"
    )
    public String getAllPeople() {
        return repository.findAll().stream().map(Person::toString).collect(Collectors.joining(", "));
    }

    @DefineKernelFunction(
            name = "insertPerson",
            description = "Insert new person into the database.",
            returnType = "string"
    )
    public String insertNewPerson(
            @KernelFunctionParameter(name = "name", description = "Name of a person.") String name,
            @KernelFunctionParameter(name = "info", description = "Info for a person.") String info
    ) {
        final var person = Person.builder().name(name).info(info).build();
        return repository.save(person).toString();
    }

}
