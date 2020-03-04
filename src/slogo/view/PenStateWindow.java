package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class PenStateWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";

    private VBox myView;
    private ViewTurtle myViewTurtle;
    private ColorPalette myColorPalette;
    private Label penStatus;
    private Label penColor;
    private Label penThickness;
    private String penStatusText;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    public PenStateWindow(ViewTurtle view, ColorPalette colors)
    {
        myView = new VBox();
        myColorPalette = colors;
        myViewTurtle = view;
        init();
    }

    private void init()
    {
        if(myViewTurtle.getPenStatusProperty().get())
        {
            penStatusText =visualText.getString("pendown");

        }
        else
        {
            penStatusText = visualText.getString("penup");
        }
        penStatus = makeLabel(visualText.getString("penstatus"));
    }

    private Label makeLabel(String identifier, String info)
    {
        return new Label(identifier + ":" + info);
    }

    private Label makeLabelWithGraphic(String identifier, Node graphic)
    {

    }



    @Override
    public void update() {

    }

    @Override
    public Node getView() {
        return null;
    }
}
