package slogo.view.components;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.controller.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import slogo.model.exceptions.ExecutionException;

/**
 * @author Saurav Sanjay
 */

public class Menu {
    private static final String CSS_FILE = "/resources/uistyle.css";
    private static final String AVAILABLE_LANGUAGES = "resources.availableLanguages";
    private static final String UI_TEXT = "resources.UIText";
    private static final Double DEFAULT_BACKGROUND_COLOR = 6.0;
    private static final Double DEFAULT_PEN_COLOR = 7.0;
    private static final String BGCOLORS = "bgcolors";
    private static final String PENCOLORS = "pencolors";
    private static final String IMAGES = "images";
    private static final String LANGUAGE = "language";
    private static final String HELP = "help";
    private static final int ICON_SIZE = 20;
    private static final String DEFAULT_IMAGE = "turtle.jpg";
    private static final String ENGLISH = "English";
    private static final String HELPWINDOW = "src/resources/windowtext/HelpWindowText.txt";
    private static final int HELP_SIZE = 800;
    private static final String SAVE = "save";
    private static final String LOAD = "load";
    private static final String COMMAND_FILE_IO_ERROR = "commandFile";
    private static final int MENU_ICON_SIZE = 30;

    private ResourceBundle turtleImages;
    private ResourceBundle languageModes = java.util.ResourceBundle.getBundle(AVAILABLE_LANGUAGES);
    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private HBox myView;
    private MenuButton bgColors;
    private MenuButton images;
    private MenuButton penColors;
    private MenuButton languages;
    private SimpleDoubleProperty activeBackgroundColor;
    private SimpleDoubleProperty activePenColor;
    private SimpleStringProperty turtleImage;
    private SimpleStringProperty activeLanguage;
    private SimpleBooleanProperty tellUpdate;
    private Controller myController;
    private ColorPalette myColorPalette;
    private Button help;
    private Button saveCode;
    private Button loadCode;
    private CodeStage myCode;


    /**
     * Creates a new Menu object
     * @param control controller to be used my menu
     * @param update boolean property that will indicate when the view needs to be updated
     * @param code a CodeStage object that holds code that needs to be parsed
     */
    public Menu(Controller control,SimpleBooleanProperty update,CodeStage code)
   {
       myController = control;
       tellUpdate = update;
       myCode = code;

       turtleImages = ResourceBundle.getBundle(myController.getAvailableImagesFile());

       myView = new HBox();
       myColorPalette = control.getColorPalette();

       initDropDownButtons();
       initOtherButtons();

       myView.getChildren().addAll(bgColors,penColors,languages,images,loadCode,saveCode,help);
   }

   private void initOtherButtons()
   {
       help = makeButton(visualText.getString(HELP),e->makeHelpScreen());
       saveCode = makeButton(visualText.getString(SAVE),e->save());
       loadCode = makeButton(visualText.getString(LOAD),e->load());

   }

   private Button makeButton(String buttonText, EventHandler e)
   {
       Button make = new Button(buttonText);
       make.setOnAction(e);
       return make;
   }

   private void load()
   {
       Stage load = new Stage();
       FileChooser chooseCode = new FileChooser();
       File codeFile = chooseCode.showOpenDialog(load);
       try {
           myController.parseFileCode(codeFile);
       } catch (Exception e) {
           showError(e.getMessage());
       }
       tellUpdate.setValue(true);
       myCode.clearStagedCode();
   }

   private void save()
   {
       try{
           myController.saveCommandHistory();
       }
       catch(IOException e)
       {
           showError(e.getMessage());

       }
   }

