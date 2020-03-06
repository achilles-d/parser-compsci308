package slogo.model.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import slogo.controller.Language;
import slogo.model.exceptions.ExecutionException;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.Coordinate;
import slogo.model.turtle.UserVariableHandler;
import slogo.model.exceptions.InvalidCommandException;

public class CommandFactory {
    private BackEndTurtle turtle;
    private UserVariableHandler userVariableHandler;
    private int inputCounter;
    private int stringCounter;
    private List<String> unExecutedCommands;
    private int commandCounter;

    private static final String RESOURCES_PACKAGE="resources.modelproperties.";
    private static final String THIS_CLASS_PATH="slogo.model.commands.";
    private ResourceBundle methods = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ObjectMatch");
    private ResourceBundle errors = ResourceBundle.getBundle(RESOURCES_PACKAGE + "ExceptionMessage");

    private List<BackEndTurtle> listOfTurtles = new ArrayList<>();
    private Integer index;
    private Map<String, Method> match;
    private  static final String NO_FILE="noFile";
    private  static final String IMPOSSIBLE_COMMANDS="impossibleCommand";
    private  static final String CLASS_NOT_FOUND="classNotFound";
    private  static final String LIST="List";
    private Language language;


    public CommandFactory(BackEndTurtle turtle, UserVariableHandler userVariableHandler,
                          List<String> unExecutedCommands,Language language) {

        this.unExecutedCommands=unExecutedCommands;
        this.turtle = turtle;
        this.language=language;
        this.userVariableHandler=userVariableHandler;
        match=new HashMap<>();
        mathMethods();
    }


    public Object getCommand(String commandName, List<Object> arguments){
        return makeCommand(commandName, arguments);
    }

    private Object makeCommand(String commandName, List<Object> arguments) {
        Object currentCommand = null;
        Class<?> c = null;
        try {
            c = Class.forName(THIS_CLASS_PATH + commandName);

        } catch (ClassNotFoundException e) {
            throw new InvalidCommandException(errors.getString(IMPOSSIBLE_COMMANDS), e);
        }
        Class<?>[] pType = c.getDeclaredConstructors()[0].getParameterTypes();// edit it later
        Object[] ar = new Object[pType.length];
        inputCounter = 0;

        populateTypeOfArguments(arguments, pType, ar);
        Constructor<?> cons = null;
          try {
            cons = c.getDeclaredConstructor(pType);
          } catch (NoSuchMethodException e) {
            throw new InvalidCommandException(errors.getString(IMPOSSIBLE_COMMANDS), e);
          }
          try {
            currentCommand = cons.newInstance(ar);
          } catch (InstantiationException | IllegalAccessException  | InvocationTargetException e) {
            throw new InvalidCommandException(CLASS_NOT_FOUND);
          }
          return currentCommand;
        }

    private void populateTypeOfArguments(List<Object> arguments, Class<?>[] pType, Object[] ar) {
        for (int j = 0; j < pType.length; j++) {
            String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];

            if(match.containsKey(className)){
                try {
                    ar[j] =match.get(className).invoke(this, null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new InvalidCommandException(errors.getString(NO_FILE), e);
                }
            } else if(className.equals(LIST)){
                ar[j]=arguments;
            } else {
                ar[j]=arguments.get(inputCounter);
                inputCounter++;
            }
        }
    }


    private void mathMethods() {
        for(String str:methods.keySet()){
            try {
                Class[] clszz=null;
                match.put(str, this.getClass().getDeclaredMethod(methods.getString(str),clszz));
            } catch (NoSuchMethodException e) {
                throw new ExecutionException(errors.getString(NO_FILE), e);
            }
        }
    }
    private BackEndTurtle getTurtle(){
        return turtle;
    }
    private Coordinate getCoordinate(){
        return turtle.getPosition();
    }
    private UserVariableHandler getUserVariableHandler(){
        return userVariableHandler;
    }

    private Language getLanguage(){
        return language;
    }

}
