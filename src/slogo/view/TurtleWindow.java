package slogo.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TurtleWindow extends Window {

    private StackPane myView;
    private Canvas background;
    private ViewTurtle myTurtle;

    public TurtleWindow()
    {
        myView = new StackPane();
        background = new Canvas();
        myTurtle = new ViewTurtle();
        myView.getChildren().addAll(background,myTurtle.getView());
        makeBackgroundColor("red");
        myView.setStyle("-fx-background-color: red");
        myTurtle.getView().setTranslateX(200);
    }

    public void setBackgroundColor(String color) {
        makeBackgroundColor(color);
    }

    private void makeBackgroundColor(String color)
    {
        myView.setBackground(new Background(new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void update() {

    }

    @Override
    public Node getView() {
        return myView;
    }
}
