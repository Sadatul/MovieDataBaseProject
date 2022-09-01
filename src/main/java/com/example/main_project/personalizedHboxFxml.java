package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class personalizedHboxFxml {

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @FXML
    private Label column1;
    @FXML
    private Label column2;
    @FXML
    private Button column3;

    @FXML
    private Label siNo;

    public void showMoreInfo(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moreInfo.fxml"));
            root = fxmlLoader.load();
            ((MoreInfo)fxmlLoader.getController()).setFields(movie);
            Stage stage = new Stage();
            stage.setTitle("More Info");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFields(int i)
    {
        siNo.setText(Integer.toString(i));
        column1.setText(movie.getTitle());
        String genres = String.join(", ", movie.getGenre());
        column2.setText(genres);
    }

}
