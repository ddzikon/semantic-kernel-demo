package org.example.agent;

import com.google.inject.Inject;
import com.microsoft.semantickernel.semanticfunctions.annotations.DefineKernelFunction;
import com.microsoft.semantickernel.semanticfunctions.annotations.KernelFunctionParameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entities.CityHotel;
import org.example.model.repositories.CityHotelRepository;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AccommodationPlugin {

    private final CityHotelRepository cityHotelRepository;

    @DefineKernelFunction(
            name = "findHotelInCity",
            description = "Find a hotel in city.",
            returnType = "string",
            returnDescription = "Name of a hotel in the city."
    )
    public String findHotelInCity(
            @KernelFunctionParameter(name = "cityName", description = "Name of a city to look for a hotel in.") String cityName
    ) {
        log.info("findHotelInCity called with cityName: {}", cityName);
        return cityHotelRepository.findByCityName(cityName).stream()
                .map(CityHotel::getHotelName)
                .collect(Collectors.joining(","));
    }

    @DefineKernelFunction(
            name = "getHotelEmailAddress",
            description = "Get email address of a hotel.",
            returnType = "string",
            returnDescription = "Email address of a hotel."
    )
    public String getHotelEmailAddress(
            @KernelFunctionParameter(name = "hotelName", description = "Name of a hotel to get its email address.") String hotelName
    ) {
        log.info("getHotelEmailAddress called with hotelName: {}", hotelName);
        return cityHotelRepository.findByHotelName(hotelName).stream()
                .findFirst().orElseThrow(() -> {
                    final var errorMessage = String.format("Multiple hotels found for hotel name: %s", hotelName);
                    return new RuntimeException(errorMessage);
                }).getEmail();
    }

    @DefineKernelFunction(
            name = "sendEmail",
            description = "Send an email to a given email address.",
            returnType = "boolean",
            returnDescription = "Confirmation whether email was sent successfully."
    )
    public boolean sendEmail(
            @KernelFunctionParameter(name = "email", description = "Email address.") String email,
            @KernelFunctionParameter(name = "message", description = "Email's content.") String message
    ) {
        final boolean emailExists = cityHotelRepository.findAll().stream()
                .map(CityHotel::getEmail)
                .anyMatch(email::equals);

        log.info("sendEmail called with email: {}\nmessage: {}\nemailExists: {}", email, message, emailExists);

        return emailExists;
    }
}
