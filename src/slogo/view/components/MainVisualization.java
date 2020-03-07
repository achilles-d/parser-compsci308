package slogo.view.components;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.controller.ParserController;
import slogo.view.components.LogoVisualization;

import javax.security.auth.login.LoginContext;
import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainVisualization {

    private static final String CSS_FILE = "/resources/uistyle.css";
    private static final String UI_TEXT = "resources.UIText";
    private static final String WORKSPACEHEADER = "workspaceheader";
    private static final String WORKSPACEDIALOG = "workspacedialog";
    private static final String WORKSPACEBUTTON = "workspacebutton";
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 1000;
    private static final String RESOURCES_CONFIGURATION = "resources.configuration.";
    private static final int REMOVE_END = 11;
    private static final String CONFIGFILE = "configfile";
    private static final String CONFIGURATION = "src/resources/configuration";


    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private TabPane myTabs;
    private Button makeWorkspace;
    private BorderPane myBorder;
    private Stage myStage;
    private TextInputDialog tabNameInput;

    public MainVisualization(Stage stage)
    {
        tabNameInput = new TextInputDialog();
        tabNameInput.setContentText(visualText.getString(WORKSPACEHEADER));
        tabNameInput.setHeaderText(visualText.getString(WORKSPACEDIALOG));
        myStage = stage;
        myTabs = new TabPane();
        myBorder = new BorderPane();
        makeWorkspace = new Button(visualText.getString(WORKSPACEBUTTON));
        makeWorkspace.setOnAction(event -> {
            addWorkspace();
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

    public void addWorkspace()
    {
        FileChooser chooseConfig = new FileChooser();
        chooseConfig.setTitle(visualText.getString(CONFIGFILE));
        chooseConfig.setInitialDirectory(new File(CONFIGURATION));
        File config = chooseConfig.showOpenDialog(myStage);
        String path = RESOURCES_CONFIGURATION + config.getName().substring(0,config.getName().length()- REMOVE_END);

        LogoVisualization workspace = new LogoVisualization(new ParserController(path));
        Optional tabName = tabNameInput.showAndWait();
        myTabs.getTabs().add(new Tab((String)tabName.get(),workspace));
    }


}
