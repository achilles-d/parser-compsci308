package slogo.view.components;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Saurav Sanjay
 * This object represents the color palette that will connect indices to colors
 */
public class ColorPalette {

    private Map<Integer, Color> colorMatch;
    private ResourceBundle availableColorNames;


    /**
     * Creates a color palette object with a given colors configuration file that associates the colors
     * with their corresponding indices
     * @param colorsFile that matches color names with integer indices
     */
    public ColorPalette(String colorsFile)
    {
        colorMatch = new HashMap<>();
        availableColorNames = ResourceBundle.getBundle(colorsFile);
        init();

    }

    private void init()
    {
        for(String colorIndex:availableColorNames.keySet())
        {
            colorMatch.put(Integer.parseInt(colorIndex),Color.valueOf(availableColorNames.getString(colorIndex)));
        }
    }

    /**
     * Sets a color in color palette to given RGB value
     * @param index index of color that needs to be changed
     * @param red red value
     * @param green green value
     * @param blue blue value
     */
    public void setColor(int index, int red, int green, int blue)
    {
        Color indexColor = Color.rgb(red,green,blue);
        colorMatch.put(index,indexColor);
    }

    /**
     * Returns color object associated with index
     * @param index of needed color
     * @return Color object at index
     */
    public Color getColor(int index)
    {
        return colorMatch.get(index);
    }


    /**
     * Returns the available indices that colors are mapped to
     * @return list of integers that colors are mapped to
     */
    public Set<Integer> getAvailableIndices()
    {
        return colorMatch.keySet();
    }
}
