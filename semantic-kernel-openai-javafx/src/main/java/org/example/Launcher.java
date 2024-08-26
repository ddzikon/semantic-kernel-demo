package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.agent.AgentModule;
import org.example.view.ViewController;
import org.example.view.ViewModule;
import org.example.model.ModelModule;
import org.example.viewmodel.ViewModelModule;
import org.flywaydb.core.Flyway;

import java.io.IOException;


public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Injector injector = Guice.createInjector(
                new ViewModule(),
                new ViewModelModule(),
                new ModelModule(),
                new AgentModule()
        );

        injector.getInstance(Flyway.class).migrate();

        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/fxml/ViewController.fxml"));
        loader.setController(injector.getInstance(ViewController.class));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }

    public static void launch(String[] args) {
        Application.launch(args);
    }
}
