package com.example.csc325_firebase_webview_auth.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            // Firebase Auth: verify the user exists with that email
            App.fauth.getUserByEmail(email);

            // If no exception was thrown, user exists — go to main screen
            App.setRoot("/files/AccessFBView.fxml");

        } catch (Exception e) {
            errorLabel.setText("Invalid email or password.");
            e.printStackTrace();
        }
    }

    @FXML
    private void goToRegister(ActionEvent event) throws IOException {
        App.setRoot("/files/Register.fxml");
    }
}