package com.example.main_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MovieDataSet movieDataSet = null;
        try {
            movieDataSet = new MovieDataSet("movies.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchByName.fxml"));
        Parent root = fxmlLoader.load();
        ((searchByNameController) fxmlLoader.getController()).setMovieDataSet(movieDataSet);

        Scene scene = new Scene(root);
        stage.setTitle("Movie Data Base!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}