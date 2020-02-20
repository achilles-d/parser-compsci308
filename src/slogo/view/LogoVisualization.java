package slogo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
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

        TitledPane variablesWhole = new TitledPane();
        variablesWhole.setText("Variables");
        variablesWhole.setCollapsible(false);
        variablesWhole.setPrefHeight(200);
        ListView<String> variableTracker =  new ListView<>();
        variableTracker.setPrefHeight(100);
        variableTracker.getItems().addAll("varXSSSSSSSSSSJFL:DKJF","varY","varY","varY","varY","varY","varY","varY","varY");
        variablesWhole.setContent(variableTracker);
        commandWindow.setPrefWidth(650);
        commandWindow.setMaxWidth(Double.MAX_VALUE);
        Button execute = new Button("Execute");
        execute.setOnAction((event -> {
            System.out.println(commandWindow.getText());
        }));
        Button reset = new Button("Reset");
        reset.setOnAction((event -> {commandWindow.clear();}));

        TitledPane history = new TitledPane();
        history.setText("Command History");
        ListView<String> commandHistory =  new ListView<>();
        commandHistory.getItems().addAll("fd 50","back 50", "loop 20");
        history.setContent(commandHistory);
        history.setCollapsible(false);

        TitledPane available = new TitledPane();
        available.setText("Available Commands");
        ListView<String> availableCommands =  new ListView<>();
        availableCommands.getItems().addAll("forward","backward","right","left");
        available.setContent(availableCommands);
        available.setExpanded(false);

        VBox leftComps = new VBox();
        leftComps.getChildren().addAll(history,available);

        HBox menu = new HBox();
        MenuItem itemBlue = new MenuItem("Blue");
        MenuItem itemGreen = new MenuItem("Green");
        MenuButton colors = new MenuButton("Colors",null,itemBlue,itemGreen);

        MenuItem itemGerman = new MenuItem("German");
        MenuItem itemSpanish = new MenuItem("Spanish");
        MenuItem itemEnglish = new MenuItem("English");
        MenuButton language = new MenuButton("Language",null,itemGerman,itemSpanish,itemEnglish);

        menu.getChildren().addAll(colors,language);

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
        bottom.getChildren().addAll(variablesWhole,commandWindow,buttons);
        //HBox.setHgrow(border, Priority.ALWAYS);
        //border.setTop(filler);
        allTurtle.setStyle("-fx-background-color: red");
        border.setBottom(bottom);
        border.setCenter(allTurtle);
        border.setLeft(leftComps);
        border.setTop(menu);
       // border.setRight(available);
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
