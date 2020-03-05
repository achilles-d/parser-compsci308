package slogo.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class ConsoleWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String RESET = "reset";
    private static final String EXECUTE = "execute";
    private static final int RETURN_CONSOLE_WIDTH = 150;
    private static final int CONSOLE_WIDTH = 500;
    private static final String RETURNCONSOLE = "returnconsole";
    private static final int PADDING = 10;
    private static final int MAX_HEIGHT = 150;
    private static final String RETURNS = "returns:";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private HBox myView;
    private TitledPane consoleItems;
    private TextArea console;
    private TextArea returnConsole;
    private VBox buttonPane;
    private Button execute;
    private Button reset;
    private ParserController myController;
    private SimpleBooleanProperty tellUpdate;
    private CodeStage myCode;


    public ConsoleWindow(ParserController control,SimpleBooleanProperty update, CodeStage code)
    {
        tellUpdate = update;
        myCode = code;

        myView = new HBox();
        consoleItems = new TitledPane();
        consoleItems.setText("Console");
        myController = control;
        HBox myContainer = new HBox();
        console = new TextArea();
        returnConsole = new TextArea();
        returnConsole.setPrefWidth(RETURN_CONSOLE_WIDTH);
        returnConsole.setText(visualText.getString(RETURNCONSOLE));
        console.setPrefWidth(CONSOLE_WIDTH);
        console.setMaxWidth(Double.MAX_VALUE);

        execute = new Button(visualText.getString(EXECUTE));
        execute.setOnAction(event -> {codeEntered();});

        reset = new Button(visualText.getString(RESET));
        reset.setOnAction((event -> {console.clear();}));

        buttonPane = new VBox();
        buttonPane.setPadding(new Insets(PADDING));
        buttonPane.getChildren().addAll(execute,reset);
        buttonPane.setAlignment(Pos.CENTER);

        myContainer.getChildren().addAll(returnConsole,console,buttonPane);
        myContainer.setMaxHeight(MAX_HEIGHT);
        consoleItems.setContent(myContainer);
       // consoleItems.setMaxHeight(150);

        myView.getChildren().add(myContainer);
        myView.setMaxHeight(MAX_HEIGHT);
    }

    private void codeEntered()
    {
        myCode.addCodeToBeParsed(console.getText());
        tellUpdate.setValue(true);
    }

    public void addReturn(Double returned)
    {
        returnConsole.setText(RETURNS + returned);
    }

    public void update() {
        //Supposed to do nothing
    }

//    public String getConsoleText()
//    {
//        return console.getText();
//    }

    public Node getView() {
        return myView;
    }
}
