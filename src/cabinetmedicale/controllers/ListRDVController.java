/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cabinetmedicale.controllers;

import cabinetmedicale.entities.PateintRDV;
import cabinetmedicale.entities.RDV;
import cabinetmedicale.services.PatientService;
import cabinetmedicale.services.RDVService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ListRDVController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView add;

    @FXML
    private JFXListView<PateintRDV> list;

    @FXML
    private DatePicker date;

    @FXML
    void initialize() {

    }

    int userId = LoginController.userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Date d = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = DateFor.format(d);

        RDVService rdvService = new RDVService();

        try {
            list.setItems(rdvService.findByCabinetId(userId, stringDate));
            list.setCellFactory(param -> new ItemCellRDVController());
        } catch (SQLException ex) {
            Logger.getLogger(ListPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        date.addEventHandler(ActionEvent.ACTION, event -> {
            try {
                list.setItems(rdvService.findByCabinetId(userId, date.getValue().toString()));
                list.setCellFactory(param -> new ItemCellRDVController());
            } catch (SQLException ex) {
                Logger.getLogger(ListPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        add.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println(userId + "liste patient");
            add.getScene().getWindow().hide();
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

            stage.show();

        });
    }

}
