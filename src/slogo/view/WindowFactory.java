package slogo.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WindowFactory {
    private static final String CLASS_TO_PARAMETERS = "resources.parameters";
    private static final String INDEX_TO_PARAMNAME = "resources.parameternames";


    private Map<String,Object> possibleParameters;

    private ResourceBundle parameterList = java.util.ResourceBundle.getBundle(CLASS_TO_PARAMETERS);
    private ResourceBundle parameterNames = java.util.ResourceBundle.getBundle(INDEX_TO_PARAMNAME);


    public WindowFactory(List<Object> params)
    {
        possibleParameters = new HashMap<>();
        for(int i = 0;i<params.size();i++)
        {
            possibleParameters.put(parameterNames.getString(i+""),params.get(i));
        }

    }

    public Window makeWindow(String windowName)
    {
        
    }

}
