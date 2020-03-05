package slogo.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private static final String CSS_ID = "PenState";

    private HBox myView;
    private VBox penInfo;
    private VBox penChanger;
    private HBox penThickButtons;
    private HBox penStatusButtons;
    private ViewTurtle myViewTurtle;
    private ColorPalette myColorPalette;
    private Label penStatus;
    private Label penColor;
    private Label penThickness;
    private Button penUp;
    private Button penDown;
    private Button thickPen;
    private Button thinPen;
    private SimpleDoubleProperty checkPenColor;


    private String penStatusText;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    public PenStateWindow(ViewTurtle view, ColorPalette colors)
    {
        myView = new HBox();
        myView.setId(CSS_ID);
        penInfo = new VBox();
        penChanger = new VBox();
        setSizing(penInfo,120);
        setSizing(penChanger,100);
        penThickButtons = new HBox();
        penStatusButtons = new HBox();

        myColorPalette = colors;
        myViewTurtle = view;


        initButtons();
        update();

        penThickButtons.getChildren().addAll(thinPen,thickPen);
        penThickButtons.setAlignment(Pos.CENTER);
        penStatusButtons.getChildren().addAll(penUp,penDown);
        penChanger.getChildren().addAll(penStatusButtons,penThickButtons);

        checkPenColor = myViewTurtle.getPenColorProperty();
        checkPenColor.addListener(e->{update();});


        myView.getChildren().addAll(penInfo,penChanger);
    }

    private void setSizing(VBox node,int size)
    {
        node.setMaxWidth(size);
        node.setPrefWidth(size);
    }

    private void initButtons()
    {
        penUp = new Button(visualText.getString("penup"));

        penUp.setOnAction(e->{
            myViewTurtle.getPenStatusProperty().setValue(false);
            update();
        });

        penDown = new Button(visualText.getString("pendown"));

        penDown.setOnAction(e->{
            myViewTurtle.getPenStatusProperty().setValue(true);
            update();
        });

        thickPen = new Button(visualText.getString("penthick"));
        thickPen.setOnAction(e->{
            myViewTurtle.changePenSize(1);
            update();
        });
        thinPen = new Button(visualText.getString("penthin"));
        thinPen.setOnAction(e->{
            myViewTurtle.changePenSize(-1);
            update();
        });


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
        penInfo.getChildren().clear();
        penInfo.getChildren().addAll(penStatus,penThickness,penColor);
    }

    @Override
    public Node getView() {
        return myView;
    }
}