package slogo.view;

import slogo.model.exceptions.WindowCreationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WindowFactory {
    private static final String CLASS_TO_PARAMETERS = "resources.parameters";
    private static final String INDEX_TO_PARAMNAME = "resources.parameternames";
    private static final String CLASSPATH = "slogo.view.";
    private static final String WINDOW = "Window";
    private static final String ERRORS = "resources.errors.InvalidCommandExceptionText";
    private static final String WINDOW_ERROR = "WindowError";


    private Map<String,Object> possibleParameters;

    private ResourceBundle parameterList = java.util.ResourceBundle.getBundle(CLASS_TO_PARAMETERS);
    private ResourceBundle parameterNames = java.util.ResourceBundle.getBundle(INDEX_TO_PARAMNAME);
    private ResourceBundle errorMessages = java.util.ResourceBundle.getBundle(ERRORS);


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
        Constructor[] constructors;
        try {
            constructors = Class.forName(CLASSPATH+windowName+ WINDOW).getConstructors();
        } catch (ClassNotFoundException e) {
            throw new WindowCreationError(errorMessages.getString(WINDOW_ERROR));
        }
        String[] parameters = parameterList.getString(windowName).split(",");

        try {


            if (constructors[0].getParameterCount() == 4) {
                return (Window) constructors[0].newInstance(possibleParameters.get(parameters[0]), possibleParameters.get(parameters[1]),
                        possibleParameters.get(parameters[2]), possibleParameters.get(parameters[3]));
            } else {
                return (Window) constructors[0].newInstance(possibleParameters.get(parameters[0]), possibleParameters.get(parameters[1]),
                        possibleParameters.get(parameters[2]));

            }
        }
        catch(InstantiationException|IllegalAccessException| InvocationTargetException e)
        {
            throw new WindowCreationError(errorMessages.getString(WINDOW_ERROR));
        }

    }

}
