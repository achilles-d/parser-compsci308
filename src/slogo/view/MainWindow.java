package slogo.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import slogo.controller.ParserController;

public class MainWindow {

    private static final String CSS_FILE = "/resources/uistyle.css";


    private TabPane myTabs;
    private Button makeWorkspace;
    private BorderPane myBorder;
    private Stage myStage;

    public MainWindow(Stage stage)
    {
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
        Scene scene = new Scene(myBorder,1000,1000);
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        myStage.setScene(scene);
        myStage.show();

    }

    public void addWorkspace(LogoVisualization workspace)
    {
        myTabs.getTabs().add(new Tab("Tab",workspace));
    }


}
