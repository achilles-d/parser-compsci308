package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class VariableWindow extends Window {

    private TitledPane myView;
    private ListView<String> variables;

    public VariableWindow()
    {
        myView = new TitledPane();
        myView.setCollapsible(false);
        myView.setText("Variables");
        myView.setPrefHeight(200);
        variables = new ListView<>();
        variables.getItems().addAll("varXSSSSSSSSSSJFL:DKJF","varY","varY","varY","varY","varY","varY","varY","varY");
        myView.setContent(variables);

    }

    public Node getView()
    {
        return myView;
    }

    public void update()
    {

    }


}
