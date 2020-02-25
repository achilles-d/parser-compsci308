package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class AvailableCommandsWindow extends Window {


    private ResourceBundle commandNames;

    private TitledPane myView;
    private ListView<String> commands;
    private ParserController myController;



    public AvailableCommandsWindow(String languageFile,ParserController control)
    {
        myController = control;
        commandNames = java.util.ResourceBundle.getBundle(languageFile);
        myView = new TitledPane();
        myView.setText("Available Commands");
        ListView<String> availableCommands =  new ListView<>();
        for(String key:commandNames.keySet())
        {
           availableCommands.getItems().add(commandNames.getString(key));
        }
        //availableCommands.getItems().addAll("forward","backward","right","left");
        myView.setContent(availableCommands);
        myView.setExpanded(false);
    }


    public void update() {



    }


    public Node getView() {
        return myView;
    }
}
