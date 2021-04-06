package com.habravanEnterprise.fitnessForLife.gui.guest;

import com.habravanEnterprise.fitnessForLife.Main;
import com.habravanEnterprise.fitnessForLife.models.Client;
import com.habravanEnterprise.fitnessForLife.services.ClientMockIntf;
import com.habravanEnterprise.fitnessForLife.services.impl.ClientMockImpl;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputIntf;
import com.habravanEnterprise.fitnessForLife.services.impl.FileInputOutputCsvImpl;
import java.util.List;
import com.habravanEnterprise.fitnessForLife.gui.ClientFormIntf;
import com.habravanEnterprise.fitnessForLife.models.RegisterMesage;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputMsgIntf;
import com.habravanEnterprise.fitnessForLife.services.impl.FileInputOutputMsgImpl;

/**
 * FXML Controller class
 *
 * @author Dorina
 */
public class FXMLClientFormController implements Initializable, ClientFormIntf {

    @FXML
    private AnchorPane panoAnchorPane;
    @FXML
    private TextField tfFullname;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private DatePicker dpDateofBirth;
    @FXML
    private TextField tfLocation;
    @FXML
    private Button btnSubmitYourForm;

    private Main mainApp;
    @FXML
    private Button btnResetForm;

    public void setApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    private ClientMockIntf clientMock;
    private FileInputOutputIntf fileIOService;
    private String fileName = "clients.csv";

    private FileInputOutputMsgIntf fileIOMsgService;
    private String fileNameMsg = "msg.csv";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fileIOService = new FileInputOutputCsvImpl();
        List<Client> list = fileIOService.readFile(fileName);
        if (list == null) {
            clientMock = new ClientMockImpl();
        } else {
            clientMock = new ClientMockImpl(list);
        }

        fileIOMsgService = new FileInputOutputMsgImpl();

    }

    @FXML
    private void handleButtonSUBMITYOURFORM(ActionEvent event) {

        Client client = readForm();
        clientMock.save(client);
        List<Client> listaClienti = clientMock.findAll();
        fileIOService.save(fileName, listaClienti);

        RegisterMesage msg = new RegisterMesage();
        msg.setText(tfFullname.getText() + ": " + textArea.getText());
        fileIOMsgService.save(fileNameMsg, msg);

        // System.out.println("The form has been sent successfully!  " + guest); - poate sa imi trebuiasca mai tirziu pentru a vedea in consola.
        showConfirmationDialog("You are choosing the right path!\n"
                + "Thank you for getting in touch with us!\n"
                + "\n"
                + " One of our customer happiness members will be getting back to you within a few hours.\n"
                + "\n"
                + "In the meantime, you can find out more information on our website:\n"
                + "https://www.puregym.com\n"
                + "Have a great day !");

        clearForm();

    }

    @FXML
    private void handleButtonBackAction(ActionEvent event) {
        mainApp.showStartPageScene();
    }

    @FXML
    private void handleButtonResetForm(ActionEvent event) {
        clearForm();

    }

    @Override
    public void clearForm() {

        tfFullname.setText("");
        tfEmailAddress.setText("");
        dpDateofBirth.setValue(null);
        tfPhoneNumber.setText("");
        tfLocation.setText("");
        textArea.setText("");

    }

    @Override
    public Client readForm() {
        // Facem validarea.

        Client client = new Client();
        client.setCustomerid(-1);
        client.setFullname(tfFullname.getText());
        client.setEmail_address(tfEmailAddress.getText());
        client.setDate_of_birth(dpDateofBirth.getValue());
        client.setLocation(tfLocation.getText());
        client.setPhone_number(Integer.parseInt(tfPhoneNumber.getText().trim()));
        return client;
    }

    @Override
    public void fillForm(Client client) {
        tfFullname.setText(client.getFullname());
        tfEmailAddress.setText(client.getEmail_address());
        dpDateofBirth.setValue(LocalDate.parse(client.getDate_of_birth().toString()));
        tfPhoneNumber.setText("" + client.getPhone_number());
        tfLocation.setText(client.getLocation());

    }

    public void showConfirmationDialog(String textInfo) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("EXCUSES DON'T BURN CALORIES");
        alert.setHeaderText("You are choosing the right path!\n"
                + "Thank you for getting in touch with us!\n"
                + "\n"
                + " One of our customer happiness members will be getting back to you within a few hours.\n"
                + "\n"
                + "In the meantime, you can find out more information on our website:\n"
                + "https://www.puregym.com\n"
                + "Have a great day !");
        alert.setContentText("PureGym Team");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

}
