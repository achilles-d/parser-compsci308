package slogo.view;

import java.util.ArrayList;
import java.util.List;

public class CodeStage {

    private static final int READY_CODE = 0;
    private List<String> code;

    public CodeStage()
    {
        code = new ArrayList<>();
    }

    public boolean hasCodeToBeParsed(){return code.size()>0;}

    public void addCodeToBeParsed(String lines)
    {
        code.add(lines);
    }

    public String getCodeToBeParsed()
    {
        return code.get(READY_CODE);
    }

    public void clearStagedCode()
    {
        code.clear();
    }
}
