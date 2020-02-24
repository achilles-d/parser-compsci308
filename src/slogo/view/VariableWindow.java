package slogo.view;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import slogo.model.backEndInternal.UserVariableHandler;

public class VariableWindow extends Window {

    private TitledPane myView;
    private TableView<String> variables;
    UserVariableHandler handler = new UserVariableHandler();

    public VariableWindow()
    {
        myView = new TitledPane();
        myView.setCollapsible(false);
        myView.setText("Variables");
        myView.setPrefHeight(200);
        variables = new TableView<>(handler.getKeys());
        //variables.getItems().addAll("varXSSSSSSSSSSJFL:DKJF","varY","varY","varY","varY","varY","varY","varY","varY");
        myView.setContent(variables);
        handler.makeVariable("yay",232);
        handler.makeVariable("3123",12321);
        System.out.println(handler.getVariableMap().keySet().size());
        handler.removeVariable("3123");
        System.out.println(handler.getVariableMap().keySet().size());

        TableColumn<String,String> col1 = new TableColumn<>("Key");
        col1.setCellValueFactory(cd-> Bindings.createStringBinding(()-> cd.getValue()));

        TableColumn<String,String> col2 = new TableColumn<>("Value");
        col2.setCellValueFactory(cd -> Bindings.valueAt(handler.getVariableMap(),cd.getValue()));
        variables.getColumns().setAll(col1,col2);


    }

    public Node getView()
    {
        return myView;
    }

    public void update()
    {

    }


}
