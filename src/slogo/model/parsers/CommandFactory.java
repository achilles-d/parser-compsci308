package slogo.model.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import slogo.controller.Language;
import slogo.controller.TurtleController;
import slogo.model.commands.Command;
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
    private Map<String, List<List<Command>>> userDefinedCommands;
    private TurtleController turtleController;


    public CommandFactory(TurtleController turtleController, UserVariableHandler userVariableHandler,
                          List<String> unExecutedCommands,Language language,
                          Map<String, List<List<Command>>>  userDefinedCommand) {

        this.userDefinedCommands=userDefinedCommand;
        this.unExecutedCommands=unExecutedCommands;
        this.turtle = turtleController.getBackEndTurtle(0);
        //turtleController.getA
        this.turtleController=turtleController;
        this.language=language;
        this.userVariableHandler=userVariableHandler;
        match=new HashMap<>();
        mathMethods();
    }


    public Object getCommand(String commandName, List<Object> arguments){

       List<Object> listOfCommands= new ArrayList<>();

        List<BackEndTurtle> listOfTurtles= (List<BackEndTurtle>) turtleController.getAllBackEndTurtles();
        System.out.println("Size of turtle "+ listOfTurtles.size());

        if(needTurtle(commandName)){
            for(BackEndTurtle bt: listOfTurtles){
                System.out.println("Should add more turtles "+bt.toString());
                turtle=bt;
                listOfCommands.add(makeCommand(commandName, arguments));
            }
        } else{
            listOfCommands.add(makeCommand(commandName, arguments));
        }

        return listOfCommands;//listOfCommands.get(0);


    }

    private Object makeCommand(String commandName, List<Object> arguments) {
        Object currentCommand = null;
        Class<?> c = null;
        System.out.println("command to create "+commandName);
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
    private Map<String, List<List<Command>>> getUserDefinedCommands(){
        return userDefinedCommands;
    }

    private Language getLanguage(){
        return language;
    }
    private TurtleController getTurtleController(){
        return turtleController;
    }

  private boolean needTurtle(String commandName){
      Class<?> c = null;
      try {
          c = Class.forName(THIS_CLASS_PATH + commandName);

      } catch (ClassNotFoundException e) {
          throw new InvalidCommandException(errors.getString(IMPOSSIBLE_COMMANDS), e);
      }
      Class<?>[] pType = c.getDeclaredConstructors()[0].getParameterTypes();

      for(int j=0; j<pType.length;j++){
          String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];
          System.out.println("types needed "+className);
          if(className.equals("BackEndTurtle")){
              return true;
          }
      }
      return false;
    }

}
