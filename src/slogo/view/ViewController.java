package slogo.view;

public class ViewController {

    private LogoVisualization myVis;

    public ViewController(LogoVisualization vis)
    {
        myVis = vis;
    }

    public void setBackgroundColor(String color)
    {
        myVis.setBackgroundColor(color);
    }
}
