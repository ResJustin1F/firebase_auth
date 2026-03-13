package com.example.csc325_firebase_webview_auth.view;

import com.example.csc325_firebase_webview_auth.model.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AccessFBView {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField departmentField;
    @FXML private TextField majorField;
    @FXML private TextField emailField;
    @FXML private TextField imageUrlField;
    @FXML private TableView<Person> tableView;

    private ObservableList<Person> listOfUsers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableView.setItems(listOfUsers);
        loadFromFirebase();
    }

    @FXML
    private void addRecord(ActionEvent event) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String department = departmentField.getText();
        String major = majorField.getText();
        String email = emailField.getText();
        String imageUrl = imageUrlField.getText();

        DocumentReference docRef = App.fstore.collection("Students").document(id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("firstName", firstName);
        data.put("lastName", lastName);
        data.put("department", department);
        data.put("major", major);
        data.put("email", email);
        data.put("imageUrl", imageUrl);
        ApiFuture<WriteResult> result = docRef.set(data);

        listOfUsers.add(new Person(id, firstName, lastName, department, major, email, imageUrl));
        clearFields(null);
    }

    @FXML
    private void clearFields(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        departmentField.clear();
        majorField.clear();
        emailField.clear();
        imageUrlField.clear();
    }

    @FXML
    private void deleteRecord(ActionEvent event) {
        Person selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            App.fstore.collection("Students").document(selected.getId()).delete();
            listOfUsers.remove(selected);
        }
    }

    @FXML
    private void editRecord(ActionEvent event) {
        Person selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            firstNameField.setText(selected.getFirstName());
            lastNameField.setText(selected.getLastName());
            departmentField.setText(selected.getDepartment());
            majorField.setText(selected.getMajor());
            emailField.setText(selected.getEmail());
            imageUrlField.setText(selected.getImageUrl());
            listOfUsers.remove(selected);
            App.fstore.collection("Students").document(selected.getId()).delete();
        }
    }

    private void loadFromFirebase() {
        ApiFuture<QuerySnapshot> future = App.fstore.collection("Students").get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot doc : documents) {
                listOfUsers.add(new Person(
                        doc.getId(),
                        String.valueOf(doc.getData().getOrDefault("firstName", "")),
                        String.valueOf(doc.getData().getOrDefault("lastName", "")),
                        String.valueOf(doc.getData().getOrDefault("department", "")),
                        String.valueOf(doc.getData().getOrDefault("major", "")),
                        String.valueOf(doc.getData().getOrDefault("email", "")),
                        String.valueOf(doc.getData().getOrDefault("imageUrl", ""))
                ));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToRegister() throws IOException {
        App.setRoot("/files/Register.fxml");
    }

    @FXML
    private void closeApp() {
        javafx.application.Platform.exit();
    }

    @FXML
    private void setLightTheme() {
        App.scene.getStylesheets().clear();
        App.scene.getStylesheets().add(getClass().getResource("/files/styles.css").toExternalForm());
    }

    @FXML
    private void setDarkTheme() {
        App.scene.getStylesheets().clear();
    }

    @FXML
    private void showAbout() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("Created by Justin Restrepo");
        alert.showAndWait();
    }
}
