package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.Controller;
import slogo.view.components.CodeStage;

import java.util.ResourceBundle;

public class AvailableCommandsWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String AVAILABLE_COMMANDS = "available";


    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    private ResourceBundle commandNames;

    private TitledPane myView;
    private ListView<String> availableCommands;



    public AvailableCommandsWindow(Controller control,SimpleBooleanProperty update, CodeStage code)
    {
        myController = control;
        tellUpdate = update;
        myCode = code;

        commandNames = java.util.ResourceBundle.getBundle(myController.getLanguage());


        myView = new TitledPane();
        myView.setText(visualText.getString(AVAILABLE_COMMANDS));

        availableCommands =  new ListView<>();
        populateCommands();

        myView.setContent(availableCommands);
        myView.setExpanded(false);
    }


    public void update() {

        commandNames = java.util.ResourceBundle.getBundle(myController.getLanguage());
        availableCommands.getItems().clear();
        populateCommands();
    }

    private void populateCommands() {
        for (String key : commandNames.keySet()) {
            availableCommands.getItems().add(commandNames.getString(key));
        }
    }


    public Node getView() {
        return myView;
    }
}
