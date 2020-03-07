package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.ParserController;
import slogo.view.components.CodeStage;

import java.util.ResourceBundle;

public class HistoryWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String HISTORYWINDOW = "historywindow";
    private static final String NOHISTORY = "nohistory";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TitledPane myView;
    private ListView<String> commandHistory;

    public HistoryWindow(ParserController control,SimpleBooleanProperty update, CodeStage code)
    {
        myController = control;
        tellUpdate = update;
        myCode = code;

        myView = new TitledPane();
        myView.setText(visualText.getString(HISTORYWINDOW));

        commandHistory =  new ListView<>();
        commandHistory.setPlaceholder(new Label(visualText.getString(NOHISTORY)));
        setSelection();

        myView.setContent(commandHistory);
    }

    private void setSelection()
    {
        commandHistory.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
        myCode.addCodeToBeParsed(commandHistory.getItems().get((int) newValue));
             tellUpdate.setValue(true);});
    }

    public void update() {

       for(int i=commandHistory.getItems().size(); i<myController.getCommandHistory().size();i++)
       {
           commandHistory.getItems().add(myController.getCommandHistory().get(i));
       }


    }

    public Node getView() {
        return myView;
    }
}
