package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllMovies extends RecentMovies {
    @FXML
    private VBox vBox;
//    @FXML
//    private HBox titleBar;
//
//    @FXML
//    private ScrollPane scrollPane;

    private ArrayList<Movie> movies;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movies = new ArrayList<>();
//        new ReadThread(ClientApplication.client.getSocketWrapper(), movies, this);
        MainMenuController.readThread.setData(movies);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("5");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void print()
    {
        int j = 1;
        for (int i = movies.size() - 1; i >= 0; i--)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personalizedHboxFxml.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((personalizedHboxFxml) fxmlLoader.getController()).setMovie(movies.get(i));
                ((personalizedHboxFxml) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reload(ActionEvent event) {
        vBox.getChildren().clear();

        movies = new ArrayList<>();
//        new ReadThread(ClientApplication.client.getSocketWrapper(), movies, this);
        MainMenuController.readThread.setData(movies);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("5");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
