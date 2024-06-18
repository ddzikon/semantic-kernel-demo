package org.example.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.db.DbModule;
import org.flywaydb.core.Flyway;


public class Launcher extends Application {

    private Drafter drafter;

    @Override
    public void init() {
        Injector injector = Guice.createInjector(new DbModule(), new GuiModule());
        injector.getInstance(Flyway.class).migrate();
        this.drafter = injector.getInstance(Drafter.class);
    }

    @Override
    public void start(Stage stage) {
        this.drafter.compose(stage);
        stage.show();
    }

    public static void launch(String[] args) {
        Application.launch(args);
    }
}
