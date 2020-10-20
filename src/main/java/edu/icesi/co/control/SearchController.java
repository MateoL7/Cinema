package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Genero;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.MainWindow;
import edu.icesi.co.view.SearchWindow;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class SearchController {

    private SearchWindow view;
    private SQLConnection connection;

    public SearchController(SearchWindow view) {
        this.view = view;
        init();
    }

    public void init() {
        connection = SQLConnection.getInstance();
        view.getSearchActionBtt().setOnAction(
                (e) -> {
                    search();
                }
        );
        view.getHomeBtt().setOnAction(
                (e) -> {
                    MainWindow mw = new MainWindow();
                    view.close();
                    mw.show();
                }
        );
        view.setOnCloseRequest((e) -> {
            connection.close();
        });
        view.getOptionsBox().setOnAction((e) -> {
            fillLists(view.getOptionsBox().getValue() + "");
        });
    }

    public void search() {
        try {
            ArrayList<Pelicula> movies = new ArrayList<>();
            ArrayList<Actor> actors = new ArrayList<>();
            String choice = (String) view.getOptionsBox().getValue();
            String option = view.getComboBox().getValue() + "";
            String[] parts = option.split("ID: ");
            int id = Integer.parseInt(parts[1]);
            switch (choice) {
                case "Películas por género":
                    movies = connection.getMoviesByGenre(id);
                    view.getInfoList().getItems().clear();
                    if (movies.isEmpty()) {
                        view.getInfoList().getItems().add("NO HAY PELÍCULAS DE ESTE GÉNERO");
                    } else {
                        for (int i = 0; i < movies.size(); i++) {
                            view.getInfoList().getItems().add(movies.get(i).getNombre());
                        }
                    }
                    break;
                case "Películas de un actor":
                    movies = connection.getMoviesByActor(id);
                    view.getInfoList().getItems().clear();
                    if (movies.isEmpty()) {
                        view.getInfoList().getItems().add("ESTE ACTOR NO TIENE PELÍCULAS");
                    } else {
                        for (int i = 0; i < movies.size(); i++) {
                            view.getInfoList().getItems().add(movies.get(i).getNombre());
                        }
                    }
                    break;
                case "Actores de una película":
                    actors = connection.getActorsByMovie(id);
                    view.getInfoList().getItems().clear();
                    if (actors.isEmpty()) {
                        view.getInfoList().getItems().add("ESTA PELÍCULA NO TIENE ACTORES");
                    } else {
                        for (int i = 0; i < actors.size(); i++) {
                            view.getInfoList().getItems().add(actors.get(i).getNombre());
                        }
                    }
                    break;
            }
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
        }
    }

    public void fillLists(String option) {
        view.getComboBox().getItems().clear();
        view.getComboBox().setVisible(true);
        switch (option) {
            case "Películas por género":
                ArrayList<Genero> gens = connection.getAllGenres();
                for (int i = 0; i < gens.size(); i++) {
                    view.getComboBox().getItems().add(gens.get(i).getTipo() + "-> ID: " + gens.get(i).getId());
                }
                break;
            case "Películas de un actor":
                ArrayList<Actor> actors = connection.getAllActors();
                for (int i = 0; i < actors.size(); i++) {
                    view.getComboBox().getItems().add(actors.get(i).getNombre() + "-> ID: " + actors.get(i).getId());
                }
                break;
            case "Actores de una película":
                ArrayList<Pelicula> movies = connection.getAllMovies();
                for (int i = 0; i < movies.size(); i++) {
                    view.getComboBox().getItems().add(movies.get(i).getNombre() + "-> ID: " + movies.get(i).getId());
                }
                break;
        }
    }
}
