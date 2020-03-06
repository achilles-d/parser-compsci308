package slogo.view.windows;

import slogo.model.exceptions.WindowCreationError;
import slogo.view.windows.Window;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WindowFactory {
    private static final String CLASS_TO_PARAMETERS = "resources.configuration.parameters";
    private static final String INDEX_TO_PARAMNAME = "resources.configuration.parameternames";
    private static final String CLASSPATH = "slogo.view.windows.";
    private static final String WINDOW = "Window";
    private static final String ERRORS = "resources.errors.InvalidCommandExceptionText";
    private static final String WINDOW_ERROR = "WindowError";
    private static final int MAXLENGTH = 4;
    private static final int FIRST_PARAM = 0;
    private static final int SECOND_PARAM = 1;
    private static final int THIRD_PARAM = 2;
    private static final int FOURTH_PARAM = 3;


    private Map<String,Object> possibleParameters;

    private ResourceBundle parameterList = java.util.ResourceBundle.getBundle(CLASS_TO_PARAMETERS);
    private ResourceBundle parameterNames = java.util.ResourceBundle.getBundle(INDEX_TO_PARAMNAME);
    private ResourceBundle errorMessages = java.util.ResourceBundle.getBundle(ERRORS);


    public WindowFactory(List<Object> params)
    {
        possibleParameters = new HashMap<>();
        for(int i = FIRST_PARAM; i<params.size(); i++)
        {
            possibleParameters.put(parameterNames.getString(i+""),params.get(i));
        }

    }

    public Window makeWindow(String windowName)
    {
        Constructor[] constructors = new Constructor[FIRST_PARAM];
        try {
            constructors = Class.forName(CLASSPATH+windowName+ WINDOW).getConstructors();
        } catch (ClassNotFoundException e) {
            throw new WindowCreationError(errorMessages.getString(WINDOW_ERROR));
        }
        String[] parameters = parameterList.getString(windowName).split(",");

        try {


            if (constructors[FIRST_PARAM].getParameterCount() == MAXLENGTH) {
                return (Window) constructors[FIRST_PARAM].newInstance(possibleParameters.get(parameters[FIRST_PARAM]), possibleParameters.get(parameters[SECOND_PARAM]),
                        possibleParameters.get(parameters[THIRD_PARAM]), possibleParameters.get(parameters[FOURTH_PARAM]));
            } else {
                return (Window) constructors[FIRST_PARAM].newInstance(possibleParameters.get(parameters[FIRST_PARAM]), possibleParameters.get(parameters[SECOND_PARAM]),
                        possibleParameters.get(parameters[THIRD_PARAM]));

            }
        }
        catch(InstantiationException|IllegalAccessException| InvocationTargetException e)
        {
            throw new WindowCreationError(errorMessages.getString(WINDOW_ERROR));
        }

    }

}
