package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class searchByNameController implements Initializable {

    private MovieDataSet movieDataSet;

    public MovieDataSet getMovieDataSet() {
        return movieDataSet;
    }

    public void setMovieDataSet(MovieDataSet movieDataSet) {
        this.movieDataSet = movieDataSet;
    }
    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nameFxml.fxml"));
//            Parent fxml = fxmlLoader.load();
//            ((NameFxml) fxmlLoader.getController()).setMovieDataSet(movieDataSet);
////            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
//            contentArea.getChildren().clear();
//            contentArea.getChildren().addAll(fxml);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void changeToReleaseYear(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("releaseYearFxml.fxml"));
            Parent fxml = fxmlLoader.load();
            ((ReleaseYearFxml) fxmlLoader.getController()).setMovieDataSet(movieDataSet);
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToName(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nameFxml.fxml"));
            Parent fxml = fxmlLoader.load();
            ((NameFxml) fxmlLoader.getController()).setMovieDataSet(movieDataSet);
//            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}