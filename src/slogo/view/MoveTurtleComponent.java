package slogo.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;

public class MoveTurtleComponent {

    private TitledPane myView;
    private ParserController myController;
    private SimpleBooleanProperty tellUpdate;
    private CodeStage myCode;
    private Button forward;
    private Button back;
    private Button right;
    private Button left;

    public MoveTurtleComponent(ParserController control,SimpleBooleanProperty update, CodeStage code)
    {
        myController = control;
        tellUpdate = update;
        myCode = code;
        myView = new TitledPane();

        myView.setText("Move Turtle");

        VBox buttonPane = new VBox();

        forward = makeButton("Forward",event ->moveForward());
        back = makeButton("Back",event->moveBackward());
        right = makeButton("Right",event -> turnRight());
        left = makeButton("Left",event->turnLeft());

        buttonPane.getChildren().addAll(forward,back,right,left);
        buttonPane.setAlignment(Pos.CENTER);

        myView.setContent(buttonPane);

    }

    private void moveForward()
    {
        myCode.addCodeToBeParsed("fd 50");
        tellUpdate.setValue(true);
    }

    private void moveBackward()
    {
        myCode.addCodeToBeParsed("back 50");
        tellUpdate.setValue(true);
    }

    private void turnRight()
    {
        myCode.addCodeToBeParsed("rt 10");
        tellUpdate.setValue(true);
    }

    private void turnLeft()
    {
        myCode.addCodeToBeParsed("left 10");
        tellUpdate.setValue(true);
    }

    private Button makeButton(String buttonText, EventHandler<ActionEvent> e)
    {
          Button button = new Button(buttonText);
          button.setOnAction(e);
          return button;
    }

    public Node getView()
    {
        return myView;
    }
}
