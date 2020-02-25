package slogo.model.backEndInternal;

        import java.lang.reflect.Constructor;
        import java.lang.reflect.InvocationTargetException;

public class CommandFactory {
    private BackEndTurtle turtle;
    private UserVariableHandler userVariableHandler;
    private int counter;

    public CommandFactory(BackEndTurtle turtle, UserVariableHandler userVariableHandler) {

        this.turtle = turtle;
        this.userVariableHandler=userVariableHandler;
    }

    public Object getCommand(String commandType, Double[] arguments) throws InvocationTargetException,
            NoSuchMethodException, ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        return makeCommand(commandType, arguments);
        //return
    }

    private Object makeCommand(String commandType, Double[] arguments) throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            ClassNotFoundException {

        Object currentCommand = null;


            Class<?> c = Class.forName("slogo.model.backEndInternal.commands." + commandType);
            System.out.println(commandType);

            Class<?>[] pType = c.getDeclaredConstructors()[0].getParameterTypes();
            Object[] ar = new Object[pType.length];
            counter = 0;

            for (int j = 0; j < pType.length; j++) {
                String className = (pType[j].getName().split("[.]"))[pType[j].getName().split("[.]").length - 1];
                if (className.equals("BackEndTurtle")) {
                    ar[j] = turtle;
                } else if (className.equals("Coordinate")) {
                    ar[j] = turtle.getPosition();
                }  else if(className.equals("UserVariableHandler")){
                    ar[j]=userVariableHandler;
                }
                else {
                    ar[j] = arguments[counter];
                    counter++;
                }
            }

            Constructor<?> cons = c.getDeclaredConstructor(pType);
            currentCommand = cons.newInstance(ar);
        return currentCommand;
    }

}
