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
    private static final int ROTATE_AMOUNT = 10;
    private static final String FORWARD = "Forward";
    private static final String BACK = "Backward";
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";
    private static final String UI_TEXT = "resources.UIText";
    private static final String MOVETURTLE = "moveturtle";


    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    private ResourceBundle commandsList;

    private TitledPane myView;
    private Button forward;
    private Button back;
    private Button right;
    private Button left;

    public MoveTurtleWindow(ParserController control, SimpleBooleanProperty update, CodeStage code) {
        myController = control;
        tellUpdate = update;
        myCode = code;

        myView = new TitledPane();
        myView.setText(visualText.getString(MOVETURTLE));

        setLanguage();
        VBox buttonPane = new VBox();
        makeAllButtons();

        buttonPane.getChildren().addAll(forward, back, right, left);
        buttonPane.setAlignment(Pos.CENTER);
        myView.setContent(buttonPane);

    }

    private void makeAllButtons()
    {
        forward = makeButton(getCommandName(FORWARD), event -> {
            action(FORWARD,MOVE_AMOUNT);
        });
        back = makeButton(getCommandName(BACK), event -> {
            action(BACK,MOVE_AMOUNT);

        });
        right = makeButton(getCommandName(RIGHT), event -> {
            action(RIGHT,ROTATE_AMOUNT);
        });
        left = makeButton(getCommandName(LEFT), event -> {
            action(LEFT,ROTATE_AMOUNT);
        });
    }

    private void setLanguage() {
        commandsList = ResourceBundle.getBundle(myController.getLanguage());
    }

    private String getCommandName(String key) {
        String[] commands = commandsList.getString(key).split("\\|");
        return commands[0];
    }

    private void action(String command,int amount){
        setLanguage();
        myCode.addCodeToBeParsed(getCommandName(command) + " " + amount);
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
