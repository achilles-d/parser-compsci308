package slogo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LogoVisualization extends Application {

    Pane root = new Pane();
    BorderPane border = new BorderPane();

    public void start(Stage stage)
    {

        TextArea commandWindow = new TextArea();
        ListView<String> variableTracker =  new ListView<>();
        variableTracker.setPrefHeight(100);
        variableTracker.getItems().addAll("varXSSSSSSSSSSSJFL:DKJF","varY","varY","varY","varY","varY","varY","varY","varY");
        commandWindow.setPrefWidth(650);
        commandWindow.setMaxWidth(Double.MAX_VALUE);
        Button execute = new Button("Execute");
        execute.setOnAction((event -> {
            System.out.println(commandWindow.getText());
        }));
        Button reset = new Button("Reset");
        reset.setOnAction((event -> {commandWindow.clear();}));

        ListView<String> commandHistory =  new ListView<>();
        commandHistory.getItems().addAll("fd 50","back 50", "loop 20");

        StackPane allTurtle = new StackPane();
        Image turtle = new Image("turtle.jpg");
        ImageView turtleWrap = new ImageView(turtle);
        Canvas turtleWindow = new Canvas();
        allTurtle.getChildren().addAll(turtleWindow,turtleWrap);
        turtleWrap.setFitHeight(50);
        turtleWrap.setFitWidth(50);
        HBox bottom  = new HBox();
        VBox buttons = new VBox();
        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(execute,reset);
        buttons.setAlignment(Pos.CENTER);
        //bottom.setPrefWidth(1000);
        bottom.getChildren().addAll(variableTracker,commandWindow,buttons);
        //HBox.setHgrow(border, Priority.ALWAYS);
        //border.setTop(filler);
        border.setBottom(bottom);
        border.setCenter(allTurtle);
        border.setLeft(commandHistory);
        turtleWrap.setTranslateX(200);
        //getChildren().addAll(border,allTurtle);
        //border.setPrefSize(900,900);

        //root.getChildren().addAll(border,turtleWrap);
        //root.prefHeightProperty().bind(border.heightProperty());
        //root.prefWidthProperty().bind(border.widthProperty());

        Scene scene = new Scene(border,1000,1000);
        stage.setScene(scene);
        stage.show();
        System.out.println(turtleWrap.getX());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
