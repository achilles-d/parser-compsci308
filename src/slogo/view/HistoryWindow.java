package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class HistoryWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String HISTORYWINDOW = "historywindow";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TitledPane myView;
    private ParserController myController;
    private ListView<String> commandHistory;


    public HistoryWindow(ParserController control)
    {
        myController = control;
        myView = new TitledPane();
        myView.setText(visualText.getString(HISTORYWINDOW));
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
