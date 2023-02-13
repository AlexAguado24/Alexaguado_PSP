package com.example.proyectopsp;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    private LoginController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setController(LoginController controller){
        this.controller = controller;
    }

}
