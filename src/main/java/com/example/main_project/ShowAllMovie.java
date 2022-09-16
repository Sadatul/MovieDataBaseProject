package com.example.main_project;

import Client.ReadThread;
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

public class ShowAllMovie implements Initializable {
    @FXML
    private TextField textFieldName;
    @FXML
    private Label errorMessageName;
    @FXML
    private ScrollPane scrollPaneName;
    @FXML
    private VBox vBox;
    @FXML
    private HBox titleBar;

    private String state;

    public void setState(String state) {
        this.state = state;
        if(state.equals("year"))
        {
            textFieldName.setPromptText("Enter a Year");
        }
        else if(state.equals("genre"))
        {
            textFieldName.setPromptText("Enter a Genre");
        }
    }

    private ArrayList<Movie> movies;
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void searchName(ActionEvent event) {
        vBox.getChildren().clear();
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
        errorMessageName.setVisible(false);
        String name = textFieldName.getText();
        if(name.equals(""))
        {
            errorMessageName.setVisible(true);
            errorMessageName.setText("No Input Given");
            return;
        }

        ArrayList<Movie> moviesToShow = new ArrayList<>();
        if(state.equalsIgnoreCase("name"))
        {
            moviesToShow = serachByName(name);
        }
        else if(state.equalsIgnoreCase("genre"))
        {
            moviesToShow = searchByGenre(name);
        }
        else if(state.equalsIgnoreCase("year"))
        {
            int num;
            try{
                num = Integer.parseInt(name);
            }
            catch (Exception e)
            {
                errorMessageName.setVisible(true);
                errorMessageName.setText("Please Enter a Integer");
                return;
            }
            moviesToShow = searchByYear(num);
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

    private ArrayList<Movie> serachByName(String name)
    {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++)
        {
            if(movies.get(i).getTitle().equalsIgnoreCase(name))
            {
                movieArrayList.add(movies.get(i));
                break;
            }
        }
        return movieArrayList;
    }

    private ArrayList<Movie> searchByGenre(String genre)
    {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++)
        {
            for(String s: movies.get(i).getGenre())
            {
                if(s.equalsIgnoreCase(genre))
                {
                    movieArrayList.add(movies.get(i));
                    break;
                }
            }
        }
        return movieArrayList;
    }

    private ArrayList<Movie> searchByYear(int num)
    {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++)
        {
            if(movies.get(i).getYear_of_release() == num)
            {
                movieArrayList.add(movies.get(i));
            }
        }
        return movieArrayList;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPaneName.setVisible(false);
        titleBar.setVisible(false);
    }
}
