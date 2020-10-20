package edu.icesi.co.view;

import edu.icesi.co.control.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterWindow extends Stage {

    private ComboBox optionsBox;
    private ComboBox genresBox;
    private TextField nameTxt;
    private Button registerActionBtt;
    private Button homeBtt;

    private RegisterController rc;

    public RegisterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RegisterWindow.fxml"));
            Parent root = loader.load();

            optionsBox = (ComboBox) loader.getNamespace().get("optionsBox");
            genresBox = (ComboBox) loader.getNamespace().get("genresBox");
            nameTxt = (TextField) loader.getNamespace().get("nameTxt");
            registerActionBtt = (Button) loader.getNamespace().get("registerActionBtt");
            homeBtt = (Button) loader.getNamespace().get("homeBtt");

            genresBox.setVisible(false);
            genresBox.setPromptText("Género");

            optionsBox.getItems().add("Género");
            optionsBox.getItems().add("Película");
            optionsBox.getItems().add("Actor");

            Scene scene = new Scene(root);
            this.setTitle("Registrar");
            this.setScene(scene);

            rc = new RegisterController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ComboBox getOptionsBox() {
        return optionsBox;
    }

    public RegisterController getRc() {
        return rc;
    }

    public TextField getNameTxt() {
        return nameTxt;
    }

    public Button getRegisterActionBtt() {
        return registerActionBtt;
    }

    public Button getHomeBtt() {
        return homeBtt;
    }

    public ComboBox getGenresBox() {
        return genresBox;
    }
}
