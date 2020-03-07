package slogo.view.components;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class ColorPalette {

    private Map<Integer, Color> colorMatch;
    private ResourceBundle availableColorNames;


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

    public void setColor(int index, int red, int green, int blue)
    {
        Color indexColor = Color.rgb(red,green,blue);
        colorMatch.put(index,indexColor);
    }

    public Color getColor(int index)
    {
        return colorMatch.get(index);
    }


    public Set<Integer> getAvailableIndices()
    {
        return colorMatch.keySet();
    }
}
