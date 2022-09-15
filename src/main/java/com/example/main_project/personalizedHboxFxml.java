package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class personalizedHboxFxml {

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @FXML
    private Label column1;
    @FXML
    private Label column2;
    @FXML
    private Button column3;

    @FXML
    private Label siNo;
    @FXML
    private HBox hBox;
    private boolean showTransfer  = true;
    public void setShowTransfer(boolean bol)
    {
        showTransfer = bol;
    }
    public void showMoreInfo(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("moreInfo.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            ((MoreInfo)fxmlLoader.getController()).setFields(movie, stage);
            if(!showTransfer)
            {
                ((MoreInfo)fxmlLoader.getController()).noTransferButton();
            }
            stage.setTitle("More Info");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFields(int i)
    {
        if(i % 2 == 0)
        {
            hBox.setStyle("-fx-background-color:  #2E8BC0");
            column1.setStyle("-fx-text-fill: #ffffff");
            column2.setStyle("-fx-text-fill: #ffffff");;
        }
        siNo.setText(Integer.toString(i));
        column1.setText(movie.getTitle());
        String genres = String.join(", ", movie.getGenre());
        column2.setText(genres);
    }

}
