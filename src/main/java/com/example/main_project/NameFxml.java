package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NameFxml implements Initializable {

    private MovieDataSet movieDataSet;

    public MovieDataSet getMovieDataSet() {
        return movieDataSet;
    }

    public void setMovieDataSet(MovieDataSet movieDataSet) {
        this.movieDataSet = movieDataSet;
    }

    @FXML
    private Label name;
    @FXML
    private Label genre;
    @FXML
    private Label companyName;
    @FXML
    private Label year;
    @FXML
    private Label runningTime;
    @FXML
    private Label budget;
    @FXML
    private Label revenue;

    @FXML
    private Label errorMessage;
    @FXML
    private TextField textField;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.setVisible(false);
    }
    public void search(ActionEvent event) {
        errorMessage.setText("");
        anchorPane.setVisible(false);
        String movieName = textField.getText();
        Movie movie = movieDataSet.searchByName(movieName);
        if(movie == null)
        {
            errorMessage.setText("Movie Not Found");
            return;
        }
        anchorPane.setVisible(true);
        name.setText(movie.getTitle());
        String genreString = String.join(", ", movie.getGenre());
        genre.setText(genreString);
        companyName.setText(movie.getProduction_company());
        year.setText(Integer.toString(movie.getYear_of_release()));
        runningTime.setText(Integer.toString(movie.getRunning_time()));
        budget.setText(Long.toString(movie.getBudget()));
        revenue.setText(Long.toString(movie.getRevenue()));
    }

}
