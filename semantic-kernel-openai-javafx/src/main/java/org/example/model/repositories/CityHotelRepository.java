package org.example.model.repositories;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.example.model.entities.CityHotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CityHotelRepository {

    private final SessionFactory sessionFactory;

    public List<CityHotel> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CityHotel order by id", CityHotel.class).list();
        }
    }

    public List<CityHotel> findByCityName(String cityName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CityHotel where cityName = :cityName", CityHotel.class)
                    .setParameter("cityName", cityName)
                    .list();
        }
    }

    public List<CityHotel> findByHotelName(String hotelName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CityHotel where hotelName = :hotelName", CityHotel.class)
                    .setParameter("hotelName", hotelName)
                    .list();
        }
    }
}
