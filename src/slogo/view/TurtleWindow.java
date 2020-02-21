package slogo.view;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

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
        myView.setStyle("-fx-background-color: red");
        myTurtle.getView().setTranslateX(200);
    }

    @Override
    public void update() {

    }

    @Override
    public Node getView() {
        return myView;
    }
}
