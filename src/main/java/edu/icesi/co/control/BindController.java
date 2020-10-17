package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.view.BindWindow;
import edu.icesi.co.view.MainWindow;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;


public class BindController {

    private BindWindow view;
    private SQLConnection connection;

    public BindController(BindWindow view) {
        this.view = view;
        init();
    }

    public void init() {
        connection = SQLConnection.getInstance();
        view.getBindActionBtt().setOnAction(
                (e) -> {
                    bind();
                }
        );
        view.getHomeBtt().setOnAction(
                (e) -> {
                    MainWindow mw = new MainWindow();
                    view.close();
                    mw.show();
                }
        );
    }

    public void bind(){
        try {
            int aID = Integer.parseInt(view.getActorTxt().getText());
            int pID = Integer.parseInt(view.getMovieTxt().getText());
            connection.linkActorMovie(pID,aID);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Se han vinculado correctamente");
            alert.showAndWait();
            view.getActorTxt().setText("");
            view.getMovieTxt().setText("");
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Por favor utiliza IDs válidos");
            alert.showAndWait();
        }
    }
}
