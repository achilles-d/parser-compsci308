package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class PenStateWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final int ICON_SIZE = 20;

    private HBox myView;
    private VBox penInfo;
    private VBox penChanger;
    private ViewTurtle myViewTurtle;
    private ColorPalette myColorPalette;
    private Label penStatus;
    private Label penColor;
    private Label penThickness;
    private String penStatusText;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    public PenStateWindow(ViewTurtle view, ColorPalette colors)
    {
        myView = new HBox();
        penInfo = new VBox();
        penChanger = new VBox();
        myColorPalette = colors;
        myViewTurtle = view;
        update();
        myView.getChildren().addAll(penStatus,penThickness,penColor);
    }


    private Label makeLabel(String identifier, String info)
    {
        return new Label(identifier + ":" + info);
    }

    private Label makeLabelWithGraphic(String identifier, Node graphic)
    {
        return new Label(identifier,graphic);
    }



    @Override
    public void update() {
        if(myViewTurtle.getPenStatusProperty().get())
        {
            penStatusText =visualText.getString("pendown");

        }
        else
        {
            penStatusText = visualText.getString("penup");
        }
        penStatus = makeLabel(visualText.getString("penstatus"),penStatusText);
        penThickness = makeLabel(visualText.getString("penthickness"),myViewTurtle.getPenSize()+"");
        Rectangle colorPatch = new Rectangle(ICON_SIZE, ICON_SIZE,myColorPalette.getColor((int)myViewTurtle.getPenColorIndex()));
        penColor = makeLabelWithGraphic(visualText.getString("pencolor"),colorPatch);
    }

    @Override
    public Node getView() {
        return null;
    }
}
