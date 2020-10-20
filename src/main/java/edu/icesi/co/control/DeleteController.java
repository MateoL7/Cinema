package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Genero;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.DeleteWindow;
import edu.icesi.co.view.MainWindow;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.util.ArrayList;

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
        view.getOptionsBox().setOnAction((e) -> {
            fillLists(view.getOptionsBox().getValue() + "");
        });
        view.setOnCloseRequest((e) -> {
            connection.close();
        });
    }

    public void delete() {
        try {
            String option = view.getComboBox().getValue() + "";
            String toDelete = view.getOptionsBox().getValue() + "";
            String[] parts = option.split("ID: ");
            int id = Integer.parseInt(parts[1]);
            String msg = "";
            switch (toDelete) {
                case "Eliminar Género":
                    connection.deleteGenre(id);
                    msg = "El género con id: " + id + " ha sido eliminado";
                    fillLists(toDelete);
                    break;
                case "Eliminar Película":
                    connection.deleteMovie(id);
                    msg = "La película con id: " + id + " ha sido eliminada";
                    fillLists(toDelete);
                    break;
                case "Eliminar Actor":
                    connection.deleteActor(id);
                    msg = "El actor con id: " + id + " ha sido eliminado";
                    fillLists(toDelete);
                    break;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(msg);
            alert.showAndWait();
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Por favor usar un ID válido");
            alert.showAndWait();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Elige lo que quieres buscar");
            alert.showAndWait();
        }catch (ArrayIndexOutOfBoundsException a){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Elige cuál quieres eliminar");
            alert.showAndWait();
        }
    }

    public void fillLists(String option) {
        view.getComboBox().getItems().clear();
        view.getComboBox().setVisible(true);
        switch (option) {
            case "Eliminar Género":
                ArrayList<Genero> gens = connection.getAllGenres();
                for (int i = 0; i < gens.size(); i++) {
                    view.getComboBox().getItems().add(gens.get(i).getTipo() + "-> ID: " + gens.get(i).getId());
                }
                break;
            case "Eliminar Película":
                ArrayList<Pelicula> movies = connection.getAllMovies();
                for (int i = 0; i < movies.size(); i++) {
                    view.getComboBox().getItems().add(movies.get(i).getNombre() + "-> ID: " + movies.get(i).getId());
                }
                break;
            case "Eliminar Actor":
                ArrayList<Actor> actors = connection.getAllActors();
                for (int i = 0; i < actors.size(); i++) {
                    view.getComboBox().getItems().add(actors.get(i).getNombre() + "-> ID: " + actors.get(i).getId());
                }
                break;
        }

    }

}
