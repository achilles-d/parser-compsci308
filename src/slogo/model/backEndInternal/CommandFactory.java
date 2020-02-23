package slogo.model.backEndInternal;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.commands.Command;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CommandFactory  {
    private BackEndTurtle turtle;
    public CommandFactory(){
      turtle=new BackEndTurtle();
    }

    public Object getCommand(String commandType, Double[] arguments){
        Object currentCommand = null;
        //System.out.println(commandType);
        try {

            Class<?> c = Class.forName("slogo.model.backEndInternal.commands."+commandType);

            Class<?>[] pType  = c.getDeclaredConstructors()[0].getParameterTypes();
            Object[] ar=new Object[pType.length];

            for (int j=0; j<pType.length; j++){
                ar[j]=arguments[j];

//                if(pType[j].equals(Double)){
//                    System.out.println("Ptype class is "+pType[j]);
//                }


            }

            Constructor<?> cons = c.getDeclaredConstructor(pType);
            currentCommand =cons.newInstance(ar);



           //System.out.println(pType[0].TYPE);

//            Class[] args=new Class[arguments.length];
//
//            for(int i=0; i<args.length; i++){
//                args[i]=Double.TYPE;
//            }
//            Constructor<?> cons = c.getDeclaredConstructor(args);
//            currentCommand =cons.newInstance((Object[]) arguments);

//           // Method method = c.getDeclaredMethod("execute");
//            //System.out.println("answe is " + method.invoke(object));
        } catch (Throwable e) {
            System.err.println(e);
        }


        return currentCommand;
    }

}
