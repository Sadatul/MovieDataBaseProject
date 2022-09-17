package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchByRunningTime implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldName1;
    @FXML
    private Label errorMessageName;
    @FXML
    private ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;

    private ArrayList<Movie> movies;
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
    }

    public void searchName(ActionEvent event) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String time1 = textFieldName.getText();
        String time2 = textFieldName1.getText();
        if(time1.equals("") || time2.equals(""))
        {
            errorMessageName.setVisible(true);
            errorMessageName.setText("Proper Input Not Given");
            return;
        }

        ArrayList<Movie> moviesToShow = new ArrayList<>();

        int num1, num2;
        try {
            num1 = Integer.parseInt(time1);
            num2 = Integer.parseInt(time2);
        } catch (Exception e) {
            errorMessageName.setVisible(true);
            errorMessageName.setText("Please Enter a Integer");
            return;
        }
        for (int i = 0; i < movies.size(); i++)
        {
            int time = movies.get(i).getRunning_time();
            if(time >= num1 && time <= num2)
            {
                moviesToShow.add(movies.get(i));
            }
        }
        if(moviesToShow.size() == 0)
        {
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Movie found");
            return;
        }

        scrollPaneName.setVisible(true);
        titleBar.setVisible(true);
        int j = 1;
        for (int i = moviesToShow.size() - 1; i >= 0; i--)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personalizedHboxFxml.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((personalizedHboxFxml) fxmlLoader.getController()).setMovie(moviesToShow.get(i));
                ((personalizedHboxFxml) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
