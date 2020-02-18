package slogo.view;

import javafx.scene.paint.Color;
//Created an interface specifically for graphic pane since the user has more control over it than the other panes which just display data

public interface GraphicPane {

    /**
     * This updates the drawings on the graphics screen left behind by the turtle
     * It is a part of the front-end external API
     */
    public void updateTrails();

    /**
     * This method allows one to set the background color of the graphics pane
     * This will be in the front-end internal API
     * @param back is color user chooses for background
     */
    public void setBackgroundColor(Color back);

    /**
     * This method allows one to set the pen color used to draw lines in the graphic pane
     * This will be in the front-end internal API
     * @param pen is the color chosen by the user for the pen
     */
    public void setPenColor(Color pen);

}
