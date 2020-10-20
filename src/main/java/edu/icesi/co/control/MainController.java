package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.*;
import javafx.application.Platform;

import java.util.ArrayList;

public class MainController {

    private MainWindow view;
    private SQLConnection connection;

    public MainController(MainWindow view) {
        this.view = view;
        init();
    }

    public void init() {
        connection = SQLConnection.getInstance();
        view.getRegisterBtt().setOnAction(

                (e) -> {
                    RegisterWindow rw = new RegisterWindow();
                    rw.show();
                    view.close();
                }

        );
        view.getBindBtt().setOnAction(
                (e)->{
                    BindWindow bw = new BindWindow();
                    bw.show();
                    view.close();
                }
        );
        view.getSearchBtt().setOnAction(
                (e)->{
                    SearchWindow sw = new SearchWindow();
                    sw.show();
                    view.close();
                }
        );
        view.getMoviesBtt().setOnAction(
                (e)->{
                    getAllInfo();
                }
        );
        view.getDeleteBtt().setOnAction(
                (e)->{
                    DeleteWindow dw = new DeleteWindow();
                    dw.show();
                    view.close();
                }
        );
        view.setOnCloseRequest((e)->{
            connection.close();
        });
    }
    public void getAllInfo(){
        ArrayList<Pelicula> movies = connection.getAllMovies();
        int gen = -1;
        String genero = "";
        String title = "";
        for (int i = 0; i < movies.size();i++){
            title = movies.get(i).getNombre();
            ArrayList<Actor> actors = connection.getActorsByMovie(movies.get(i).getId());
            gen = movies.get(i).getGeneroID();
            genero = connection.getGenre(gen);
            String msg = "\nTitulo: " + title + " / Genero: " + genero + " / Actores: ";
            for (int j = 0; j < actors.size(); j++){
                msg += "\n" + actors.get(j).getNombre();
            }
            msg+="\n---------------------------------------------------";
            view.getInfoArea().appendText(msg);
        }
    }
}
