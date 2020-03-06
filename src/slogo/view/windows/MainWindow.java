package slogo.view.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import slogo.controller.ParserController;
import slogo.view.components.LogoVisualization;

import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow {

    private static final String CSS_FILE = "/resources/uistyle.css";
    private static final String UI_TEXT = "resources.UIText";
    private static final String WORKSPACEHEADER = "workspaceheader";
    private static final String WORKSPACEDIALOG = "workspacedialog";
    private static final String WORKSPACEBUTTON = "workspacebutton";
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;


    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TabPane myTabs;
    private Button makeWorkspace;
    private BorderPane myBorder;
    private Stage myStage;
    private TextInputDialog tabNameInput;

    public MainWindow(Stage stage)
    {
        tabNameInput = new TextInputDialog();
        tabNameInput.setContentText(visualText.getString(WORKSPACEHEADER));
        tabNameInput.setHeaderText(visualText.getString(WORKSPACEDIALOG));
        myStage = stage;
        myTabs = new TabPane();
        myBorder = new BorderPane();
        makeWorkspace = new Button(visualText.getString(WORKSPACEBUTTON));
        makeWorkspace.setOnAction(event -> {
            addWorkspace(new LogoVisualization(new ParserController()));
        });
        init();
    }

    private void init() {
        myBorder.setCenter(myTabs);
        myBorder.setTop(makeWorkspace);

        Scene scene = new Scene(myBorder, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        tabNameInput.getDialogPane().getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());

        myStage.setScene(scene);
        myStage.show();


    }

    public void addWorkspace(LogoVisualization workspace)
    {
        Optional tabName = tabNameInput.showAndWait();
        myTabs.getTabs().add(new Tab((String)tabName.get(),workspace));
    }


}
