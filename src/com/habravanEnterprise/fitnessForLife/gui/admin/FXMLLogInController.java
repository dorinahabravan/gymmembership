package com.habravanEnterprise.fitnessForLife.gui.admin;

import com.habravanEnterprise.fitnessForLife.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorina
 */
public class FXMLLogInController implements Initializable {

    @FXML
    private AnchorPane panoAnchorPane;
    @FXML
    private TextField tfUserID;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnCancel;
    @FXML
    private PasswordField tfPassword;
    private Main mainApp;

    public void setApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonCancelAction(ActionEvent event) {
        mainApp.showStartPageScene();

    }

    @FXML
    private void handleButtonLogInAction(ActionEvent event) {
        String user = tfUserID.getText();
        String password = tfPassword.getText();
        System.out.println("User=" + user);
        System.out.println("Password=" + password);
        // if (tfUserID.getText().equals("Admin")&& tfPassword.getText().equals("1234")) {
        if (user.equals("Admin") && password.equals("1234")) {

            mainApp.showAdminInterface();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        }

    }

}
