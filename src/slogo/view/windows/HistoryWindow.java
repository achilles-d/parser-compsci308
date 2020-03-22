package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import slogo.controller.Controller;
import slogo.view.components.CodeStage;

import java.util.ResourceBundle;

/**
 * @author Saurav Sanjay
 * Window pane with history of all previously executed commands
 */
public class HistoryWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String HISTORYWINDOW = "historywindow";
    private static final String NOHISTORY = "nohistory";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TitledPane myView;
    private ListView<String> commandHistory;

    /**
     * Creates a new HistoryWindow object
     * @param control controller to be used
     * @param update boolean property that will indicate when the view needs to be updated
     * @param code a CodeStage object that holds code that needs to be parsed
     */
    public HistoryWindow(Controller control,SimpleBooleanProperty update, CodeStage code)
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

    /**
     * Updates view, in this case, adds newly executed commands to history
     */
    public void update() {

       for(int i=commandHistory.getItems().size(); i<myController.getCommandHistory().size();i++)
       {
           commandHistory.getItems().add(myController.getCommandHistory().get(i));
       }


    }

    /**
     * Returns Node object for display
     * @return Node for display
     */
    public Node getView() {
        return myView;
    }
}
