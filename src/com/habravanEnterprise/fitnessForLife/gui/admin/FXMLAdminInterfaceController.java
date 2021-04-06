package com.habravanEnterprise.fitnessForLife.gui.admin;

import com.habravanEnterprise.fitnessForLife.Main;
import com.habravanEnterprise.fitnessForLife.dao.ClientDaoIntf;
import com.habravanEnterprise.fitnessForLife.dao.impl.ClientDaoImpl;
import com.habravanEnterprise.fitnessForLife.gui.FormIntf;
import com.habravanEnterprise.fitnessForLife.models.Client;
import com.habravanEnterprise.fitnessForLife.models.RegisterMesage;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputIntf;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputMsgIntf;
import com.habravanEnterprise.fitnessForLife.services.impl.FileInputOutputExcelImpl;
import com.habravanEnterprise.fitnessForLife.services.impl.FileInputOutputCsvImpl;
import com.habravanEnterprise.fitnessForLife.services.impl.FileInputOutputMsgImpl;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorina
 */
public class FXMLAdminInterfaceController implements Initializable, FormIntf {

   // ClientMockIntf clientMock;
    FileInputOutputCsvImpl fileIO;
    FileInputOutputExcelImpl fileIOExcel;

    @FXML
    private AnchorPane panoAnchorPane;
    @FXML
    private TextField tfCustomerID;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfTelephoneNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnFindbyID;
    @FXML
    private Button btnFindbyName;
    @FXML
    private Button btnFindAll;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBack;
    @FXML
    private ListView<Client> listView;
    @FXML
    private Button btnViewRequests;

    private Main mainApp;
    @FXML
    private Button btnExportCSV;
    @FXML
    private Button btnImportCSV;
    @FXML
    private Button btnExportExcel;
    @FXML
    private Button btnImportExcel;

    public void setApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    private ClientDaoIntf clientDao;
    private FileInputOutputIntf fileIOService;
    private String fileName = "clients.csv";

