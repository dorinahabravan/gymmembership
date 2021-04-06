package com.habravanEnterprise.fitnessForLife;
//com/habravanEnterprise/fitnessForLife/images/GymImageBackG.jpg

import com.habravanEnterprise.fitnessForLife.gui.admin.FXMLAdminInterfaceController;
import com.habravanEnterprise.fitnessForLife.gui.admin.FXMLLogInController;
import com.habravanEnterprise.fitnessForLife.gui.guest.FXMLClientFormController;
import com.habravanEnterprise.fitnessForLife.gui.startPage.FXMLStartPageSceneController;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Dorina
 */
public class Main extends Application {
    
    

    private Stage stage;
    // private final double MINIMUM_WINDOW_WIDTH = 1200.0;
    // private final double MINIMUM_WINDOW_HEIGHT = 1000.0;

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Gym membership application");
            //stage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
            // stage.setMinHeight(MINIMUM_WINDOW_WIDTH);
            showStartPageScene();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void showStartPageScene() {

        try {
            FXMLStartPageSceneController aFXMLStartPageSceneController = (FXMLStartPageSceneController) replaceSceneContent("/com/habravanEnterprise/fitnessForLife/gui/startPage/FXMLStartPageScene.fxml");

            aFXMLStartPageSceneController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showLogIn() {

        try {
            FXMLLogInController aFXMLLogInController = (FXMLLogInController) replaceSceneContent("/com/habravanEnterprise/fitnessForLife/gui/admin/FXMLLogIn.fxml");

            aFXMLLogInController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAdminInterface() {
        try {
            FXMLAdminInterfaceController aFXMLAdminInterfaceController = (FXMLAdminInterfaceController) replaceSceneContent("/com/habravanEnterprise/fitnessForLife/gui/admin/FXMLAdminInterface.fxml");

            aFXMLAdminInterfaceController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showClientForm() {
        try {
            FXMLClientFormController aFXMLClientFormController = (FXMLClientFormController) replaceSceneContent("/com/habravanEnterprise/fitnessForLife/gui/guest/FXMLClientForm.fxml");

            aFXMLClientFormController.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;

        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
       
       
    }
    
}


