package com.example.main_project;

import Client.Client;
import Client.ReadThreadForTransfer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class TransferWindow {
    @FXML
    private TextField movieName;

    @FXML
    private TextField reMovieName;

    @FXML
    private TextField companyName;

    @FXML
    private Label errorMessage;
    public void setName1(String text) {
        movieName.setText(text);
    }

    public void noMovieFound()
    {
        errorMessage.setText("No Movie Found");
        checkCompanyName = false;
    }
    private ArrayList<Movie> movies;
    private boolean checkCompanyName;
    public String returnCompanyName()
    {
        return companyName.getText();
    }
    public void companyCheck()
    {
        if(movies.size() == 0)
        {
            errorMessage.setText("Company Not Found");
        }
    }
    public void launchCompanyReadThread()
    {
        if(checkCompanyName) {
            System.out.println("2");
            new ReadThreadForTransfer(ClientApplication.client.getSocketWrapper(), movies, this);
        }
    }
    public void transfer(ActionEvent event) {
        checkCompanyName = true;
        errorMessage.setText("");
        movies = new ArrayList<>();
        String name = reMovieName.getText();
        if(!(name.equalsIgnoreCase(movieName.getText())))
        {
            errorMessage.setText("Make Sure that the Movie names match");
            return;
        }
        if(ClientApplication.client.getName().equalsIgnoreCase(companyName.getText()))
        {
            errorMessage.setText("Movie Already Exists");
            return;
        }
        Movie movie = new Movie("");
        new ReadThreadForTransfer(ClientApplication.client.getSocketWrapper(), movie, this);
        try {
            ClientApplication.client.getSocketWrapper().write("**" + movieName.getText());
        } catch (IOException e) {
        }

    }
}
