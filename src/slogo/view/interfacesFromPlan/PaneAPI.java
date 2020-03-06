package slogo.view.interfacesFromPlan;

public interface PaneAPI {

    /**
     * this interface can be implemented by different panes in environment so
     * they can write their own update methods based on what they need to do to update
     */
    public void updateWindow();
}
