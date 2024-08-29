package org.example.agent;

import com.google.inject.Inject;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.viewmodel.PersonViewModel;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PersonPlugin {

    private final PersonViewModel personViewModel;

    @DefineKernelFunction(
            name = "getPersonPreferredWeather",
            description = "Get preferred weather for a person by person's full name.",
            returnType = "string"
    )
    public String getPersonPreferredWeather(
            @KernelFunctionParameter(name = "fullName", description = "Full name of a person.") String fullName
    ) {
        log.info("getPersonPreferredWeather called with argument: fullName = {}", fullName);
        return personViewModel.getPersonPreferredWeather(fullName);
    }

    @DefineKernelFunction(
            name = "getPersonPreferredWeatherByFirstName",
            description = "Get preferred weather for a person searching the person by first name.",
            returnType = "string"
    )
    public String getPersonPreferredWeatherByFirstName(
            @KernelFunctionParameter(name = "firstName", description = "First name of a person") String firstName
    ) {
        log.info("getPersonPreferredWeatherByFirstName called with argument: firstName = {}", firstName);
        return personViewModel.getPersonPreferredWeatherByFirstName(firstName);
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
            @KernelFunctionParameter(name = "name", description = "Full name of a person.") String name,
            @KernelFunctionParameter(name = "preferredWeather", description = "Preferred weather of a person.") String preferredWeather
    ) {
        log.info("insertNewPerson called with arguments: name = {}, preferredWeather = {}", name, preferredWeather);
        return personViewModel.insertNewPerson(name, preferredWeather);
    }
}
