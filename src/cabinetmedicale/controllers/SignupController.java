/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.Cabinet;
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
public class SignupController implements Initializable {

    @FXML
    private JFXTextField nom;
    private JFXTextField specialite;
    @FXML
    private JFXPasswordField password;
    private JFXPasswordField cpassword;
    @FXML
    private JFXButton submit;

    private JFXTextField email;

    @FXML
    private JFXButton LoginSignupButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LoginSignupButton.setOnAction(event -> {
            LoginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/cabinetmedicale/view/login.fxml"));

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

            if (!nom.getText().equals("") || !specialite.getText().equals("") || !password.getText().equals("") || !password.getText().equals(cpassword.getText()) || !email.getText().equals("")) {

                try {
                    signup(nom.getText(), specialite.getText(), password.getText(), email.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                modal("Vérifier vos inputs");
            }

        });
    }

    void signup(String nom, String specialite, String password, String email) throws SQLException {
        CabinetService cabinetS = new CabinetService();
        Cabinet cabinet = new Cabinet(nom, specialite, password, email);
        if (cabinetS.find(email).size() == 0) {
            try {
                cabinetS.ajouter(cabinet);
                modal("Compte crée avec succée");
            } catch (SQLException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            modal("Utilisateur exist");
        }

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

}
