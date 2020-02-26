package slogo.model.backEndInternal;

        import java.lang.reflect.Constructor;
        import java.lang.reflect.InvocationTargetException;
        import java.util.List;

public class CommandFactory {
    private BackEndTurtle turtle;
    private UserVariableHandler userVariableHandler;
    private int doubleCounter;
    private int stringCounter;
    private List<String> unExecutedCommands;
    private int commandCounter;

    public CommandFactory(BackEndTurtle turtle, UserVariableHandler userVariableHandler,
                          List<String> unExecutedCommands, int counter) {
        this.commandCounter=counter;
        this.unExecutedCommands=unExecutedCommands;
        this.turtle = turtle;
        this.userVariableHandler=userVariableHandler;
    }

    public Object getCommand(List<String> commandWithDependency, Double[] arguments) throws InvocationTargetException,
            NoSuchMethodException, ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {


        return makeCommand(commandWithDependency, arguments);
        //return
    }

    private Object makeCommand(List<String> commandWithDependency, Double[] arguments) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            ClassNotFoundException {
            Object currentCommand = null;

            Class<?> c = Class.forName("slogo.model.backEndInternal.commands." + commandWithDependency.get(0));
            Class<?>[] pType = c.getDeclaredConstructors()[0].getParameterTypes();
            Object[] ar = new Object[pType.length];
            doubleCounter = 0;
            stringCounter= 1;
            System.out.println("Constructor arguemnt size " + pType.length);


            for (int j = 0; j < pType.length; j++) {

                String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];

                if (className.equals("BackEndTurtle")) {
                    ar[j] = turtle;
                } else if (className.equals("Coordinate")) {
                    ar[j] = turtle.getPosition();
                }  else if(className.equals("UserVariableHandler")){
                    ar[j]=userVariableHandler;
                } else if(className.equals("String")){
                    ar[j]=commandWithDependency.get(stringCounter);
                    stringCounter++;

                } else if(className.equals("List")){ //list of commands
                    //System.out.println("Class name list "+ className);
                    ar[j]=unExecutedCommands;

                }  else if(className.equals("Integer")){
                    //System.out.println("Counter integer "+ className);
                    ar[j]=commandCounter;
                }
                else {
                    //System.out.println("Counter double "+ className);
                    ar[j] = arguments[doubleCounter];
                    doubleCounter++;
                }
            }

            Constructor<?> cons = c.getDeclaredConstructor(pType);
            currentCommand = cons.newInstance(ar);
        return currentCommand;
    }

}
