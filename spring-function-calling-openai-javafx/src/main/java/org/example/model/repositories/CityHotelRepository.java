package org.example.model.repositories;

import org.example.model.entities.CityHotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityHotelRepository extends JpaRepository<CityHotel, Integer> {

    CityHotel findByCityName(String cityName);

    CityHotel findByHotelName(String hotelName);

    boolean existsByEmail(String email);

}
