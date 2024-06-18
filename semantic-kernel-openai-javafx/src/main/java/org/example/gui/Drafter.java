package org.example.gui;

import com.google.inject.Inject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.example.agent.Agent;
import org.example.db.Person;
import org.example.db.Repository;


@RequiredArgsConstructor(onConstructor = @__(@Inject))
class Drafter {

    public static final Font DEFAULT_FONT;

    static {
        DEFAULT_FONT = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30.0);
    }

    private final Repository personRepository;
    private final Agent agent;

    public void compose(Stage stage) {
        stage.setTitle("semantic kernel demo");
        stage.setResizable(true);

        TextField textInput = new TextField();
        textInput.setAlignment(Pos.BASELINE_LEFT);
        textInput.setPrefWidth(800);
        textInput.setPrefHeight(200);
        textInput.setFont(DEFAULT_FONT);

        Button sendButton = new Button("send");
        sendButton.setAlignment(Pos.BASELINE_RIGHT);
        sendButton.setPrefWidth(200);
        sendButton.setPrefHeight(100);
        sendButton.setFont(DEFAULT_FONT);
        sendButton.setOnMouseClicked(event -> {
            agent.askGpt(textInput.getText());
        });

        HBox chatInput = new HBox(new Label("chat input"));
        chatInput.setAlignment(Pos.BASELINE_CENTER);
        chatInput.getChildren().addAll(textInput, sendButton);

        ListView chatHistory = new ListView(DataHolder.chatHistoryProperty);
        chatHistory.setPrefWidth(1000);
        chatHistory.setPrefHeight(800);
        chatHistory.setSelectionModel(new NoSelectionModel<String>());

        TableColumn<Person, Integer> personIdColumn = new TableColumn<>("ID");
        personIdColumn.setPrefWidth(50);
        personIdColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getId()));
        personIdColumn.setSortType(TableColumn.SortType.ASCENDING);

        TableColumn<Person, String> nameColumn = new TableColumn<>("name");
        nameColumn.setPrefWidth(150);
        nameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getName()));
        nameColumn.setSortable(false);

        TableColumn<Person, String> infoColumn = new TableColumn<>("info");
        infoColumn.setPrefWidth(150);
        infoColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getInfo()));
        nameColumn.setSortable(false);

        TableView<Person> dbState = new TableView<>();
        dbState.setPrefWidth(350);
        dbState.setPrefHeight(800);
        dbState.getColumns().addAll(personIdColumn, nameColumn, infoColumn);
        dbState.setPlaceholder(new Label("no items"));
        dbState.itemsProperty().bind(new SimpleObjectProperty<>(DataHolder.dbStateProperty));
        DataHolder.dbStateProperty.setAll(personRepository.findAll());

        HBox chatAndDbView = new HBox(new Label("chat and db"));
        chatAndDbView.setAlignment(Pos.BASELINE_CENTER);
        chatAndDbView.getChildren().addAll(chatHistory, dbState);

        VBox actionOnScene = new VBox(chatAndDbView, chatInput);

        stage.setScene(new Scene(actionOnScene));
    }


    private static class NoSelectionModel<T> extends MultipleSelectionModel<T> {

        @Override
        public ObservableList<Integer> getSelectedIndices() {
            return FXCollections.emptyObservableList();
        }

        @Override
        public ObservableList<T> getSelectedItems() {
            return FXCollections.emptyObservableList();
        }

        @Override
        public void selectIndices(int i, int... ints) {

        }

        @Override
        public void selectAll() {

        }

        @Override
        public void clearAndSelect(int i) {

        }

        @Override
        public void select(int i) {

        }

        @Override
        public void select(T t) {

        }

        @Override
        public void clearSelection(int i) {

        }

        @Override
        public void clearSelection() {

        }

        @Override
        public boolean isSelected(int i) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public void selectPrevious() {

        }

        @Override
        public void selectNext() {

        }

        @Override
        public void selectFirst() {

        }

        @Override
        public void selectLast() {

        }
    }

}
