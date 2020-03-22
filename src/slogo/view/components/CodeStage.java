package slogo.view.components;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Saurav Sanjay
 * This class is used to keep track of text that needs to be
 * sent to the parser to be parsed as code
 *
 */

public class CodeStage {

    private static final int READY_CODE = 0;

    private List<String> code;

    /**
     * Creates a new CodeStage object
     */
    public CodeStage()
    {
        code = new ArrayList<>();
    }


    /**
     * checks whether there is any code ready to be parsed
     * @return boolean that indicates if code present that needs to be parsed
     */
    public boolean hasCodeToBeParsed(){return code.size()>0;}

    /**
     * Stages code to be parsed later
     * @param lines of code that need to be parsed
     */
    public void addCodeToBeParsed(String lines)
    {
        code.add(lines);
    }

    /**
     * Returns text code that needs to be parsed, operates first in first out
     * @return string of code to be parsed
     */
    public String getCodeToBeParsed()
    {
        return code.get(READY_CODE);
    }

    /**
     * Clears staged code so no code present that needs to be parsed
     */
    public void clearStagedCode()
    {
        code.clear();
    }
}
