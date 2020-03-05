package slogo.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import slogo.controller.ParserController;
import slogo.model.backEndInternal.UserVariableHandler;

import java.util.Optional;
import java.util.ResourceBundle;

public class VariableWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String VARWINDOW = "varwindow";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TitledPane myView;
    private ListView<String> variables;
    UserVariableHandler handler = new UserVariableHandler();
    private ParserController myController;
    private SimpleBooleanProperty tellUpdate;
    private CodeStage myCode;
    private TextInputDialog variableValueInput;



    public VariableWindow(ParserController control,SimpleBooleanProperty update, CodeStage code)
    {

        myController = control;
        tellUpdate = update;
        myCode = code;

        myView = new TitledPane();
        myView.setText(visualText.getString(VARWINDOW));
        myView.setPrefHeight(200);
//
        variables = new ListView<>();
        myView.setContent(variables);

        variableValueInput = new TextInputDialog();
        variables.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            updateVariable((int) newValue);
                });





//        variables = new TableView<>(myController.getVariableKeysProperties());
//        //variables.getItems().addAll("varXSSSSSSSSSSJFL:DKJF","varY","varY","varY","varY","varY","varY","varY","varY");
//        myView.setContent(variables);
//        handler.makeVariable("yay",232.0);
//        handler.makeVariable("3123",12321.0);
//        System.out.println(myController.getVariableMap().keySet().size());
//        //handler.removeVariable("3123");
//        System.out.println(myController.getVariableMap().keySet().size());
//
//        TableColumn<String,String> col1 = new TableColumn<>("Key");
//        col1.setCellValueFactory(cd-> Bindings.createStringBinding(()-> cd.getValue()));
//
//        TableColumn<String,String> col2 = new TableColumn<>("Value");
//        col2.setCellValueFactory(cd -> Bindings.valueAt(myController.getVariableMap(),cd.getValue()));
////        col2.setCellValueFactory(TextFieldTableCell.forTableColumn());
////        col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<String, String>>() {
////            @Override
////            public void handle(TableColumn.CellEditEvent<String, String> event) {
////
////            }
////        });
//        variables.getColumns().setAll(col1,col2);
//
//        variables.setEditable(true);




    }
    private void updateVariable(int variableIndex)
    {
        if(!(variables.getItems().size() ==0)) {
            Optional val = variableValueInput.showAndWait();
            String varName = variables.getItems().get(variableIndex).split("=")[0];
            myController.setVariable(varName, Double.parseDouble((String) val.get()));
            update();
        }

    }
    public Node getView()
    {
        return myView;
    }

    public void update()
    {
        variables.getItems().clear();
        for(String s : myController.getAllVariables())
        {
            variables.getItems().add(s);
        }


    }


}
