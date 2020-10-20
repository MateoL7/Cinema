package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.BindWindow;
import edu.icesi.co.view.MainWindow;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import java.util.ArrayList;


public class BindController {

    private BindWindow view;
    private SQLConnection connection;

    private ArrayList<Actor> actors;
    private ArrayList<Pelicula> movies;

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
        fillLists();
        view.getListActors().setOnAction(
                (e)-> {
                    updateActors();
                });
        view.getListMovies().setOnAction(
                (e)-> {
                    updateMovies();
                });
        view.setOnCloseRequest((e)->{
            connection.close();
        });
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
    public void fillLists(){
        actors = connection.getAllActors();
        movies = connection.getAllMovies();
        for(int i = 0; i < actors.size(); i++){
            view.getListActors().getItems().add(""+actors.get(i).getNombre()+" -> ID: "+ actors.get(i).getId());
        }
        for(int i = 0; i < movies.size(); i++){
            view.getListMovies().getItems().add(""+movies.get(i).getNombre()+" -> ID: "+ movies.get(i).getId());
        }
    }
    public void updateActors(){
        String choiceA = view.getListActors().getValue()+"";
        String[] partsA = choiceA.split("ID: ");
        view.getActorTxt().setText(partsA[1]);
    }
    public void updateMovies(){
        String choiceB = view.getListMovies().getValue()+"";
        String[] partsB = choiceB.split("ID: ");
        view.getMovieTxt().setText(partsB[1]);
    }

}
