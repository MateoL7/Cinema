package edu.icesi.co.view;

import edu.icesi.co.control.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Stage {

    private MainController mc;
    private Button registerBtt;
    private Button deleteBtt;
    private Button bindBtt;
    private Button searchBtt;
    private Button moviesBtt;
    private TextArea infoArea;

    public MainWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainWindow.fxml"));
            Parent root = loader.load();

            registerBtt = (Button) loader.getNamespace().get("registerBtt");
            deleteBtt = (Button) loader.getNamespace().get("deleteBtt");
            bindBtt = (Button) loader.getNamespace().get("bindBtt");
            searchBtt = (Button) loader.getNamespace().get("searchBtt");
            moviesBtt = (Button) loader.getNamespace().get("moviesBtt");
            infoArea = (TextArea) loader.getNamespace().get("infoArea");

            Scene scene = new Scene(root);
            this.setScene(scene);

            mc = new MainController(this);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public MainController getMc() {
        return mc;
    }

    public Button getRegisterBtt() {
        return registerBtt;
    }

    public Button getDeleteBtt() {
        return deleteBtt;
    }

    public Button getBindBtt() {
        return bindBtt;
    }

    public Button getSearchBtt() {
        return searchBtt;
    }

    public Button getMoviesBtt() {
        return moviesBtt;
    }

    public TextArea getInfoArea() {
        return infoArea;
    }
}
