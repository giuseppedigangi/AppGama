package application.controller;



import java.io.IOException;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception  {
        Parent root = FXMLLoader.load(getClass().getResource("../view/SchermataPrincipale.fxml"));

        Scene scene = new Scene(root);

        stage.setResizable(false);
        stage.setScene(scene);
       // stage.getIcons().add(new Image(this.getClass().getResource("../GAMA1.png").toString()));
        stage.show();

        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));
	}

	public static void main(String[] args) {
		launch(args);


	}
}
