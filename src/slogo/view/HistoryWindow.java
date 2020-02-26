package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.ParserController;

public class HistoryWindow extends Window {

    private TitledPane myView;
    private ParserController myController;
    private ListView<String> commandHistory;


    public HistoryWindow(ParserController control)
    {
        myController = control;
        myView = new TitledPane();
        myView.setText("Command History");
        commandHistory =  new ListView<>();
        myView.setContent(commandHistory);
        myView.setCollapsible(false);
    }

    public void update() {

        commandHistory.getItems().clear();
       for(String s:myController.getCommandHistory())
       {
           commandHistory.getItems().add(s);
       }
    }

    public Node getView() {
        return myView;
    }
}
