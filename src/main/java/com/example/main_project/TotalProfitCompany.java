package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TotalProfitCompany extends RecentMovies {
    @FXML
    private Label totalProfit;
//    @FXML
//    private ScrollPane scrollPane;

    //    @FXML
//    private Label bigLabel;
    private long[] arr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalProfit.setOnMouseEntered(hoverEnterHandler);
        totalProfit.setOnMouseExited(hoverExitHandler);
        arr = new long[1];
//        new ReadThread(ClientApplication.client.getSocketWrapper(), arr, this);
        MainMenuController.readThread.setData(arr);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("3");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void print()
    {
        int j = 1;
        totalProfit.setText(Long.toString(arr[0]));
    }

    public void reload(ActionEvent event) {
        arr = new long[1];
//        new ReadThread(ClientApplication.client.getSocketWrapper(), arr, this);
        MainMenuController.readThread.setData(arr);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("3");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    EventHandler<MouseEvent> hoverEnterHandler = event -> {
        ((Label)(event.getTarget())).setStyle("-fx-background-color: #293241; -fx-text-fill: #ee6c4d; -fx-font-weight: bold; -fx-font-size: 24;");

    };

    EventHandler<MouseEvent> hoverExitHandler = event -> {
        ((Label)(event.getTarget())).setStyle("-fx-background-color: #3d5a80; -fx-text-fill: #d4f1f4;");
    };

}
