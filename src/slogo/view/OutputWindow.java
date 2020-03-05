package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

public class OutputWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String OUTPUT = "output";

    private TitledPane myView;
    private TextArea output;
    private String outputText;
    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);


    public OutputWindow()
    {
        myView = new TitledPane();
        myView.setMaxWidth(250);
        output = new TextArea();
        outputText = "";
        myView.setContent(output);
        myView.setText(visualText.getString(OUTPUT));
    }

    public void setOutput(Double out)
    {
        outputText = out+"";
    }

    @Override
    public void update() {
        output.setText(outputText);
    }

    @Override
    public Node getView() {
        return myView;
    }
}
