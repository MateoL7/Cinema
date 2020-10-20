package edu.icesi.co.db;

import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Genero;
import edu.icesi.co.model.Pelicula;


import java.sql.*;
import java.util.ArrayList;

public class SQLConnection {

    private static SQLConnection instance;

    public static synchronized SQLConnection getInstance() {
        if(instance == null) {
            instance = new SQLConnection();
        }
        return instance;
    }

    private Connection connection;

    public SQLConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema","root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void recreateDataBase(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("Create table generos(id int primary key auto_increment, tipo VARCHAR (100))");
            statement.execute("CREATE TABLE películas(id int primary key auto_increment, nombre VARCHAR (100), generoID int, foreign key (generoID) references generos(id))");
            statement.execute("CREATE TABLE actores(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(100))");
            statement.execute("CREATE TABLE películas_actores(id INT PRIMARY KEY AUTO_INCREMENT, películaID INT, actorID INT, FOREIGN KEY (películaID) REFERENCES películas(id), FOREIGN KEY (actorId) REFERENCES actores(id))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void close(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void insertGenero(Genero genero){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO generos(tipo) values ('$TIPO')"
                    .replace("$TIPO",genero.getTipo());
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void insertPelicula(Pelicula pelicula){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO películas(nombre, generoID) values ('$NOMBRE', '$GENEROID')"
                    .replace("$NOMBRE", pelicula.getNombre())
                    .replace("$GENEROID", ""+pelicula.getGeneroID());
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void insertActor(Actor actor){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO actores(nombre) values ('$NOMBRE')"
                    .replace("$NOMBRE", actor.getNombre());
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void linkActorMovie(int pID, int aID){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO películas_actores(películaID,actorID) values ('$PID','$AID')"
                    .replace("$PID", ""+pID)
                    .replace("$AID", ""+aID);
            statement.execute(sql);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public ArrayList<Actor> getAllActors(){
        ArrayList<Actor> actors = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from actores");
            while(resultSet.next()) {
                int id = resultSet.getInt(resultSet.findColumn("id"));
                String nombre = resultSet.getString(resultSet.findColumn("nombre"));
                actors.add(new Actor(id,nombre));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  actors;
    }
    public ArrayList<Pelicula> getAllMovies(){
        ArrayList<Pelicula> movies = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from películas");
            while(resultSet.next()) {
                int id = resultSet.getInt(resultSet.findColumn("id"));
                String nombre = resultSet.getString(resultSet.findColumn("nombre"));
                int gID = resultSet.getInt(resultSet.findColumn("generoID"));
                movies.add(new Pelicula(id,nombre,gID));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  movies;
    }
    public ArrayList<Genero> getAllGenres(){
        ArrayList<Genero> genres = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from generos");
            while(resultSet.next()) {
                int id = resultSet.getInt(resultSet.findColumn("id"));
                String nombre = resultSet.getString(resultSet.findColumn("tipo"));
                genres.add(new Genero(id,nombre));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  genres;
    }
    public ArrayList<Actor> getActorsByMovie(int pID){
        ArrayList<Actor> actors = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT actores.id, actores.nombre FROM (actores INNER JOIN películas_actores ON actores.id = películas_actores.actorID) INNER JOIN películas ON películas_actores.películaID = películas.id WHERE películas.id =" + pID);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                actors.add(new Actor(id,nombre));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return actors;
    }
    public ArrayList<Pelicula> getMoviesByGenre(int gID){
        ArrayList<Pelicula> movies = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT películas.id, películas.nombre FROM películas INNER JOIN generos ON películas.generoID = generos.id WHERE generos.id =" + gID);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                movies.add(new Pelicula(id,nombre,gID));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return movies;
    }
    public ArrayList<Pelicula> getMoviesByActor(int aID){
        ArrayList<Pelicula> movies = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT películas.id, películas.nombre, películas.generoID FROM (películas INNER JOIN películas_actores ON películas.id = películas_actores.películaID) INNER JOIN actores ON películas_actores.actorID = actores.id WHERE actores.id =" + aID);
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                int gID = resultSet.getInt(3);
                movies.add(new Pelicula(id,nombre, gID));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return movies;
    }
    public String getGenre(int gID){
        String gen ="";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT generos.id, generos.tipo FROM generos WHERE generos.id = " + gID);
            while(resultSet.next()) {
                gen = resultSet.getString(resultSet.findColumn("tipo"));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return gen;
    }
    public void deleteGenre(int gID){
        try{
            Statement statement = connection.createStatement();
            ArrayList<Pelicula> moviesG = getMoviesByGenre(gID);
            for (int i = 0; i < moviesG.size();i++){
                int del = moviesG.get(i).getId();
                deleteMovie(del);
            }
            String sql = ("DELETE FROM generos WHERE generos.id = $ID")
                    .replace("$ID", ""+gID);
            statement.execute(sql);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void deleteMovie(int mID){
        try{
            Statement statement = connection.createStatement();
            String sqlPA = ("DELETE FROM películas_actores WHERE películas_actores.películaID = $MID")
                    .replace("$MID", ""+mID);
            String sqlP = ("DELETE FROM películas WHERE películas.id = $MID")
                    .replace("$MID", ""+mID);
            statement.execute(sqlPA);
            statement.execute(sqlP);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void deleteActor(int aID){
        try{
            Statement statement = connection.createStatement();
            String sqlPA = ("DELETE FROM películas_actores WHERE películas_actores.actorID = $AID")
                    .replace("$AID", ""+aID);
            String sqlA = ("DELETE FROM actores WHERE actores.id = $AID")
                    .replace("$AID", ""+aID);
            statement.execute(sqlPA);
            statement.execute(sqlA);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
