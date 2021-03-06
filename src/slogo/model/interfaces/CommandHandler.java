package slogo.model.interfaces;

import java.util.List;

public interface CommandHandler {
    /**
     * This will be in the external back-end API, and will essentially update the command history by communicating with
     * the internal backend to get the new commands and giving the command history to the frontend external.
     */
    public List<String> getCommandHistory();
    public void updateCommandHistory(String nextCommand);

}
