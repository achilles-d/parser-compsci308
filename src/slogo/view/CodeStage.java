package slogo.view;

import java.util.ArrayList;
import java.util.List;

public class CodeStage {

    private List<String> code;

    public CodeStage()
    {
        code = new ArrayList<>();
    }

    public void addCodeToBeParsed(String lines)
    {
        code.add(lines);
    }

    public String getCodeToBeParsed()
    {
        return code.get(0);
    }

    public void clearStagedCode()
    {
        code.clear();
    }
}