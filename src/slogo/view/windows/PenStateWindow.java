package slogo.view.windows;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import slogo.view.components.ColorPalette;
import slogo.view.components.ViewTurtle;

import java.util.ResourceBundle;

public class PenStateWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final int ICON_SIZE = 20;
    private static final String CSS_ID = "PenState";
    private static final int PEN_INFO_SIZE = 120;
    private static final int PEN_CHANGE_SIZE = 100;
    private static final String PENUP = "penup";
    private static final String PENDOWN = "pendown";
    private static final String PENTHICK = "penthick";
    private static final String PENTHIN = "penthin";
    private static final String PENSTATUS = "penstatus";
    private static final String PENTHICKNESS = "penthickness";
    private static final String PENCOLOR = "pencolor";

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
        setSizing(penInfo, PEN_INFO_SIZE);
        setSizing(penChanger, PEN_CHANGE_SIZE);
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
        penUp = new Button(visualText.getString(PENUP));

        penUp.setOnAction(e->{
            myViewTurtle.getPenStatusProperty().setValue(false);
            update();
        });

        penDown = new Button(visualText.getString(PENDOWN));

        penDown.setOnAction(e->{
            myViewTurtle.getPenStatusProperty().setValue(true);
            update();
        });

        thickPen = new Button(visualText.getString(PENTHICK));
        thickPen.setOnAction(e->{
            myViewTurtle.changePenSize(1);
            update();
        });
        thinPen = new Button(visualText.getString(PENTHIN));
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
            penStatusText =visualText.getString(PENDOWN);

        }
        else
        {
            penStatusText = visualText.getString(PENUP);
        }
        penStatus = makeLabel(visualText.getString(PENSTATUS),penStatusText);
        penThickness = makeLabel(visualText.getString(PENTHICKNESS),myViewTurtle.getPenSize()+"");
        Rectangle colorPatch = new Rectangle(ICON_SIZE, ICON_SIZE,myColorPalette.getColor((int)myViewTurtle.getPenColorIndex()));
        penColor = makeLabelWithGraphic(visualText.getString(PENCOLOR),colorPatch);
        penInfo.getChildren().clear();
        penInfo.getChildren().addAll(penStatus,penThickness,penColor);
    }

    @Override
    public Node getView() {
        return myView;
    }
}
