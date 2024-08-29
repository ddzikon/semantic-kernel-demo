package org.example.model;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.model.entities.ChatEntry;
import org.example.model.entities.CityHotel;
import org.example.model.entities.Person;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ModelModule extends AbstractModule {

    // DB_CLOSE_DELAY=-1 ensures the DB isn't closed after last connection is closed
    private static final String DB_CONNECTION_STRING = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "test";
    private static final String DB_SCHEMA = "test_schema";
    private static final String DB_DRIVER = "org.h2.Driver";

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(CityHotel.class);
        configuration.addAnnotatedClass(ChatEntry.class);

        configuration.setProperty("connection.driver_class", DB_DRIVER);
        configuration.setProperty("hibernate.connection.url", DB_CONNECTION_STRING);
        configuration.setProperty("hibernate.connection.username", DB_USER);
        configuration.setProperty("hibernate.connection.password", DB_USER);
        configuration.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.default_schema", DB_SCHEMA);
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

        return configuration.buildSessionFactory();
    }

    @Provides
    @Singleton
    public Flyway provideFlyway() {
        return new FluentConfiguration()
                .dataSource(DB_CONNECTION_STRING, DB_USER, DB_USER)
                .driver(DB_DRIVER)
                .schemas(DB_SCHEMA)
                .locations("classpath:db/migration")
                .load();
    }
}
