package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;
import slogo.view.components.CodeStage;

import java.util.ResourceBundle;

public class MoveTurtleWindow extends Window {

    private static final int MOVE_AMOUNT = 50;
    private static final String FORWARD = "Forward";
    private static final String BACK = "Backward";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";
    private static final String UI_TEXT = "resources.UIText";


    private static final int ROTATE_AMOUNT = 10;
    private static final String MOVETURTLE = "moveturtle";
    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    private ResourceBundle commandsList;
    private TitledPane myView;
    private ParserController myController;
    private SimpleBooleanProperty tellUpdate;
    private CodeStage myCode;
    private Button forward;
    private Button back;
    private Button right;
    private Button left;

    public MoveTurtleWindow(ParserController control, SimpleBooleanProperty update, CodeStage code) {
        myController = control;
        tellUpdate = update;
        myCode = code;
        myView = new TitledPane();
        commandsList = ResourceBundle.getBundle(myController.getLanguage());


        myView.setText(visualText.getString(MOVETURTLE));

        VBox buttonPane = new VBox();

        forward = makeButton(getCommandName(FORWARD), event -> {
            setLanguage();
            action(FORWARD,MOVE_AMOUNT);
        });
        back = makeButton(getCommandName(BACK), event -> {
            setLanguage();
            action(BACK,MOVE_AMOUNT);

        });
        right = makeButton(getCommandName(RIGHT), event -> {
            setLanguage();
            action(RIGHT,ROTATE_AMOUNT);
        });
        left = makeButton(getCommandName(LEFT), event -> {
            setLanguage();
            action(LEFT,ROTATE_AMOUNT);
        });

        buttonPane.getChildren().addAll(forward, back, right, left);
        buttonPane.setAlignment(Pos.CENTER);

        myView.setContent(buttonPane);

    }

    private void setLanguage() {
        commandsList = ResourceBundle.getBundle(myController.getLanguage());
    }

    private String getCommandName(String key) {
        String[] commands = commandsList.getString(key).split("\\|");
        return commands[0];
    }

    private void action(String command,int amount){
        myCode.addCodeToBeParsed(getCommandName(command) + " " + amount);
        tellUpdate.setValue(true);

    }

    private void moveForward() {
        myCode.addCodeToBeParsed(getCommandName(FORWARD) + " " + MOVE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private void moveBackward() {
        myCode.addCodeToBeParsed(getCommandName(BACK) + " " + MOVE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private void turnRight() {
        myCode.addCodeToBeParsed(getCommandName(RIGHT) + " " + ROTATE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private void turnLeft() {
        myCode.addCodeToBeParsed(getCommandName(LEFT) + " " + ROTATE_AMOUNT);
        tellUpdate.setValue(true);
    }

    private Button makeButton(String buttonText, EventHandler<ActionEvent> e) {
        Button button = new Button(buttonText);
        button.setOnAction(e);
        return button;
    }

    private void updateButtonText()
    {
        setLanguage();
        forward.setText(getCommandName(FORWARD));
        back.setText(getCommandName(BACK));
        right.setText(getCommandName(RIGHT));
        left.setText(getCommandName(LEFT));
    }

    public void update()
    {
        updateButtonText();
    }
    public Node getView()
    {
        return myView;
    }
}
