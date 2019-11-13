package oop;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MustRuut extends Application {

    @Override
    public void start(Stage peaLava) throws Exception {

        Group juur = new Group();
        Circle ring1 = new Circle(100, 100, 100, Color.RED);
        juur.getChildren().add(ring1);
        ring1.setOnMouseEntered(event -> System.out.println("ringil"));
        ring1.setOnMouseClicked(event -> ring1.setFill(Color.GREEN));
        Scene stseen1 = new Scene(juur);
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
