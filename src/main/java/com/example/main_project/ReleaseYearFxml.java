package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReleaseYearFxml implements Initializable {

    private MovieDataSet movieDataSet;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField textField;

    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setVisible(false);
        titleBar.setVisible(false);
    }
    public MovieDataSet getMovieDataSet() {
        return movieDataSet;
    }

    public void setMovieDataSet(MovieDataSet movieDataSet) {
        this.movieDataSet = movieDataSet;
    }

    public void search(ActionEvent event) {
        scrollPane.setVisible(false);
        titleBar.setVisible(false);
        errorMessage.setText("");
        int year;
        try {
            year = Integer.parseInt(textField.getText());
        }
        catch (Exception e)
        {
            errorMessage.setText("You must enter a number.");
            return;
        }
        List<Movie> movies = movieDataSet.searchByReleaseyear(year);
        if(movies.size() == 0)
        {
            errorMessage.setText("No Movie Found");
            return;
        }

        scrollPane.setVisible(true);
        titleBar.setVisible(true);
        vBox.getChildren().clear();

        int j = 1;
        for (int i = movies.size() - 1; i >= 0; i--)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personalizedHboxFxml.fxml"));
            Node root;
            try {
                root = (Node) fxmlLoader.load();
                ((personalizedHboxFxml) fxmlLoader.getController()).setMovie(movies.get(i));
                ((personalizedHboxFxml) fxmlLoader.getController()).setFields(j);
                vBox.getChildren().add(root);
                j++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
