package com.habravanEnterprise.fitnessForLife.gui.startPage;

import com.habravanEnterprise.fitnessForLife.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorina
 */
public class FXMLStartPageSceneController implements Initializable {

    @FXML
    private AnchorPane panoAnchorPane;
    @FXML
    private Label lLegaturaButoane;
    @FXML
    private Button btnAdministrator;
    @FXML
    private Button btnGuest;

    private Main mainApp;

    public void setApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleButtonOpenGuestEnquiryForm(ActionEvent event) {
        mainApp.showClientForm();

    }

    @FXML
    private void handleButtonOpenAdminLogIn(ActionEvent event) {
        mainApp.showLogIn();

    }

}//end class
