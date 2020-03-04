package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class LogoVisualization extends BorderPane{

    private static final String UI_TEXT = "resources.UIText";
    private static final String CSS_FILE = "/resources/uistyle.css";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    //private BorderPane border = new BorderPane();
    private Stage myStage;
    private CodeStage myCode;
    private TurtleWindow graphics;
    private ParserController myController;
    private PaletteWindow myPalette;
    private MoveTurtleComponent turtleMover;
    private Property activePenColor;
    private Property activeTurtleImage;
    private SimpleBooleanProperty updateNeeded;
    private VariableWindow myVariables;
    private ConsoleWindow myConsole;
    private HistoryWindow myHistory;
    private Menu toolbar;
    AvailableCommandsWindow available;
    private Button executeButton;

    public LogoVisualization(ParserController control)
    {
        //myStage = stage;
        myController = control;
        init();
    }



    public void init()
    {
        myCode = new CodeStage();


        updateNeeded = new SimpleBooleanProperty();
        updateNeeded.setValue(false);
        updateNeeded.addListener(((observable, oldValue, newValue) -> checkUpdate(newValue)));

        toolbar = new Menu(myController,updateNeeded,myController.getColorPalette());
        myConsole = new ConsoleWindow(myController,updateNeeded,myCode);
        myVariables = new VariableWindow(myController,updateNeeded,myCode);
        myHistory = new HistoryWindow(myController,updateNeeded,myCode);
        available = new AvailableCommandsWindow(toolbar.getActiveLanguage(),myController,updateNeeded,myCode);
        graphics = new TurtleWindow(toolbar.getActiveBackgroundColor(),toolbar.getActiveTurtleImage(),myController,toolbar.getActivePenColor(),myController.getColorPalette());
        turtleMover = new MoveTurtleComponent(myController,updateNeeded,myCode);
        myPalette = new PaletteWindow(myController,updateNeeded);


        VBox leftComps = new VBox();
        leftComps.getChildren().addAll(myHistory.getView(),available.getView(),myVariables.getView());

        HBox bottom  = new HBox();
        bottom.getChildren().addAll(myConsole.getView());
        bottom.setMaxHeight(50);

        VBox rightComps = new VBox();
        rightComps.getChildren().addAll(turtleMover.getView(),myPalette.getView());

        bottom.setAlignment(Pos.CENTER);

        this.setCenter(graphics.getView());
        this.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        this.setLeft(leftComps);
        this.setTop(toolbar.getView());
        this.setRight(rightComps);



        /*
        Scene scene = new Scene(border,1000,1000);
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        myStage.setScene(scene);
        myStage.show();

         */


    }

    private void checkUpdate(boolean check)
    {
        if(check)
        {
            updateAllPanes();
        }
    }

    private void updateAllPanes()
    {
        try {
            myController.parseCode(myCode.getCodeToBeParsed());
        }
        catch (Exception e)
        {
            showError(e.getMessage());
        }

        graphics.update();
        myHistory.update();
        myVariables.update();
        myPalette.update();

        myCode.clearStagedCode();
        updateNeeded.setValue(false);

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }






}
