package slogo.model.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.UserVariableHandler;
import slogo.model.exceptions.InvalidCommandException;

public class CommandFactory {
    private BackEndTurtle turtle;
    private UserVariableHandler userVariableHandler;
    private int inputCounter;
    private int stringCounter;
    private List<String> unExecutedCommands;
    private int commandCounter;

    private List<BackEndTurtle> listOfTurtles = new ArrayList<>();
    private Integer index;

    public CommandFactory(BackEndTurtle turtle, UserVariableHandler userVariableHandler,
                          List<String> unExecutedCommands, int counter) {
        this.commandCounter=counter;
        this.unExecutedCommands=unExecutedCommands;
        this.turtle = turtle;
        this.userVariableHandler=userVariableHandler;
    }

    public Object getCommand(String commandName, List<Object> arguments) throws InvalidCommandException{

        return makeCommand(commandName, arguments);

        //return
    }

    private Object makeCommand(String commandName, List<Object> arguments) throws InvalidCommandException {

            Object currentCommand = null;

      Class<?> c = null;
      try {
        c = Class.forName("slogo.model.commands." + commandName);
      } catch (ClassNotFoundException e) {
        throw new InvalidCommandException("Temp", e);    //FIXME improve error msg
      }
      Class<?>[] pType = c.getDeclaredConstructors()[0].getParameterTypes();// edit it later
            Object[] ar = new Object[pType.length];
            inputCounter = 0;

           System.out.println("Constructor command name " + commandName);


            for (int j = 0; j < pType.length; j++) {

                String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];
                System.out.println("Constructor name is " + className);

                if (className.equals("BackEndTurtle")) {
                    ar[j] = turtle;
                } else if (className.equals("Coordinate")) {
                    ar[j] = turtle.getPosition();
                }  else if(className.equals("UserVariableHandler")) {
                    ar[j] = userVariableHandler;

                } else if(className.equals("List")){
                    ar[j]=arguments;
                    System.out.println("here is the data for the group "+arguments.toString());
                    System.out.println("size of the arguments "+j);
                } else {
                    ar[j]=arguments.get(inputCounter);
                    System.out.println("here is the data "+arguments.get(inputCounter).toString());
                    inputCounter++;
                }
            }
      Constructor<?> cons = null;
      try {
        cons = c.getDeclaredConstructor(pType);
      } catch (NoSuchMethodException e) {
        throw new InvalidCommandException("temp", e); //FIXME improve error message
      }
      System.out.println("Inputs size to constructor "+ar.length);

      try {
        currentCommand = cons.newInstance(ar);
      } catch (InstantiationException | IllegalAccessException  | InvocationTargetException e) {
        throw new InvalidCommandException("temp", e); //FIXME improve error message
      }
      return currentCommand;
    }

    public void updateCounter(Integer v) {
        commandCounter = v;
    }

}
