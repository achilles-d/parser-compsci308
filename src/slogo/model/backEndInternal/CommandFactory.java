package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CommandFactory  {

    public Command getCommand(String commandType){
        Command object = null;
        try {

            Class<?> c = Class.forName("slogo.model.backEndInternal.commands.Difference");
            Constructor<?> cons = c.getDeclaredConstructor(Double.TYPE, Double.TYPE);
            object = (Command) cons.newInstance(45.0, 45.6);
           // Method method = c.getDeclaredMethod("execute");
            //System.out.println("answe is " + method.invoke(object));
        } catch (Throwable e) {
            System.err.println(e);
        }


        return object;
    }

}
