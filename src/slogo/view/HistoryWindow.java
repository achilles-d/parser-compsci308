package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class HistoryWindow extends Window {

    private TitledPane myView;

    public HistoryWindow()
    {
        myView = new TitledPane();
        myView.setText("Command History");
        ListView<String> commandHistory =  new ListView<>();
        commandHistory.getItems().addAll("fd 50","back 50", "loop 20");
        myView.setContent(commandHistory);
        myView.setCollapsible(false);
    }

    public void update() {

    }

    public Node getView() {
        return myView;
    }
}