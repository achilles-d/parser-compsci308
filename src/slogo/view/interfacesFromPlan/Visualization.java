package slogo.view.interfacesFromPlan;

public interface Visualization {

    /**
     * This method will update the entire environment's view
     * It will be in front-end external API
     */
    public void updateView();

    /**
     * This will update all the panes in the view
     * It will be in the front-end external API.
     * We chose to have an overall panes update and specific window updates as well, in case down the line
     * we want to have the ability to update one specific pane and keep the rest the same
     */
    public void updatePanes();

    /**
     * This will update the variables pane
     */
    public void updateVariablesWindow();

    /**
     * This will update the graphics pane
     * It will be in the front-end external API.
     */
    public void updateGraphicsWindow();

    /**
     * This will update the graphics pane
     * It will be in the front-end external API.
     */
    public void updateCommandWindow();

    /**
     * This will update the graphics pane
     * It will be in the front-end external API.
     */
    public void updateHistoryWindow();

    /**
     * This will clear the graphics pane screen of all drawings
     * It will be in the front-end external API.
     */
    public void clearScreen();

    /**
     * Based on the error name given, it will display a user-friendly message to explain what the error was
     * It will be in the front-end external API.
     * @param exceptionName is the name of the exception
     */
    public void displayError(String exceptionName);

    /**
     * This will set the language of the environment and parsing to the specified language
     It will be in the front-end internal API.
     * @param languageName will be name of language that will be used to find appropriate resource folder
     */
    public void setLanguage(String languageName);

    /**
     * This method will allow one to access the help screen
     * It will be in the front-end internal API.
     */
    public void accessHelp();

}
