package org.example.ai.functions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entities.CityHotel;
import org.example.model.repositories.CityHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
public class AccommodationFunctions {

    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class HotelFinder implements Function<HotelFinder.Request, HotelFinder.Response> {

        public static String FUNCTION_NAME = "findHotelInCity";
        public static String FUNCTION_DESCRIPTION = "Find a hotel in city.";

        public record Request(String cityName) {}
        public record Response(String hotelName) {}

        private final CityHotelRepository cityHotelRepository;

        @Override
        public Response apply(Request request) {
            log.info("Looking for CityHotel by name: {}", request.cityName);
            CityHotel cityHotel = cityHotelRepository.findByCityName(request.cityName);
            log.info("Found CityHotel: {}", cityHotel);
            return new Response(cityHotel.getHotelName());
        }
    }

    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class EmailFinder implements Function<EmailFinder.Request, EmailFinder.Response> {

        public static String FUNCTION_NAME = "getHotelEmailAddress";
        public static String FUNCTION_DESCRIPTION = "Get email address of a hotel.";

        public record Request(String hotelName) {}
        public record Response(String email) {}

        private final CityHotelRepository cityHotelRepository;

        @Override
        public Response apply(Request request) {
            log.info("Looking for email to hotel: {}", request.hotelName);
            CityHotel cityHotel = cityHotelRepository.findByHotelName(request.hotelName);
            log.info("Found CityHotel: {}", cityHotel);
            return new Response(cityHotel.getEmail());
        }
    }

    @Component
    @RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public static class EmailSender implements Function<EmailSender.Request, EmailSender.Response> {

        public static String FUNCTION_NAME = "sendEmail";
        public static String FUNCTION_DESCRIPTION = "Send an email to a given email address.";

        public record Request(String emailAddress, String message) {}
        public record Response(boolean emailSent) {}

        private final CityHotelRepository cityHotelRepository;

        @Override
        public Response apply(Request request) {
            boolean emailExists = cityHotelRepository.existsByEmail(request.emailAddress);
            log.info("Sending email to address: {}, email exists: {}, content:\n{}", request.emailAddress, emailExists, request.message);
            return new Response(emailExists);
        }
    }
}
