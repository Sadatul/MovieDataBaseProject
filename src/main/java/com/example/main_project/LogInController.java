package com.example.main_project;

import Client.Client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.MovieNotFoundException;
import util.SocketWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {


    ClientApplication clientApplication;
    @FXML
    private TextField signInTextField;

    public void setClientApplication(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    @FXML
    private Label errorMessage;

    public void signIn(ActionEvent event) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Object object;
        SocketWrapper socketWrapper;
        try {
            socketWrapper = new SocketWrapper(serverAddress, serverPort);
            socketWrapper.write(signInTextField.getText());
            object = socketWrapper.read();
        } catch (Exception e) {
            errorMessage.setText("Error 404");
            return;
        }
        if (!(object instanceof MovieNotFoundException)) {
//                System.out.println(s);
//                    continue;
            ClientApplication.client.setName((String) object);
//            System.out.println(ClientApplication.client.getName());
            ClientApplication.client.setSocketWrapper(socketWrapper);
            ClientApplication.client.setGotClient(true);
            clientApplication.swapToMainMenu();
//            gotName = true;
//            System.out.println(clientName);
        }
        else
        {
            errorMessage.setText("Company Not Found");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signInTextField.setFocusTraversable(false);
//        gotName = false;
    }
//    public SocketWrapper getSocketWrapper()
//    {
//        return clientSocket;
//    }

//    public String getClientName()
//    {
//        return clientName;
//    }
//
//    public boolean isGotName()
//    {
//        return gotName;
//    }
}
