package slogo.view;

public interface Pane {

    /**
     * this interface can be implemented by different panes in environment so
     * they can write their own update methods based on what they need to do to update
     */
    public void updateWindow();
}
