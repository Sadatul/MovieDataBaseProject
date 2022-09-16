package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private StackPane contentArea;
    @FXML
    private Button allMovieButton;
    @FXML
    private Button maxMovieButton;
    @FXML
    private Button recentMovieButton;
    @FXML
    private Button addButton;
    @FXML
    private Button companyButton;
    @FXML
    private Button transferButton;

    @FXML
    private Button totalProfitButton;
    private String state;
    private String transferMovieName;
    private boolean setFirstName;
    public static ReadThread readThread;
    static MainMenuController me;


    public void setTransferMovieName(String transferMovieName) {
        this.transferMovieName = transferMovieName;
    }

    public void setSetFirstName(boolean setFirstName) {
        this.setFirstName = setFirstName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readThread = new ReadThread(ClientApplication.client.getSocketWrapper(), new Movie(""), new RecentMovies(), this);
        me = this;
        setFirstName = false;
        transferMovieName = "";
        allMovieButton.setOnMouseEntered(hoverEnterHandler);
        allMovieButton.setOnMouseExited(hoverExitHandler);
        recentMovieButton.setOnMouseEntered(hoverEnterHandler);
        recentMovieButton.setOnMouseExited(hoverExitHandler);
        maxMovieButton.setOnMouseEntered(hoverEnterHandler);
        maxMovieButton.setOnMouseExited(hoverExitHandler);
        totalProfitButton.setOnMouseEntered(hoverEnterHandler);
        totalProfitButton.setOnMouseExited(hoverExitHandler);
        addButton.setOnMouseEntered(hoverEnterHandler);
        addButton.setOnMouseExited(hoverExitHandler);
        companyButton.setOnMouseEntered(hoverEnterHandler);
        companyButton.setOnMouseExited(hoverExitHandler);
        transferButton.setOnMouseEntered(hoverEnterHandler);
        transferButton.setOnMouseExited(hoverExitHandler);
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nameFxml.fxml"));
//            Parent fxml = fxmlLoader.load();
//            ((NameFxml) fxmlLoader.getController()).setMovieDataSet(movieDataSet);
////            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
//            contentArea.getChildren().clear();
//            contentArea.getChildren().addAll(fxml);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void changeToRecentMovies(ActionEvent event) {
        state = "recent";
        setButtonDefaultColor();
        recentMovieButton.setOnMouseEntered(event1 -> {});
        recentMovieButton.setOnMouseExited(event1 -> {});
        recentMovieButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recentMovies.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToMaxRevenueMovie(ActionEvent event) {
        state = "MaxRev";
        setButtonDefaultColor();
        maxMovieButton.setOnMouseEntered(event1 -> {});
        maxMovieButton.setOnMouseExited(event1 -> {});
        maxMovieButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("maxRevenueMovie.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeToDashBoard(ActionEvent event) {
        state = "DashBoard";
        setButtonDefaultColor();
        companyButton.setOnMouseEntered(event1 -> {});
        companyButton.setOnMouseExited(event1 -> {});
        companyButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashBoard.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeToAllMovies(ActionEvent event) {
        state = "AllMovies";
        setButtonDefaultColor();
        allMovieButton.setOnMouseEntered(event1 -> {});
        allMovieButton.setOnMouseExited(event1 -> {});
        allMovieButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("allMovies.fxml"));
            Parent fxml = fxmlLoader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToTotalProfit(ActionEvent event) {
        state = "TotalProfit";
        setButtonDefaultColor();
        totalProfitButton.setOnMouseEntered(event1 -> {});
        totalProfitButton.setOnMouseExited(event1 -> {});
        totalProfitButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("totalProfitCompany.fxml"));
            Parent fxml = fxmlLoader.load();
//            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToAddMovie(ActionEvent event) {
        state = "AddMovie";
        setButtonDefaultColor();
        addButton.setOnMouseEntered(event1 -> {});
        addButton.setOnMouseExited(event1 -> {});
        addButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddMovie.fxml"));
            Parent fxml = fxmlLoader.load();
//            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeToTransfer(ActionEvent event) {
        state = "Transfer";
        setButtonDefaultColor();
        transferButton.setOnMouseEntered(event1 -> {});
        transferButton.setOnMouseExited(event1 -> {});
        transferButton.setStyle("-fx-background-color:  #98c1d9; -fx-text-fill: #293241");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransferWindow.fxml"));
            Parent fxml = fxmlLoader.load();
            if(setFirstName)
            {
                ((TransferWindow)fxmlLoader.getController()).setName1(transferMovieName);
                transferMovieName = "";
                setFirstName = false;
            }
//            Parent fxml = FXMLLoader.load(getClass().getResource("nameFxml.fxml"));
            contentArea.getChildren().clear();
            contentArea.getChildren().addAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void refresh()
    {
        if(state.equals("recent"))
        {
            changeToRecentMovies(new ActionEvent());
        }
        if(state.equals("MaxRev")) {
            changeToMaxRevenueMovie(new ActionEvent());
        }
        if(state.equals("DashBoard")) {
            changeToDashBoard(new ActionEvent());
        }
        if(state.equals("AllMovies")) {
            changeToAllMovies(new ActionEvent());
        }
        if(state.equals("TotalProfit")) {
            changeToTotalProfit(new ActionEvent());
        }
    }

    public void setButtonDefaultColor()
    {
        allMovieButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        recentMovieButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        maxMovieButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        addButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        companyButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        transferButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        totalProfitButton.setStyle("-fx-background-color:  #3d5a80; -fx-text-fill: #d4f1f4");
        allMovieButton.setOnMouseExited(hoverExitHandler);
        allMovieButton.setOnMouseEntered(hoverEnterHandler);
        allMovieButton.setOnMouseExited(hoverExitHandler);
        recentMovieButton.setOnMouseEntered(hoverEnterHandler);
        recentMovieButton.setOnMouseExited(hoverExitHandler);
        maxMovieButton.setOnMouseEntered(hoverEnterHandler);
        maxMovieButton.setOnMouseExited(hoverExitHandler);
        totalProfitButton.setOnMouseEntered(hoverEnterHandler);
        totalProfitButton.setOnMouseExited(hoverExitHandler);
        addButton.setOnMouseEntered(hoverEnterHandler);
        addButton.setOnMouseExited(hoverExitHandler);
        companyButton.setOnMouseEntered(hoverEnterHandler);
        companyButton.setOnMouseExited(hoverExitHandler);
        transferButton.setOnMouseEntered(hoverEnterHandler);
        transferButton.setOnMouseExited(hoverExitHandler);
    }
    EventHandler<MouseEvent> hoverEnterHandler = event -> {
        ((Button)(event.getTarget())).setStyle("-fx-background-color: #98c1d9; -fx-text-fill: #293241; -fx-border-color: #ee6c4d ; -fx-border-width: 0px 0px 0px 5px;");

    };

    EventHandler<MouseEvent> hoverExitHandler = event -> {
        ((Button)(event.getTarget())).setStyle("-fx-background-color: #3d5a80; -fx-text-fill: #d4f1f4; -fx-border-color: WHITE; -fx-border-width: 0px 0px 0px 0px;");
    };

    EventHandler<MouseEvent> emptyHandler = event -> {

    };
}

