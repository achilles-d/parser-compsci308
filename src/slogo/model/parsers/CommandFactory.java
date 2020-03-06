package slogo.model.parsers;

import slogo.controller.TurtleController;
import slogo.model.turtle.BackEndTurtle;
import slogo.model.turtle.UserVariableHandler;
import slogo.model.commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandFactory {
    private  TurtleController turtleController;
    private UserVariableHandler userVariableHandler;
    private int inputCounter;
    private int stringCounter;
    private List<String> unExecutedCommands;
    private Map<String, List<Command>>  userCommandHandler;
    private List<Object > listOfCommands;
   // private int commandCounter;

    private List<BackEndTurtle> listOfTurtles = new ArrayList<>();
    private Integer index;

    public CommandFactory(TurtleController turtleController, UserVariableHandler userVariableHandler,
                          List<String> unExecutedCommands, Map<String, List<Command>>  userCommandHandler ) {

        this.unExecutedCommands=unExecutedCommands;
        this.turtleController=turtleController;
        this.userVariableHandler=userVariableHandler;
        this.userCommandHandler=userCommandHandler;
    }

    public Object getCommand(String commandName, List<Object> arguments) throws InvocationTargetException,
            NoSuchMethodException, ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {
        listOfCommands=new ArrayList<>();

        listOfTurtles= (List<BackEndTurtle>) turtleController.getAllActiveBackEndTurtles();
        System.out.println("Size of listOfTurtles "+listOfTurtles.get(0));
        //turtleController.getNumberOfTurtles()
        System.out.println("Active turtles " +turtleController.getMap());
        for(BackEndTurtle bt: listOfTurtles){
            listOfCommands.add(makeCommand(commandName, arguments,  bt));
        }
        return listOfCommands;

    }

    private Object makeCommand(String commandName, List<Object> arguments, BackEndTurtle turtle) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            ClassNotFoundException {

            Object currentCommand = null;

            Class<?> c = Class.forName("slogo.model.commands." + commandName);
            Class<?>[] pType = c.getDeclaredConstructors()[0].getParameterTypes();// edit it later
            Object[] ar = new Object[pType.length];
            inputCounter = 0;

          // System.out.println(" command name to create is  " + commandName);
           //System.out.println("Totall number of inputs " + pType.length);
           //System.out.println("For command " + commandName);

        System.out.println("Constructor type "+commandName);
            for (int j = 0; j < pType.length; j++) {

                String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];

                    if (className.equals("BackEndTurtle")) {
                        ar[j] = turtle;
                    } else if (className.equals("TurtleController")) {
                        System.out.println(" tutrtle controller");
                        ar[j]=turtleController;
                    }else if (className.equals("Coordinate")) {
                        ar[j] = turtle.getPosition();
                    }  else if(className.equals("UserVariableHandler")) {
                        ar[j] = userVariableHandler;
                    } else if(className.equals("List")){
                        ar[j]=arguments;
                        System.out.println("List type "+arguments.toString());

                    } else if(className.equals("Map")){
                        ar[j]=userCommandHandler;
                        System.out.println("map ");

                    }
                    else {

                        System.out.println("Command argument type "+className);

                        ar[j]=arguments.get(inputCounter);
                        //System.out.println("Name of the userDefined command "+arguments.get(0).toString());
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
