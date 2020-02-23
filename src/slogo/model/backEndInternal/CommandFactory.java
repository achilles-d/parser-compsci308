package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CommandFactory  {

    public Object getCommand(String commandType, Double[] arguments){
        Object currentCommand = null;
        try {

            Class<?> c = Class.forName("slogo.model.backEndInternal.commands."+commandType);
            Class[] args=new Class[arguments.length];
            for(int i=0; i<args.length; i++){
                args[i]=Double.TYPE;
            }
            Constructor<?> cons = c.getDeclaredConstructor(args);
            currentCommand =cons.newInstance((Object[]) arguments);
           // Method method = c.getDeclaredMethod("execute");
            //System.out.println("answe is " + method.invoke(object));
        } catch (Throwable e) {
            System.err.println(e);
        }


        return currentCommand;
    }

}
