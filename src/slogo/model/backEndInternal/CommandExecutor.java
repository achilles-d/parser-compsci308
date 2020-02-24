package slogo.model.backEndInternal;

import slogo.model.backEndInternal.commands.Command;
import slogo.model.ExecutionException;
import slogo.model.Executor;

import java.lang.reflect.Method;

public class CommandExecutor implements Executor {

    public CommandExecutor(){

    }
    @Override
    /**
     *
     */
    public Object executeCommand(Command currentCommand) throws ExecutionException {
       //System.out.println("So far good"+currentCommand.execute().toString());
//        //currentCommand.getClass().getMethods()[0].getReturnType();
//        try {
//            Method testMethod = currentCommand.getClass().getMethod("execute");
//           if(testMethod.getReturnType().equals(Integer.TYPE)){
//               return (Integer)currentCommand.execute();
//            }
//
//            //testMethod.getReturnType()
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        return currentCommand.execute();
    }


}
