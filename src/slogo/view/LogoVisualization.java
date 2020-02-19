package slogo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LogoVisualization extends Application {

    Group root = new Group();
    BorderPane border = new BorderPane();

    public void start(Stage stage)
    {

        TextArea commandWindow = new TextArea();

        commandWindow.setPrefWidth(900);
        commandWindow.setMaxWidth(Double.MAX_VALUE);
        Button execute = new Button("Execute");
        execute.setOnAction((event -> {
            System.out.println(commandWindow.getText());
        }));
        Button reset = new Button("Reset");
        reset.setOnAction((event -> {commandWindow.clear();}));

        HBox bottom  = new HBox();
        VBox buttons = new VBox();
        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(execute,reset);
        buttons.setAlignment(Pos.CENTER);
        //bottom.setPrefWidth(1000);
        bottom.getChildren().addAll(commandWindow,buttons);
        //HBox.setHgrow(border, Priority.ALWAYS);
        //border.setTop(filler);
        border.setBottom(bottom);
        //root.getChildren().add(border);
        Scene scene = new Scene(border,1000,1000);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
