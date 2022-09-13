package com.example.main_project;

import Client.ReadThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

}
