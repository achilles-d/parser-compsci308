package slogo.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Menu {

   private HBox myView;

   public Menu()
   {
       myView = new HBox();
       MenuItem itemBlue = new MenuItem("Blue");
       MenuItem itemGreen = new MenuItem("Green");
       MenuButton colors = new MenuButton("Colors",null,itemBlue,itemGreen);

       MenuItem itemGerman = new MenuItem("German");
       MenuItem itemSpanish = new MenuItem("Spanish");
       MenuItem itemEnglish = new MenuItem("English");
       MenuButton language = new MenuButton("Language",null,itemGerman,itemSpanish,itemEnglish);
       Button help = new Button("Help");
       help.setOnAction(event -> { makeHelpScreen();
          });

       myView.getChildren().addAll(colors,language,help);
   }

   public Node getView()
   {
       return myView;
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
