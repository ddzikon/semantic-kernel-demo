package org.example.agent;

import com.google.inject.Inject;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.PersonViewModel;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
class PersonPlugin {

    private final PersonViewModel personViewModel;

    @DefineKernelFunction(
            name = "getPersonInfo",
            description = "Get info for the person by person's name.",
            returnType = "string"
    )
    public String getPersonInfo(
            @KernelFunctionParameter(name = "name", description = "Name of a person.") String name
    ) {
        log.info("getPersonInfo called with argument: name = {}", name);
        return personViewModel.getPersonInfo(name);
    }

    @DefineKernelFunction(
            name = "getAllPeople",
            description = "Get all data for every person.",
            returnType = "string"
    )
    public String getAllPeople() {
        log.info("getAllPeople called");
        return personViewModel.getAllPeople();
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
        log.info("insertNewPerson called with arguments: name = {}, info = {}", name, info);
        return personViewModel.insertNewPerson(name, info);
    }
}
