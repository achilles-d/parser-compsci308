package slogo.model.backEndInternal;

        import java.lang.reflect.Constructor;
        import java.lang.reflect.InvocationTargetException;
        import java.util.List;

public class CommandFactory {
    private BackEndTurtle turtle;
    private UserVariableHandler userVariableHandler;
    private int inputCounter;
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

            System.out.println("Constructor arguemnt size " + pType.length);


            for (int j = 0; j < pType.length; j++) {

                String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];

                if (className.equals("BackEndTurtle")) {
                    ar[j] = turtle;
                } else if (className.equals("Coordinate")) {
                    ar[j] = turtle.getPosition();
                }  else if(className.equals("UserVariableHandler")){
                    ar[j]=userVariableHandler;
                } else{
                    ar[j]=arguments.get(inputCounter);
                    inputCounter++;
                }
            }

            Constructor<?> cons = c.getDeclaredConstructor(pType);
            System.out.println("Inputs to constructor "+ar[ar.length-1].toString());

            currentCommand = cons.newInstance(ar);
        return currentCommand;
    }

    public void updateCounter(Integer v) {
        commandCounter = v;
    }

}
