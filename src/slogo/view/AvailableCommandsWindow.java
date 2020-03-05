package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class AvailableCommandsWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private static final String AVAILABLE_COMMANDS = "available";
    private ResourceBundle commandNames;

    private TitledPane myView;
    private ParserController myController;
    private SimpleStringProperty languageChanged;
    private ListView<String> availableCommands;
    private SimpleBooleanProperty tellUpdate;
    private CodeStage myCode;


    public AvailableCommandsWindow(Property language, ParserController control,SimpleBooleanProperty update, CodeStage code)
    {
        languageChanged = (SimpleStringProperty)language;
        languageChanged.addListener((observable, oldValue, newValue) -> update());

        myController = control;
        commandNames = java.util.ResourceBundle.getBundle(myController.getLanguage());

        tellUpdate = update;
        myCode = code;

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
