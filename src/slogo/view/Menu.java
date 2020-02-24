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

import java.util.ResourceBundle;

public class Menu {

    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final String TURTLE_IMAGES = "resources.TurtleImage";

    private HBox myView;
    private ResourceBundle penColors = java.util.ResourceBundle.getBundle(PEN_COLOR);
    private ResourceBundle turtleImages = java.util.ResourceBundle.getBundle(TURTLE_IMAGES);
    private MenuButton colors;
    private MenuButton images;
    private SimpleStringProperty activePenColor;
    private SimpleStringProperty turtleImage;

   public Menu()
   {
       myView = new HBox();
       activePenColor = new SimpleStringProperty("White");
       colors = new MenuButton("Colors");
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

       myView.getChildren().addAll(colors,language,images,help);
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
               System.out.println("clicked" + turtleImage.getValue());
           });
           images.getItems().add(imageSelect);

       }

   }

   private void makePenColorsMenu()
   {
        for(String color: penColors.keySet())
        {

            MenuItem penColor = new MenuItem(color);
            penColor.setOnAction(e -> {
                activePenColor.setValue(color);
            });
            colors.getItems().add(penColor);
        }
   }

   public Node getView()
   {
       return myView;
   }

   public Property getActivePenColor()
   {
       return activePenColor;
   }

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