    private FileInputOutputMsgIntf fileIOMsgService;
    private String fileNameMsg = "msg.csv";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileIO = new FileInputOutputCsvImpl();
      //  clientMock = new ClientMockImpl();
        clientDao = new ClientDaoImpl();
        fileIOExcel = new FileInputOutputExcelImpl();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client selectedClient) {
                fillForm(selectedClient);

            }
        });

        fileIOMsgService = new FileInputOutputMsgImpl();

    }

    @FXML
    private void handleButtonFindbyID(ActionEvent event) {

        try {
            Client client = clientDao.findById(Integer.parseInt(tfCustomerID.getText()));
            if (client != null) {
                fillForm(client);
                
            } else {
                System.out.println("The client with this ID does not exist=" + tfCustomerID.getText());
                
            }
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonFindbyName(ActionEvent event) {

        try {
            List<Client> list = clientDao.findByFullname(tfName.getText());
            
            if (list.size() > 0) {
                refreshListView(list);
                
            } else {
                System.out.println("The client with this name does not exist=" + tfName.getText());
            }
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonFindAll(ActionEvent event) {
        try {
            List<Client> lista = clientDao.findAll();
            for (Client client : lista) {
                System.out.println("These are the clients in the list" + client);
                
            }
            showList();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showList() {
        try {
            ObservableList<Client> items = FXCollections.observableArrayList(clientDao.findAll());
            listView.getItems().clear();
            listView.getItems().addAll(items);
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleButtonSAVE(ActionEvent event) {
        if ((tfCustomerID.getText().isEmpty()) || (tfName.getText().isEmpty())) {
            showConfirmationDialog("The ID or the NAME are missing");
        } else {

            try {
                Client client = readForm();
                clientDao.save(client);
                System.out.println("The client has been saved!  " + client);
                
                clearForm();
                listView.getItems().add(client);
            } catch (Exception ex) {
                Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleButtonUPDATE(ActionEvent event) {
        try {
            Client client = (Client) readForm();
            clientDao.update(client);
            clearForm();
            showList();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleButtonCLEAR(ActionEvent event) {
        clearForm();

    }

    @FXML
    private void handleButtonDELETE(ActionEvent event) {
        try {
            Client client = readForm();
            clientDao.delete(client);
            clearForm();
            showList();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonBACK(ActionEvent event) {
        mainApp.showLogIn();
    }

    @FXML
    private void handleButtonExportCSV(ActionEvent event) {

        try {
            List< Client> lista = clientDao.findAll();
            fileIO.save("client.csv", lista);
            System.out.println("The export has been done successfully");
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleButtonViewRequests(ActionEvent event) {
        //1. Serviciul de citire din fisierul Citeste Mesajul
        //2.mesajul este transmis metpdei de afisare ca argument
        //3 arata mesajul
        String textMessage = "no new meesage";

        try {
            RegisterMesage registerMessage = fileIOMsgService.readFile(fileNameMsg);
            textMessage = registerMessage.getDate() + "\t" + registerMessage.getText();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        showMesageNotification(textMessage);
        fileIOMsgService.save(fileNameMsg, new RegisterMesage());

    }

    @FXML
    private void hndleButtonLogOutAction(ActionEvent event) {
        mainApp.showStartPageScene();
    }

    @Override
    public void clearForm() {
        tfCustomerID.setText("");
        tfName.setText("");
        tfLocation.setText("");
        tfTelephoneNumber.setText("");
        tfEmail.setText("");
        datePicker.setValue(null);

    }

    @Override
    public void fillForm(Client client) {
        tfCustomerID.setText("" + client.getCustomerid());
        tfName.setText(client.getFullname());
        tfLocation.setText(client.getLocation());
        tfTelephoneNumber.setText("" + client.getPhone_number());
        tfEmail.setText(client.getEmail_address());
        datePicker.setValue(LocalDate.parse(client.getDate_of_birth().toString()));

    }

    @Override
    public Client readForm() {
        Client client = new Client();
        client.setCustomerid(Integer.parseInt(tfCustomerID.getText()));
        client.setFullname(tfName.getText());
        client.setLocation(tfLocation.getText());
        client.setPhone_number(Integer.parseInt(tfTelephoneNumber.getText()));
        client.setEmail_address(tfEmail.getText());
        client.setDate_of_birth(datePicker.getValue());
        return client;

    }

    public void showConfirmationDialog(String textInfo) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("The ID or the NAME are missing");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void handleButtonImportCSV(ActionEvent event) {
        List<Client> lista = fileIO.readFile("client.csv");
        
       // ramine mai tirziu
       // clientDao = new ClientDaoImpl(lista);
        System.out.println("The import has been done successfully");
        showList();

    }

    @FXML
    private void handleButtonExportExcel(ActionEvent event) {
        try {
            List<Client> lista = clientDao.findAll();
            fileIOExcel.save("clientExcel.xls", lista);
            System.out.println("The export has been done successfully in Excel");
        } catch (Exception ex) {
            Logger.getLogger(FXMLAdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleButtonImportExcel(ActionEvent event) {
        List<Client> lista = fileIOExcel.readFile("clientExcel.xls");
        //clientDao = new ClientMockImpl(lista);
        System.out.println("The import has been done successfully in Excel ");
        showList();

    }

    public void showMesageNotification(String mesaj) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("New Requests");
        alert.setHeaderText("You have a new message in your Inbox from:\n"
                + "\n"
                + " DORINA HABRAVAN\n");
        alert.setContentText(mesaj);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            // ... user chose CANCEL or closed the dialog
        } else {
            // ... user chose OK
        }

    }

    private void refreshListView(List<Client> list) {
        ObservableList<Client> items = FXCollections.observableArrayList(list);
        listView.getItems().clear();
        listView.getItems().addAll(items);

    }

}
