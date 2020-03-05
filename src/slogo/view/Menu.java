package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import slogo.controller.ParserController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import slogo.model.exceptions.ExecutionException;

public class Menu {

    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final String BACKGROUND_COLOR = "resources.colors.BackgroundColor";
    private static final String TURTLE_IMAGES = "resources.TurtleImage";
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
    private static final String HELPWINDOW = "resources.windowtext.HelpWindowText.txt";
    private static final int HELP_SIZE = 400;

    private HBox myView;
    private ResourceBundle penColorsNames = java.util.ResourceBundle.getBundle(PEN_COLOR);
    private ResourceBundle bgColorsNames = java.util.ResourceBundle.getBundle(BACKGROUND_COLOR);
    private ResourceBundle turtleImages = java.util.ResourceBundle.getBundle(TURTLE_IMAGES);
    private ResourceBundle languageModes = java.util.ResourceBundle.getBundle(AVAILABLE_LANGUAGES);
    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    private MenuButton bgColors;
    private MenuButton images;
    private MenuButton penColors;
    private MenuButton languages;
    private SimpleDoubleProperty activeBackgroundColor;
    private SimpleDoubleProperty activePenColor;
    private SimpleStringProperty turtleImage;
    private SimpleStringProperty activeLanguage;
    private SimpleBooleanProperty tellUpdate;
    private ParserController myController;
    private ColorPalette myColorPalette;


    public Menu(ParserController control,SimpleBooleanProperty update) throws ExecutionException
   {
       myController = control;
       myView = new HBox();

       tellUpdate = update;
       myColorPalette = control.getColorPalette();

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

       Button help = new Button(visualText.getString(HELP));

       help.setOnAction(event -> {
           try {
               makeHelpScreen();
           } catch (IOException e) {
             throw new ExecutionException("Temp", e);
           }
       });

       myView.getChildren().addAll(bgColors,penColors,languages,images,help);
   }

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
           });
           languages.getItems().add(languageSelect);
       }
   }

   public Property getActiveLanguage(){ return activeLanguage;}

   private void makeImagesMenu()
   {
       for(String imageIndex: turtleImages.keySet())
       {
           MenuItem imageSelect = new MenuItem(imageIndex);
           String imgName = turtleImages.getString(imageIndex).replaceAll("\"","");
           ImageView menuIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(imgName)));
           menuIcon.setFitHeight(30);
           menuIcon.setFitWidth(30);
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


   public Node getView()
   {
       return myView;
   }

   public Property getActiveBackgroundColor()
   {
       return activeBackgroundColor;
   }

   public Property getActivePenColor() { return activePenColor;}

   private void makeHelpScreen()  throws IOException
   {
       Stage stage1 = new Stage();
       File helpWindowTextFile = new File(HELPWINDOW);
       try{
           List<String> allHelpText = Files.readAllLines(Paths.get(helpWindowTextFile.toURI()));
       }
       catch(IOException ex){
           throw ex;
       }
       Label helpText = new Label("This is a help screen");
       Group helpGroup = new Group();
       helpGroup.getChildren().addAll(helpText);
       Scene helpScreen = new Scene(helpGroup, HELP_SIZE,HELP_SIZE);
       stage1.setScene(helpScreen);
       stage1.show();
   }

   public void update()
   {
       makeColorsMenu(activePenColor,penColors);
       makeColorsMenu(activeBackgroundColor,bgColors);
   }
}
