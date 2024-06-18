package org.example.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.db.Person;

public class DataHolder {
    public final static ObservableList<String> chatHistoryProperty = FXCollections.observableArrayList();
    public final static ObservableList<Person> dbStateProperty = FXCollections.observableArrayList();
}
