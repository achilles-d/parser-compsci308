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

import java.util.ResourceBundle;

public class MoveTurtleComponent {

    private static final int MOVE_AMOUNT = 50;
    private static final String FORWARD = "Forward";
    private static final String BACK = "Backward";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";

    private static final int ROTATE_AMOUNT = 10;
    private ResourceBundle commandsList;
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
        commandsList = ResourceBundle.getBundle(myController.getLanguage());


        myView.setText("Move Turtle");

        VBox buttonPane = new VBox();

        forward = makeButton(FORWARD,event ->{setLanguage(); moveForward();});
        back = makeButton(BACK, event->{setLanguage(); moveBackward();});
        right = makeButton(RIGHT, event ->{setLanguage(); turnRight();});
        left = makeButton(LEFT, event->{setLanguage(); turnLeft();});

        buttonPane.getChildren().addAll(forward,back,right,left);
        buttonPane.setAlignment(Pos.CENTER);

        myView.setContent(buttonPane);

    }

    private void setLanguage()
    {
        commandsList = ResourceBundle.getBundle(myController.getLanguage());
    }

    private String getCommandName(String key)
    {
        String[] commands = commandsList.getString(key).split("\\|");
        return commands[1];
    }

    private void moveForward()
    {
        myCode.addCodeToBeParsed(getCommandName(FORWARD) + " " + MOVE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private void moveBackward()
    {
        myCode.addCodeToBeParsed(getCommandName(BACK) + " " + MOVE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private void turnRight()
    {
        myCode.addCodeToBeParsed(getCommandName(RIGHT) + " " + ROTATE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private void turnLeft()
    {
        myCode.addCodeToBeParsed(getCommandName(LEFT) + " " + ROTATE_AMOUNT);
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
