package slogo.model.backEndInternal;

import slogo.model.Command;
import slogo.model.InvalidCommandException;
import slogo.model.Parser;

public class CommandParser implements Parser {
    @Override
    public void parseCode(String consoleInput) throws InvalidCommandException {

    }

    @Override
    public Command getCommand(String commandInput) throws InvalidCommandException {
        return null;
    }
}
