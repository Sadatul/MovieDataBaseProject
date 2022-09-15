package com.example.main_project;

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
//        checkCompanyName = false;
    }

    public void movieTransferNotAllowed()
    {
        errorMessage.setText("This Company Can Transfer only its movies");
    }
    private ArrayList<Movie> movies;
    private boolean checkCompanyName;
    public String returnCompanyName()
    {
        return companyName.getText();
    }
    public void companyCheck()
    {
        errorMessage.setText("Company Not Found");
//        if(movies.size() == 0)
//        {
//            errorMessage.setText("Company Not Found");
//        }
//        else
//        {
//            try {
//                ClientApplication.client.getSocketWrapper().write("$*" + movieName.getText() + "," + companyName.getText() + "," + ClientApplication.client.getName());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
    public void launchCompanyReadThread()
    {
        if(checkCompanyName) {
            System.out.println("2");
//            new ReadThreadForTransfer(ClientApplication.client.getSocketWrapper(), movies, this);
            MainMenuController.readThread.setData(movies);
            MainMenuController.readThread.setReleaseYearFxml(this);
        }
    }
    public void transferComplete()
    {
        errorMessage.setText("Transfer Complete");
    }

    public void movieExists()
    {
        errorMessage.setText("Movie Already Exists");
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
//        if(ClientApplication.client.getName().equalsIgnoreCase(companyName.getText()))
//        {
//            errorMessage.setText("Movie Already Exists");
//            return;
//        }
        try {
            ClientApplication.client.getSocketWrapper().write("$*" + movieName.getText() + "," + companyName.getText() + "," + ClientApplication.client.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Movie movie = new Movie("");
        MainMenuController.readThread.setData(movie);
        MainMenuController.readThread.setReleaseYearFxml(this);
//        try {
//            ClientApplication.client.getSocketWrapper().write("**" + movieName.getText());
//        } catch (IOException e) {
//        }

    }
}
