package edu.icesi.co.control;

import edu.icesi.co.db.SQLConnection;
import edu.icesi.co.model.Actor;
import edu.icesi.co.model.Genero;
import edu.icesi.co.model.Pelicula;
import edu.icesi.co.view.MainWindow;
import edu.icesi.co.view.RegisterWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

import java.util.Optional;


public class RegisterController {

    private RegisterWindow view;
    private SQLConnection connection;


    public RegisterController(RegisterWindow view) {
        this.view = view;
        init();
    }

    public void init() {
        connection = SQLConnection.getInstance();
        view.getRegisterActionBtt().setOnAction(
                (e) -> {
                    register();
                }
        );
        view.getHomeBtt().setOnAction(
                (e)->{
                    MainWindow mw = new MainWindow();
                    view.close();
                    mw.show();
                }
        );
    }

    public void register() {
        try {
            String choice = (String) view.getOptionsBox().getValue();
            String name = view.getNameTxt().getText();
            String msg = "";
            switch (choice) {
                case "Género":
                    Genero g = new Genero(-1, name);
                    connection.insertGenero(g);
                    msg = "El nuevo género ha sido añadido correctamente";
                    break;
                case "Película":
                    int gender = -1;

                    TextInputDialog genderT = new TextInputDialog();
                    genderT.setHeaderText("¿A cuál género pertenece?");
                    Optional<String> g1 = genderT.showAndWait();
                    if (g1.isPresent()) {
                        gender = Integer.parseInt(g1.get());
                    }
                    Pelicula p = new Pelicula(-1, name, gender);
                    connection.insertPelicula(p);
                    msg = "La nueva película ha sido añadida correctamente";
                    break;
                case "Actor":
                    Actor a = new Actor(-1, name);
                    connection.insertActor(a);
                    msg = "El nuevo actor ha sido añadido correctamente";
                    break;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(msg);
            alert.showAndWait();
            view.getNameTxt().setText("");
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("FALTA GÉNERO");
            alert.showAndWait();
        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("ELIGE LO QUE QUIERES AGREGAR");
            alert.showAndWait();
        }
    }

}
