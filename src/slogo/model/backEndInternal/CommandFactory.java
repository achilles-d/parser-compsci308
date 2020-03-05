package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandFactory {
    private BackEndTurtle turtle;
    private UserVariableHandler userVariableHandler;
    private int inputCounter;
    private int stringCounter;
    private List<String> unExecutedCommands;
    private Map<String, List<Command>>  userCommandHandler;
   // private int commandCounter;

    private List<BackEndTurtle> listOfTurtles = new ArrayList<>();
    private Integer index;

    public CommandFactory(BackEndTurtle turtle, UserVariableHandler userVariableHandler,
                          List<String> unExecutedCommands,Map<String, List<Command>>  userCommandHandler ) {
        //this.commandCounter=counter;
        this.unExecutedCommands=unExecutedCommands;
        this.turtle = turtle;
        this.userVariableHandler=userVariableHandler;
        this.userCommandHandler=userCommandHandler;
    }

    public Object getCommand(String commandName, List<Object> arguments) throws InvocationTargetException,
            NoSuchMethodException, ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        return makeCommand(commandName, arguments);

        //return
    }

    private Object makeCommand(String commandName, List<Object> arguments) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            ClassNotFoundException {

            Object currentCommand = null;

            Class<?> c = Class.forName("slogo.model.backEndInternal.commands." + commandName);
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
                } else {
                    System.out.println("here is the data "+arguments.get(inputCounter).toString());
                    ar[j]=arguments.get(inputCounter);
                    System.out.println("here is the data "+arguments.get(inputCounter).toString());
                    inputCounter++;
                }
            }

        //                else if(className.equals("List")){
//                    ar[j]=arguments;
//                    System.out.println("here is the data for the group "+arguments.toString());
//                    System.out.println("size of the arguments "+j);
//                }

            Constructor<?> cons = c.getDeclaredConstructor(pType);
           System.out.println("Inputs size to constructor "+ar.length);

            currentCommand = cons.newInstance(ar);
        return currentCommand;
    }
//
//    public void updateCounter(Integer v) {
//        commandCounter = v;
//    }

}
