/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class MessageController implements Initializable {

    @FXML
    private Label text;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
        close.setOnAction(event -> {
            closeButtonAction();
        });
    }

    void setText(String message) {
        text.setText(message);
    }

    private void closeButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) close.getScene().getWindow();
        
        // do what you have to do
        stage.close();
    }

}
