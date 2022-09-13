package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private StackPane contentArea;

    private String state;

    public static ReadThread readThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readThread = new ReadThread(ClientApplication.client.getSocketWrapper(), new Movie(""), new RecentMovies(), this);
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

    public void changeToRecentMovies(ActionEvent event) {
        state = "recent";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recentMovies.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToMaxRevenueMovie(ActionEvent event) {
        state = "MaxRev";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("maxRevenueMovie.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeToMoviesByCompanies(ActionEvent event) {
        state = "MoviesByCompanies";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moviesByCompanies.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeToAllMovies(ActionEvent event) {
        state = "AllMovies";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("allMovies.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToTotalProfit(ActionEvent event) {
        state = "TotalProfit";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("totalProfitCompany.fxml"));
            Parent fxml = fxmlLoader.load();
//            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToAddMovie(ActionEvent event) {
        state = "AddMovie";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
            Parent fxml = fxmlLoader.load();
//            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh()
    {
//        System.out.println(state);
        if(state.equals("recent")) changeToRecentMovies(new ActionEvent());
        if(state.equals("MaxRev")) changeToMaxRevenueMovie(new ActionEvent());
        if(state.equals("MoviesByCompanies")) changeToMoviesByCompanies(new ActionEvent());
        if(state.equals("AllMovies")) changeToAllMovies(new ActionEvent());
        if(state.equals("TotalProfit")) changeToTotalProfit(new ActionEvent());
    }


}