package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import slogo.controller.Controller;
import slogo.view.components.CodeStage;
import java.util.Optional;
import java.util.ResourceBundle;

public class VariableWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String CSS_FILE = "/resources/uistyle.css";
    private static final String VARWINDOW = "varwindow";
    private static final int HEIGHT = 200;
    private static final String VARVAL = "varval";
    private static final String VARVALWINDOW = "varvalwindow";
    private static final String NOVARIABLES = "novariables";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TitledPane myView;
    private ListView<String> variables;
    private TextInputDialog variableValueInput;



    public VariableWindow(Controller control,SimpleBooleanProperty update, CodeStage code)
    {

        myController = control;
        tellUpdate = update;
        myCode = code;

        myView = new TitledPane();
        myView.setText(visualText.getString(VARWINDOW));
        myView.setPrefHeight(HEIGHT);

        variables = new ListView<>();
        variables.setPlaceholder(new Label(visualText.getString(NOVARIABLES)));
        myView.setContent(variables);

        variableValueInput = new TextInputDialog();
        variableValueInput.getDialogPane().getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        variableValueInput.setContentText(visualText.getString(VARVAL));
        variableValueInput.setHeaderText(visualText.getString(VARVALWINDOW));

        variables.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if(!((int)newValue ==-1))updateVariable((int) newValue);
                });



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
        updateOldVariables();
        addNewVariables();
    }

    private void addNewVariables()
    {
        for(int i=variables.getItems().size();i<myController.getAllVariables().size();i++)
        {
            variables.getItems().add(myController.getAllVariables().get(i));
        }
    }

    private void updateOldVariables()
    {
        for(int i = 0; i<variables.getItems().size();i++)
        {
            variables.getItems().set(i,myController.getAllVariables().get(i));
        }

    }


}
