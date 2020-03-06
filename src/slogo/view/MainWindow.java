package slogo.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import slogo.controller.ParserController;

import java.awt.*;
import java.util.Optional;

public class MainWindow {

    private static final String CSS_FILE = "/resources/uistyle.css";


    private TabPane myTabs;
    private Button makeWorkspace;
    private BorderPane myBorder;
    private Stage myStage;
    private TextInputDialog tabNameInput;

    public MainWindow(Stage stage)
    {
        tabNameInput = new TextInputDialog();
        tabNameInput.setContentText("Workspace Name: ");
        tabNameInput.setHeaderText("Please Enter a Name For Your New Workspace");
        myStage = stage;
        myTabs = new TabPane();
        myBorder = new BorderPane();
        makeWorkspace = new Button("New Workspace");
        makeWorkspace.setOnAction(event -> addWorkspace(new LogoVisualization(new ParserController())));
        init();
    }

    private void init() {
        myBorder.setCenter(myTabs);
        myBorder.setTop(makeWorkspace);

        Scene scene = new Scene(myBorder,1500,1000);

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
