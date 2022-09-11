package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MoviesByCompanies extends RecentMovies {
    @FXML
    private VBox vBox;
//    @FXML
//    private HBox titleBar;
//
//    @FXML
//    private ScrollPane scrollPane;

    private ArrayList<Movie> movies;

    private Map<String, Integer> map;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        map = new HashMap<>();
        new ReadThread(ClientApplication.client.getSocketWrapper(), map, this);
        try {
            ClientApplication.client.getSocketWrapper().write("4");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print()
    {
        int j = 1;
        for (Map.Entry<String, Integer> entry: map.entrySet())
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hBOXforCompanyCount.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((hBOXforComapanyCountFXML) fxmlLoader.getController()).setFields(j, entry.getKey(), entry.getValue());
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reload(ActionEvent event) {

        vBox.getChildren().clear();

        map = new HashMap<>();
        new ReadThread(ClientApplication.client.getSocketWrapper(), map, this);
        try {
            ClientApplication.client.getSocketWrapper().write("4");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
