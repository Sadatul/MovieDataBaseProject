package com.example.main_project;

import Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApplication extends Application {

    static Client client;
    Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        Parent root = fxmlLoader.load();

        ((LogInController)fxmlLoader.getController()).setClientApplication(this);
        Scene scene = new Scene(root);
        stage.setTitle("Movie Data Base!");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((event -> {
            if(client.isGotClient()) {
                event.consume();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("You're about to logout!");
                alert.setContentText("Do you want to Log Out?: ");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    try {
                        client.getSocketWrapper().write("6");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
//                    stage.close();
                    swapToLogIn();
                }
            }
        }));

    }

    public void swapToLogIn()
    {
        client.setGotClient(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((LogInController)fxmlLoader.getController()).setClientApplication(this);
        Scene scene = new Scene(root);
        stage.setTitle("Movie Data Base!");
        stage.setScene(scene);
        stage.show();
    }
    public void swapToMainMenu()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        stage.setTitle("Movie Data Base!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        client = new Client();
        launch();
        System.out.println(client.getName());
    }
}