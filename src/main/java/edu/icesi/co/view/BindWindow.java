package edu.icesi.co.view;

import edu.icesi.co.control.BindController;
import edu.icesi.co.control.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BindWindow extends Stage {
    private TextField actorTxt;
    private TextField movieTxt;
    private Button bindActionBtt;
    private Button homeBtt;

    private BindController bc;

    public BindWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BindWindow.fxml"));
            Parent root = loader.load();

            actorTxt = (TextField) loader.getNamespace().get("actorTxt");
            movieTxt = (TextField) loader.getNamespace().get("movieTxt");
            bindActionBtt = (Button) loader.getNamespace().get("bindActionBtt");
            homeBtt = (Button) loader.getNamespace().get("homeBtt");

            Scene scene = new Scene(root);
            this.setScene(scene);

            bc = new BindController(this);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public TextField getActorTxt() {
        return actorTxt;
    }

    public TextField getMovieTxt() {
        return movieTxt;
    }

    public Button getBindActionBtt() {
        return bindActionBtt;
    }

    public Button getHomeBtt() {
        return homeBtt;
    }

    public BindController getBc() {
        return bc;
    }
}
