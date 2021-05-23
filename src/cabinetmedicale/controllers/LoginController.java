/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.services.CabinetService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton submit;

    @FXML
    private JFXButton LoginSignupButton;
    
    public static int userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CabinetService cabinetS = new CabinetService();
        LoginSignupButton.setOnAction(event -> {
            LoginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cabinetmedicale/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
        });

        submit.setOnAction(event -> {

            try {
                if (cabinetS.login(email.getText(), password.getText()).size() == 1) {
                    
                    userId = cabinetS.login(email.getText(), password.getText()).get(0).getId();
                    System.out.println(userId + "login");
                    showListPatientScreen(userId);
                } else {

                    modal("Invalid input passed !!!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    void modal(String text) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/cabinetmedicale/view/message.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        MessageController message = loader.getController();

        message.setText(text);

        stage.showAndWait();
    }

    void showListPatientScreen(int userId) {
        submit.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/cabinetmedicale/view/listPatient.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        
        stage.showAndWait();

    }

}
