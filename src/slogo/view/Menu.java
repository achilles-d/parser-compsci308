package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class Menu {

    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final String BACKGROUND_COLOR = "resources.colors.BackgroundColor";
    private static final String TURTLE_IMAGES = "resources.TurtleImage";

    private HBox myView;
    private ResourceBundle penColorsNames = java.util.ResourceBundle.getBundle(PEN_COLOR);
    private ResourceBundle bgColorsNames = java.util.ResourceBundle.getBundle(BACKGROUND_COLOR);
    private ResourceBundle turtleImages = java.util.ResourceBundle.getBundle(TURTLE_IMAGES);
    private MenuButton bgColors;
    private MenuButton images;
    private MenuButton penColors;
    private SimpleStringProperty activeBackgroundColor;
    private SimpleStringProperty activePenColor;
    private SimpleStringProperty turtleImage;
    private ParserController myController;


    public Menu(ParserController control)
   {
       myController = control;
       myView = new HBox();

       activeBackgroundColor = new SimpleStringProperty("White");
       bgColors = new MenuButton("Background Colors");
       makeBackgroundColorsMenu();

       activePenColor = new SimpleStringProperty("Black");
       penColors = new MenuButton("Pen Colors");
       makePenColorsMenu();

       turtleImage = new SimpleStringProperty("turtle.jpg");
       images = new MenuButton("Turtle Images");
       makeImagesMenu();



       MenuItem itemGerman = new MenuItem("German");
       MenuItem itemSpanish = new MenuItem("Spanish");
       MenuItem itemEnglish = new MenuItem("English");
       MenuButton language = new MenuButton("Language",null,itemGerman,itemSpanish,itemEnglish);
       Button help = new Button("Help");
       help.setOnAction(event -> { makeHelpScreen();
          });

       myView.getChildren().addAll(bgColors,penColors,language,images,help);
   }

   public Property getActiveTurtleImage()
   {
       return turtleImage;
   }

   private void makeImagesMenu()
   {
       for(String image: turtleImages.keySet())
       {
           MenuItem imageSelect = new MenuItem(image);
           imageSelect.setOnAction(e -> {
               turtleImage.setValue(turtleImages.getString(image));
           });
           images.getItems().add(imageSelect);

       }

   }

   private void makePenColorsMenu()
    {
        for(String color: penColorsNames.keySet())
        {

            MenuItem penColor = new MenuItem(color);
            penColor.setOnAction(e -> {
                activePenColor.setValue(color);
            });
            penColors.getItems().add(penColor);
        }
    }

   private void makeBackgroundColorsMenu()
   {
        for(String color: bgColorsNames.keySet())
        {

            MenuItem bgColor = new MenuItem(color);
            bgColor.setOnAction(e -> {
                activeBackgroundColor.setValue(color);
            });
            bgColors.getItems().add(bgColor);
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

   private void makeHelpScreen()
   {
       Stage stage1 = new Stage();
       Label helpText = new Label("This is a help screen");
       Group helpGroup = new Group();
       helpGroup.getChildren().addAll(helpText);
       Scene helpScreen = new Scene(helpGroup,400,400);
       stage1.setScene(helpScreen);
       stage1.show();
   }
}
