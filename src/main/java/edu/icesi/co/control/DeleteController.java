package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.view.DeleteWindow;
import edu.icesi.co.view.MainWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

import java.util.Optional;

public class DeleteController {

    private DeleteWindow view;
    private SQLConnection connection;

    public DeleteController(DeleteWindow view) {
        this.view = view;
        init();
    }

    public void init() {
        connection = SQLConnection.getInstance();
        view.getHomeBtt().setOnAction(
                (e) -> {
                    MainWindow mw = new MainWindow();
                    view.close();
                    mw.show();
                }
        );
        view.getDeleteActionBtt().setOnAction(
                (e) -> {
                    delete();
                }
        );
    }

    public void delete() {
        try {
            String choice = (String) view.getOptionsBox().getValue();
            String msg = "";
            switch (choice) {
                case "Eliminar Género":
                    int gen = -1;
                    TextInputDialog genreT = new TextInputDialog();
                    genreT.setHeaderText("¿Cuál género deseas eliminar?");
                    Optional<String> g = genreT.showAndWait();
                    if (g.isPresent()) {
                        gen = Integer.parseInt(g.get());
                    }
                    connection.deleteGenre(gen);
                    msg = "El género con id: " + gen + " ha sido eliminado";
                    break;
                case "Eliminar Película":
                    int p = -1;
                    TextInputDialog movieT = new TextInputDialog();
                    movieT.setHeaderText("¿Cuál película?");
                    Optional<String> mov = movieT.showAndWait();
                    if (mov.isPresent()) {
                        p = Integer.parseInt(mov.get());
                    }
                    connection.deleteMovie(p);
                    msg = "La película con id: " + p + " ha sido eliminada";
                    break;
                case "Eliminar Actor":
                    int a = -1;
                    TextInputDialog actorT = new TextInputDialog();
                    actorT.setHeaderText("¿Cuál actor deseas eliminar?");
                    Optional<String> ac = actorT.showAndWait();
                    if (ac.isPresent()) {
                        a = Integer.parseInt(ac.get());
                    }
                    connection.deleteActor(a);
                    msg = "El actor con id: " + a + " ha sido eliminado";
                    break;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(msg);
            alert.showAndWait();
        }  catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Por favor usar un ID válido");
            alert.showAndWait();
        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Elige lo que quieres buscar");
            alert.showAndWait();
        }
    }

}
