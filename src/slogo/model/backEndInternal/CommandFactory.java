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
    private List<Object > listOfCommands;
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
        listOfCommands=new ArrayList<>();

        for(BackEndTurtle bt: listOfTurtles){
            turtle=bt;
            listOfCommands.add(makeCommand(commandName, arguments));
        }
        return listOfCommands;

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

           System.out.println(" command name to create is  " + commandName);
           System.out.println("Totall number of inputs " + pType.length);
        System.out.println("For command " + commandName);


            for (int j = 0; j < pType.length; j++) {

                String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];
                System.out.println("Constructor type needs is " + className);


                    if (className.equals("BackEndTurtle")) {
                        ar[j] = turtle;

                    } else if (className.equals("TurtleController")) {
                        ar[j]=listOfTurtles;
                    }else if (className.equals("Coordinate")) {
                        ar[j] = turtle.getPosition();
                    }  else if(className.equals("UserVariableHandler")) {
                        ar[j] = userVariableHandler;
                    } else if(className.equals("List")){
                        ar[j]=arguments;
                        System.out.println("here is the data for the group "+arguments.toString());
                        System.out.println("size of the arguments "+j);
                    } else if(className.equals("Map")){
                        ar[j]=userCommandHandler;
                        System.out.println("here is the data for the group "+arguments.toString());
                        System.out.println("size of the arguments "+j);
                    }
                    else {
                        System.out.println(" current counter is "+inputCounter);
                        System.out.println("Size of the ptype is "+pType.length);
                        System.out.println("here is the data "+className);
                        System.out.println("size of argument "+arguments.size());
                        ar[j]=arguments.get(inputCounter);
                        System.out.println("Name of the userDefined command "+arguments.get(0).toString());
                        inputCounter++;
                    }

            }
            
            Constructor<?> cons = c.getDeclaredConstructor(pType);
           //System.out.println("Inputs to constructor "+ar[0]);

           for (Object obj: ar){
               System.out.println("Inputs to constructor "+obj);

           }

            currentCommand = cons.newInstance(ar);
        return currentCommand;
    }
//
//    public void updateCounter(Integer v) {
//        commandCounter = v;
//    }

}
