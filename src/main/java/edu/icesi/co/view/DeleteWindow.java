package edu.icesi.co.view;

import edu.icesi.co.control.DeleteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteWindow extends Stage {

    private DeleteController dc;
    private Button homeBtt;
    private Button deleteActionBtt;
    private ChoiceBox optionsBox;

    public DeleteWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("DeleteWindow.fxml"));
            Parent root = loader.load();

            optionsBox = (ChoiceBox) loader.getNamespace().get("optionsBox");
            homeBtt = (Button) loader.getNamespace().get("homeBtt");
            deleteActionBtt = (Button) loader.getNamespace().get("deleteActionBtt");

            optionsBox.getItems().add("Eliminar Género");
            optionsBox.getItems().add("Eliminar Película");
            optionsBox.getItems().add("Eliminar Actor");

            Scene scene = new Scene(root);
            this.setScene(scene);

            dc = new DeleteController(this);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public DeleteController getDc() {
        return dc;
    }

    public Button getHomeBtt() {
        return homeBtt;
    }

    public Button getDeleteActionBtt() {
        return deleteActionBtt;
    }

    public ChoiceBox getOptionsBox() {
        return optionsBox;
    }
}
