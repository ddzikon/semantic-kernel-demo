package org.example.view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.model.entities.Person;
import org.example.viewmodel.AgentInteractor;
import org.example.viewmodel.ChatEntryViewModel;
import org.example.viewmodel.PersonViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ViewController implements Initializable {

    @FXML
    private ListView<String> chatHistory;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> personTableIdColumn;
    @FXML
    private TableColumn<Person, String> personTableNameColumn;
    @FXML
    private TableColumn<Person, String> personTableInfoColumn;
    @FXML
    private TextArea chatInput;
    @FXML
    private Button sendButton;

    private final PersonViewModel personViewModel;
    private final ChatEntryViewModel chatEntryViewModel;
    private final AgentInteractor agentInteractor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendButton.setOnMouseClicked(event -> {
            final var message = chatInput.getText();
            log.info("Send button clicked, message: {}", message);

            // should it be handled through ViewModel?
            if (StringUtils.isNotBlank(message)) {
                agentInteractor.askGpt(message);
                chatInput.clear();
            }
        });

        chatHistory.setItems(chatEntryViewModel.getChatHistoryProperty());
        chatHistory.setSelectionModel(new NoSelectionModel<String>());
        chatHistory.setCellFactory(createListViewCellFactoryCallback());

        personTableIdColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getId()));
        personTableNameColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getName()));
        personTableInfoColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getInfo()));

        personTable.itemsProperty().bind(new SimpleObjectProperty<>(personViewModel.getPeopleStateProperty()));
    }

    private static Callback<ListView<String>, ListCell<String>> createListViewCellFactoryCallback() {
        return listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setMinWidth(listView.getWidth());
                    setMaxWidth(listView.getWidth());
                    setPrefWidth(listView.getWidth());

                    setWrapText(true);

                    setText(item);
                }
            }
        };
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
