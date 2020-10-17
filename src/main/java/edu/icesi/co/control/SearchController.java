package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.MainWindow;
import edu.icesi.co.view.SearchWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Optional;

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
    }

    public void search(){
        try {
            ArrayList<Pelicula> movies = new ArrayList<>();
            ArrayList<Actor> actors = new ArrayList<>();
            String choice = (String) view.getOptionsBox().getValue();
            switch (choice) {
                case "Películas por género":
                    int gen = -1;
                    TextInputDialog genreT = new TextInputDialog();
                    genreT.setHeaderText("¿Cuál género?");
                    Optional<String> g = genreT.showAndWait();
                    if (g.isPresent()) {
                        gen = Integer.parseInt(g.get());
                    }
                    movies = connection.getMoviesByGenre(gen);
                    view.getInfoList().getItems().clear();
                    for(int i = 0; i < movies.size();i++){
                        view.getInfoList().getItems().add(movies.get(i).getNombre());
                    }
                    break;
                case "Películas de un actor":
                    int a = -1;
                    TextInputDialog actorT = new TextInputDialog();
                    actorT.setHeaderText("¿Cuál actor?");
                    Optional<String> ac = actorT.showAndWait();
                    if (ac.isPresent()) {
                        a = Integer.parseInt(ac.get());
                    }
                    movies = connection.getMoviesByActor(a);
                    view.getInfoList().getItems().clear();
                    for(int i = 0; i < movies.size();i++){
                        view.getInfoList().getItems().add(movies.get(i).getNombre());
                    }
                    break;
                case "Actores de una película":
                    int p = -1;
                    TextInputDialog movieT = new TextInputDialog();
                    movieT.setHeaderText("¿Cuál película?");
                    Optional<String> mov = movieT.showAndWait();
                    if (mov.isPresent()) {
                        p = Integer.parseInt(mov.get());
                    }
                    actors = connection.getActorsByMovie(p);
                    view.getInfoList().getItems().clear();
                    for(int i = 0; i < actors.size();i++){
                        view.getInfoList().getItems().add(actors.get(i).getNombre());
                    }
                    break;
            }
        } catch (NumberFormatException n) {
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
