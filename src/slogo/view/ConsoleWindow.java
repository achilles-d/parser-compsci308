package slogo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class ConsoleWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String RESET = "reset";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private HBox myView;
    private TextArea console;
    private VBox buttonPane;
    private Button execute;
    private Button reset;
    private ParserController myController;


    public ConsoleWindow(Button execution, ParserController control)
    {
        myController = control;
        myView = new HBox();
        console = new TextArea();
        console.setPrefWidth(650);
        console.setMaxWidth(Double.MAX_VALUE);
        execute = execution;
        reset = new Button(visualText.getString(RESET));
        reset.setOnAction((event -> {console.clear();}));

        buttonPane = new VBox();
        buttonPane.setPadding(new Insets(10));
        buttonPane.getChildren().addAll(execute,reset);
        buttonPane.setAlignment(Pos.CENTER);

        myView.getChildren().addAll(console,buttonPane);

    }

    public void update() {

    }

    public String getConsoleText()
    {
        return console.getText();
    }

    public Node getView() {
        return myView;
    }
}
