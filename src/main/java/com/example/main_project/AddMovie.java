package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class AddMovie extends RecentMovies{

    @FXML
    private AnchorPane hiddenPane;
    @FXML
    private TextField movieName;
    @FXML
    private Button checkButton;
    @FXML
    private TextField genre;
    @FXML
    private TextField releaseYear;

    @FXML
    private TextField runningTime;

    @FXML
    private TextField budget;

    @FXML
    private TextField revenue;

    @FXML
    private Label errorMessage;
    private Movie movie;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hiddenPane.setVisible(false);
        genre.setFocusTraversable(false);
        budget.setFocusTraversable(false);
        releaseYear.setFocusTraversable(false);
        revenue.setFocusTraversable(false);
        runningTime.setFocusTraversable(false);
//        scrollPane.setVisible(false);
//        titleBar.setVisible(false);
//        movies = new ArrayList<Movie>();
//        new ReadThread(ClientApplication.client.getSocketWrapper(), movies, this);
//        try {
//            ClientApplication.client.getSocketWrapper().write("1");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void showMovie()
    {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moreInfo.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            ((MoreInfo)fxmlLoader.getController()).setFields(movie, stage);
            stage.setTitle("More Info");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void movieAlreadyIn()
    {
        hiddenPane.setVisible(false);
        checkButton.setVisible(true);
        errorMessage.setText("Movie Already Exists");
        showMovie();
    }
    public void checkComplete()
    {
        hiddenPane.setVisible(true);
        movieName.setEditable(false);
        checkButton.setVisible(false);
    }
    public void check(ActionEvent event) {
        errorMessage.setText("");
        if(movieName.getText().equals(""))
        {
            errorMessage.setText("Must Enter a Movie Name");
            return;
        }
//        new ReadThread(ClientApplication.client.getSocketWrapper(), movie, this);
        MainMenuController.readThread.setData(movie);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("**" + movieName.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(ActionEvent event) {
        errorMessage.setText("");
        ArrayList<String> genreList = new ArrayList<>();
        String[] arr;
        arr = genre.getText().split(",");
        if(arr.length < 1 || arr.length > 3 || arr[0].equals(""))
        {
            errorMessage.setText("You must input least 1 genre and maximum 3 genre ");
            return;
        }
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = arr[i].trim();
        }
        Collections.addAll(genreList, arr);
        int year, runtime;
        long budget_val, revenue_val;
        try {
            year = Integer.parseInt(releaseYear.getText());
            runtime = Integer.parseInt(runningTime.getText());
            budget_val = Long.parseLong(budget.getText());
            revenue_val = Long.parseLong(revenue.getText());
        }
        catch (Exception e)
        {
            errorMessage.setText("Budget, RunningTime, Revenue and Release Year must be integers");
            return;
        }

        movie = new Movie(movieName.getText(), year, genreList, runtime, ClientApplication.client.getName(), budget_val, revenue_val);
//        new ReadThread(ClientApplication.client.getSocketWrapper(), movie, this);
        MainMenuController.readThread.setData(movie);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("##" + movie.objectToString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
