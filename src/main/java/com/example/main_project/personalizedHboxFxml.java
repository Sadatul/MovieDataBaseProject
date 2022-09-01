package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class personalizedHboxFxml {
    @FXML
    private Label column1;
    @FXML
    private Label column2;
    @FXML
    private Button column3;

    public void showMoreInfo(ActionEvent event) {
    }

    public void setFields(Movie movie)
    {
        column1.setText(movie.getTitle());
        String genres = String.join(", ", movie.getGenre());
        column2.setText(genres);
    }

}
