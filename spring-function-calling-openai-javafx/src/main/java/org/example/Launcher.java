package org.example;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.view.ViewController;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;


public class Launcher extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
                    applicationContext.registerBean(Application.class, () -> Launcher.this);
                    applicationContext.registerBean(Parameters.class, Launcher.this::getParameters);
                    applicationContext.registerBean(HostServices.class, Launcher.this::getHostServices);
                };

        this.context = new SpringApplicationBuilder()
                .sources(Main.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/fxml/ViewController.fxml"));
        loader.setController(context.getBean(ViewController.class));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }

    public static void launch(String[] args) {
        Application.launch(args);
    }
}
