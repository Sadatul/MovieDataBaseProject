package com.example.main_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DashBoard extends RecentMovies {
//    @FXML
//    private HBox titleBar;
//
//    @FXML
//    private ScrollPane scrollPane;

    @FXML
    private Label totalRevenue;

    @FXML
    private Label totalMovieCount;

    @FXML
    private Label marketShare1;

    @FXML
    private Label marketShare2;
    @FXML
    private Label secondary1;
    @FXML
    private Label secondary2;


    @FXML
    private Label marketSharebymc1;

    @FXML
    private Label marketSharebymc2;

//    private ArrayList<Movie> movies;
//
//    private Map<String, Integer> map;
    private long revenue;
    private int movieCount;
    private double marketShare;
    private double marketSharebymc;

    String[] arr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arr = new String[1];
//        map = new HashMap<>();
////        new ReadThread(ClientApplication.client.getSocketWrapper(), map, this);
        MainMenuController.readThread.setData(arr);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("DASH");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData()
    {
        String[] s = arr[0].split(",");
        revenue = Long.parseLong(s[0]);
        movieCount = Integer.parseInt(s[1]);
        marketShare = Double.parseDouble(s[2]);
        marketSharebymc = Double.parseDouble(s[3]);
    }
    public void print()
    {
//        System.out.println(arr[0]);
        totalRevenue.setText(Long.toString(revenue));
        totalMovieCount.setText(Integer.toString(movieCount));
//        System.out.println(marketSharebymc);
        double width1 = Math.ceil(marketShare * marketShare1.getPrefWidth());
        double width2 = Math.ceil(marketSharebymc * marketSharebymc1.getPrefWidth());
        marketShare2.setPrefWidth(width1);
        if(width1 > 30)
        {
            marketShare2.setText(Double.toString(Math.ceil(marketShare * 100)) + "%");
        }
        else
        {
            secondary1.setText(Double.toString(Math.ceil(marketShare * 100)) + "%");
        }
        marketSharebymc2.setPrefWidth(width2);
        if(width2 > 50)
        {
            marketSharebymc2.setText(Double.toString(Math.ceil(marketSharebymc * 100)) + "%");
        }
        else
        {
            secondary2.setText(Double.toString(Math.ceil(marketSharebymc * 100)) + "%");
        }
//        int j = 1;
//        for (Map.Entry<String, Integer> entry: map.entrySet())
//        {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hBOXforCompanyCount.fxml"));
//            Node root;
//            try {
//                root = (Node) fxmlLoader.load();
//                ((hBOXforComapanyCountFXML) fxmlLoader.getController()).setFields(j, entry.getKey(), entry.getValue());
//                vBox.getChildren().add(root);
//                j++;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    public void reload(ActionEvent event) {

//        vBox.getChildren().clear();
//
//        map = new HashMap<>();
////        new ReadThread(ClientApplication.client.getSocketWrapper(), map, this);
//        MainMenuController.readThread.setData(map);
//        MainMenuController.readThread.setReleaseYearFxml(this);
//        try {
//            ClientApplication.client.getSocketWrapper().write("4");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        arr = new String[1];
//        map = new HashMap<>();
////        new ReadThread(ClientApplication.client.getSocketWrapper(), map, this);
        MainMenuController.readThread.setData(arr);
        MainMenuController.readThread.setReleaseYearFxml(this);
        try {
            ClientApplication.client.getSocketWrapper().write("DASH");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
