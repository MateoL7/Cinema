package edu.icesi.co.main;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Genero;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class Launcher extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindow mw = new MainWindow();
        mw.show();

//        connection.insertGenero(new Genero(-1,"Terror"));
//        connection.insertActor(new Actor(-1,"Tom Cruise"));
//        connection.insertActor(new Actor(-1,"Jason King"));
//        connection.insertActor(new Actor(-1,"Brian Rey"));
//        connection.insertPelicula(new Pelicula(-1,"Viernes 13",1));
//        connection.insertPelicula(new Pelicula(-1,"Amanecer",2));
//        connection.insertPelicula(new Pelicula(-1,"En la oscuridad",1));

//        connection.linkActorMovie(1,1);
//        connection.linkActorMovie(1,2);
//        connection.linkActorMovie(1,3);
//        connection.linkActorMovie(1,4);
//        connection.linkActorMovie(2,3);
//        connection.linkActorMovie(3,1);
//        connection.linkActorMovie(3,3);
//        connection.linkActorMovie(3,2);

//        ArrayList<Actor> actors = connection.getActorsByMovie(2);
//        ArrayList<Actor> actors = connection.getAllActors();
//        System.out.println(actors.size());
//        for(int i = 0; i<actors.size(); i++){
//            System.out.println(actors.get(i).getNombre()) ;
//        }

//        ArrayList<Pelicula> movies = connection.getMoviesByGender(1);
//        System.out.println(movies.size());
//        for(int i = 0; i<movies.size(); i++){
//            System.out.println(movies.get(i).getNombre()) ;
//        }

//        ArrayList<Pelicula> movies = connection.getMoviesByActor(1);
//        ArrayList<Pelicula> movies = connection.getAllMovies();
//        System.out.println(movies.size());
//        for(int i = 0; i<movies.size(); i++){
//            System.out.println(movies.get(i).getNombre()) ;
//        }
//        connection.close();
//        System.exit(1);
    }
}
