package slogo.view;

import javafx.beans.property.SimpleBooleanProperty;
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
    private SimpleBooleanProperty tellUpdate;
    private CodeStage myCode;



    public HistoryWindow(ParserController control,SimpleBooleanProperty update, CodeStage code)
    {
        myController = control;
        tellUpdate = update;
        myCode = code;

        myView = new TitledPane();
        myView.setText(visualText.getString(HISTORYWINDOW));
        commandHistory =  new ListView<>();
        myView.setContent(commandHistory);
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
