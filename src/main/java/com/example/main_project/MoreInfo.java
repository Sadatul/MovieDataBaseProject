package com.example.main_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MoreInfo {
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

    public void setFields(Movie movie)
    {
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
