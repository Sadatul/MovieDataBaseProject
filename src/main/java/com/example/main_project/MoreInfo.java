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

    @FXML
    private Button transferButton;

    public void noTransferButton()
    {
        transferButton.setVisible(false);
    }
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

    public void transfer(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransferWindow.fxml"));
            root = fxmlLoader.load();
            ((TransferWindow)fxmlLoader.getController()).setName1(name.getText());
            Stage stage = new Stage();
            stage.setTitle("Transfer Page");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
