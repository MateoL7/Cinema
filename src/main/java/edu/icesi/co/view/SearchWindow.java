package edu.icesi.co.view;
import edu.icesi.co.control.SearchController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchWindow extends Stage {

    private ChoiceBox optionsBox;
    private Button homeBtt;
    private Button searchActionBtt;
    private ListView infoList;

    private SearchController sc;

    public SearchWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SearchWindow.fxml"));
            Parent root = loader.load();

            optionsBox = (ChoiceBox) loader.getNamespace().get("optionsBox");
            homeBtt = (Button) loader.getNamespace().get("homeBtt");
            searchActionBtt = (Button) loader.getNamespace().get("searchActionBtt");
            infoList = (ListView) loader.getNamespace().get("infoList");

            optionsBox.getItems().add("Películas por género");
            optionsBox.getItems().add("Películas de un actor");
            optionsBox.getItems().add("Actores de una película");

            Scene scene = new Scene(root);
            this.setScene(scene);

            sc = new SearchController(this);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ChoiceBox getOptionsBox() {
        return optionsBox;
    }

    public Button getHomeBtt() {
        return homeBtt;
    }

    public SearchController getSc() {
        return sc;
    }

    public Button getSearchActionBtt() {
        return searchActionBtt;
    }

    public ListView getInfoList() {
        return infoList;
    }
}