    private void initDropDownButtons() {
        activeBackgroundColor = new SimpleDoubleProperty(DEFAULT_BACKGROUND_COLOR);
        bgColors = new MenuButton(visualText.getString(BGCOLORS));
        makeColorsMenu(activeBackgroundColor,bgColors);

        activePenColor = new SimpleDoubleProperty(DEFAULT_PEN_COLOR);
        penColors = new MenuButton(visualText.getString(PENCOLORS));
        makeColorsMenu(activePenColor,penColors);


        turtleImage = new SimpleStringProperty(DEFAULT_IMAGE);
        images = new MenuButton(visualText.getString(IMAGES));
        makeImagesMenu();

        activeLanguage = new SimpleStringProperty(ENGLISH);
        languages = new MenuButton(visualText.getString(LANGUAGE));
        makeLanguagesMenu();

    }

    /**
     * Returns a string property that represents name of turtle image
     * @return String property with turtle image name
     */
    public Property getActiveTurtleImage()
   {
       return turtleImage;
   }

   private void makeLanguagesMenu()
   {
       for(String language: languageModes.keySet())
       {
           MenuItem languageSelect = new MenuItem(language);
           languageSelect.setOnAction(e -> {
               myController.setLanguage(language);
               activeLanguage.setValue(language);
               tellUpdate.setValue(true);
           });
           languages.getItems().add(languageSelect);
       }
   }

    /**
     * Returns the name of the active language wrapped in a string property
     * @return String property with active language name
     */
   public Property getActiveLanguage(){ return activeLanguage;}

   private void makeImagesMenu()
   {
       for(String imageIndex: turtleImages.keySet())
       {
           MenuItem imageSelect = new MenuItem(imageIndex);
           String imgName = turtleImages.getString(imageIndex).replaceAll("\"","");
           ImageView menuIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(imgName)));
           menuIcon.setFitHeight(MENU_ICON_SIZE);
           menuIcon.setFitWidth(MENU_ICON_SIZE);
           imageSelect.setGraphic(menuIcon);
           imageSelect.setOnAction(e -> {
               turtleImage.setValue(turtleImages.getString(imageIndex));
           });
           images.getItems().add(imageSelect);

       }

   }

   private void makeColorsMenu(SimpleDoubleProperty activeColor, MenuButton colorButton)
   {
       colorButton.getItems().clear();
       for(int color: myColorPalette.getAvailableIndices())
       {
           MenuItem colorItem = new MenuItem(color +"");
           colorItem.setGraphic(new Rectangle(ICON_SIZE, ICON_SIZE,myColorPalette.getColor(color)));
           colorItem.setOnAction(e -> {
               activeColor.setValue(color);
           });
           colorButton.getItems().add(colorItem);
       }
   }


    /**
     * Returns Node view for display
     * @return javaFX node to be displayed
     */
   public Node getView()
   {
       return myView;
   }

    /**
     * Returns the active background color
     * @return string property with active background color as value
     */
   public Property getActiveBackgroundColor()
   {
       return activeBackgroundColor;
   }

    /**
     * Returns active pen color
     * @return string property with active pen color
     */
   public Property getActivePenColor() { return activePenColor;}

   private void makeHelpScreen()
   {
       Stage stage1 = new Stage();
       List<String> allHelpText;
       File helpWindowTextFile = new File(HELPWINDOW);

       try{
          allHelpText = Files.readAllLines(Paths.get(helpWindowTextFile.toURI()));
       }
       catch(IOException ex){
           throw new ExecutionException(COMMAND_FILE_IO_ERROR, ex);
       }

       VBox helpLabels =new VBox();
       for(String s:allHelpText)
       {
           helpLabels.getChildren().add(new Label(s));
       }

       ScrollPane helpScreenText = new ScrollPane();
       helpScreenText.setContent(helpLabels);

       Scene helpScreen = new Scene(helpScreenText ,HELP_SIZE,HELP_SIZE);
       helpScreen.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());

       stage1.setScene(helpScreen);
       stage1.show();
   }

    /**
     * updates menu items in Menu object
     */
   public void update()
   {
       makeColorsMenu(activePenColor,penColors);
       makeColorsMenu(activeBackgroundColor,bgColors);
   }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
