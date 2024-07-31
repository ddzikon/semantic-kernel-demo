package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.viewmodel.AudioChatEntryViewModel;
import org.example.viewmodel.AudioChatServiceInteractor;
import org.example.viewmodel.ChatServiceInteractor;
import org.example.viewmodel.ChatEntryViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ViewController implements Initializable {

    private final static String RECORD_BUTTON_OFF_TEXT = "Record";
    private final static String RECORD_BUTTON_ON_TEXT = "Stop";

    @FXML
    private ListView<String> chatHistory;
    @FXML
    private ListView<String> audioChatHistory;
    @FXML
    private TextArea chatInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button recordButton;

    private final ChatEntryViewModel chatEntryViewModel;
    private final ChatServiceInteractor chatServiceInteractor;
    private final AudioChatEntryViewModel audioChatEntryViewModel;
    private final AudioChatServiceInteractor audioChatServiceInteractor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sendButton.setOnMouseClicked(event -> {
            final var message = chatInput.getText();
            log.info("Send button clicked, message: {}", message);

            // should it be handled through ViewModel?
            if (StringUtils.isNotBlank(message)) {
                chatServiceInteractor.askGpt(message);
                chatInput.clear();
            }
        });

        recordButton.setText(RECORD_BUTTON_OFF_TEXT);
        recordButton.setOnAction(actionEvent -> {
            if(recordButton.getText().equals(RECORD_BUTTON_OFF_TEXT)) {
                recordButton.setText(RECORD_BUTTON_ON_TEXT);
                audioChatServiceInteractor.startRecording();
            } else {
                recordButton.setText(RECORD_BUTTON_OFF_TEXT);
                audioChatServiceInteractor.stopRecording();
            }
        });

        chatHistory.setItems(chatEntryViewModel.getChatHistoryProperty());
        chatHistory.setSelectionModel(new NoSelectionModel<String>());
        chatHistory.setCellFactory(createListViewCellFactoryCallback());

        audioChatHistory.setItems(audioChatEntryViewModel.getAudioChatHistoryProperty());
        audioChatHistory.setSelectionModel(new NoSelectionModel<String>());
        audioChatHistory.setCellFactory(createListViewCellFactoryCallback());
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
