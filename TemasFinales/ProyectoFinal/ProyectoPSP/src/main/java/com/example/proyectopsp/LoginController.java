package com.example.proyectopsp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController  implements Initializable {


    @FXML
    private Button boton_aceptar;

    @FXML
    private TextField texto_contrasenia;

    @FXML
    private TextField texto_nombre;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boton_aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("pantalla-principal.fxml"));
                try {
                    Parent root = loader.load();
                    Scene scene = new Scene(root,600,400);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    PrincipalController controller = loader.getController();
                    controller.setController(LoginController.this);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
