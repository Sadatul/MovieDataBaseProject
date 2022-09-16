package com.example.main_project;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

public class Search extends RecentMovies{
    @FXML
    private AnchorPane namePane;
    @FXML
    private Button namePaneButton;
    private String namePaneState;

    @FXML
    private AnchorPane yearPane;
    @FXML
    private Button yearButton;
    private String yearPaneState;

    @FXML
    private AnchorPane genrePane;
    @FXML
    private Button genrePaneButton;
    private String genrePaneState;


    private Stack<String> activeStack;
    private Stack<String> inactiveStack;

    ArrayList<Movie> movies;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        namePaneState = "ACTIVE";
        yearPaneState = "ACTIVE";
        genrePaneState = "ACTIVE";
        activeStack = new Stack<>();
        activeStack.push("time");
        activeStack.push("year");
        activeStack.push("genre");
        activeStack.push("name");
        inactiveStack = new Stack<>();

        movies = new ArrayList<>();
        MainMenuController.readThread.setData(movies);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("5");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        forName();
    }

    @Override
    public void print()
    {

    }

    public void namePaneToggle(ActionEvent event) {
        if(namePaneState.equals("ACTIVE"))
        {
            if(!(activeStack.peek().equals("name")))
            {
                return;
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(namePane);

            slide.setToY(451);
            slide.play();

//            namePane.setTranslateY(200);
            slide.setOnFinished((ActionEvent e) ->{
                namePaneState = "INACTIVE";
                inactiveStack.push(activeStack.pop());
                setUp();
            });
        }
        else if (namePaneState.equals("INACTIVE"))
        {
            if(!(inactiveStack.peek().equals("name")))
            {
                return;
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(namePane);

            slide.setToY(0);
            slide.play();

//            namePane.setTranslateY(-200);
            slide.setOnFinished((ActionEvent e) ->{
                namePaneState = "ACTIVE";
                activeStack.push(inactiveStack.pop());
                setUp();
            });
        }
    }

    public void timePaneToggle(ActionEvent event) {
    }

    public void yearPaneToggle(ActionEvent event) {
        if(yearPaneState.equals("ACTIVE"))
        {
            if(!(activeStack.peek().equals("year")))
            {
                return;
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(yearPane);

            slide.setToY(451);
            slide.play();

//            namePane.setTranslateY(200);
            slide.setOnFinished((ActionEvent e) ->{
                yearPaneState = "INACTIVE";
                inactiveStack.push(activeStack.pop());
                setUp();
            });
        }
        else if (yearPaneState.equals("INACTIVE"))
        {
            if(!(inactiveStack.peek().equals("year")))
            {
                return;
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(yearPane);

            slide.setToY(0);
            slide.play();

//            namePane.setTranslateY(-200);
            slide.setOnFinished((ActionEvent e) ->{
                yearPaneState = "ACTIVE";
                activeStack.push(inactiveStack.pop());
                setUp();
            });
        }
    }

    public void genrePaneToggle(ActionEvent event) {
        if(genrePaneState.equals("ACTIVE"))
        {
            if(!(activeStack.peek().equals("genre")))
            {
                return;
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(genrePane);

            slide.setToY(451);
            slide.play();

//            namePane.setTranslateY(200);
            slide.setOnFinished((ActionEvent e) ->{
                genrePaneState = "INACTIVE";
                inactiveStack.push(activeStack.pop());
                setUp();

            });
        }
        else if (genrePaneState.equals("INACTIVE"))
        {
            if(!(inactiveStack.peek().equals("genre")))
            {
                return;
            }
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(genrePane);

            slide.setToY(0);
            slide.play();

//            namePane.setTranslateY(-200);
            slide.setOnFinished((ActionEvent e) ->{
                genrePaneState = "ACTIVE";
                activeStack.push(inactiveStack.pop());
                setUp();

            });
        }
    }

    @FXML
    private VBox vBoxName;
    @FXML
    private VBox vBoxGenre;
    @FXML
    private VBox vBoxYear;
    @FXML
    private VBox vBoxTime;

    public void setUp()
    {
//        System.out.println(activeStack.peek());
        vBoxName.setVisible(false);
        vBoxGenre.setVisible(false);
        vBoxYear.setVisible(false);
        vBoxTime.setVisible(false);
        vBoxName.getChildren().clear();
        vBoxGenre.getChildren().clear();
        vBoxYear.getChildren().clear();
        vBoxTime.getChildren().clear();
        if(activeStack.peek().equals("name"))
        {
            forName();
            vBoxName.setVisible(true);
        }
        else if(activeStack.peek().equals("year"))
        {
            forYear();
            vBoxYear.setVisible(true);
        }
        else if(activeStack.peek().equals("genre"))
        {
            forGenre();
            vBoxGenre.setVisible(true);
        }
        else if(activeStack.peek().equals("time"))
        {
            forTime();
            vBoxTime.setVisible(true);
        }
    }
    public void forName()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showAllMovie.fxml"));
        Node root;
        try {
            root = (Node) fxmlLoader.load();
            ((ShowAllMovie) fxmlLoader.getController()).setMovies(movies);
            ((ShowAllMovie) fxmlLoader.getController()).setState("name");
            vBoxName.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void forYear()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showAllMovie.fxml"));
        Node root;
        try {
            root = (Node) fxmlLoader.load();
            ((ShowAllMovie) fxmlLoader.getController()).setMovies(movies);
            ((ShowAllMovie) fxmlLoader.getController()).setState("year");
            vBoxYear.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void forGenre()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showAllMovie.fxml"));
        Node root;
        try {
            root = (Node) fxmlLoader.load();
            ((ShowAllMovie) fxmlLoader.getController()).setMovies(movies);
            ((ShowAllMovie) fxmlLoader.getController()).setState("genre");
            vBoxGenre.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void forTime()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchByRunningTime.fxml"));
        Node root;
        try {
            root = (Node) fxmlLoader.load();
            ((SearchByRunningTime) fxmlLoader.getController()).setMovies(movies);
            vBoxTime.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
