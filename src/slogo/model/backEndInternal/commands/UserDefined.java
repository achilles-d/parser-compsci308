package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserDefined implements Command {


   private Command command;
   private Command inputs;
   private Map<String, List<Command>> commandSaver;
   private boolean executable;
   private List<Command> values;
   private int state;
   private List<String> listOfCommands;



//    public UserDefined(List<Command> values, Command inputs,Command command ){
//       this.values=values;
//       this.inputs=inputs;
//       this.command=command;
//       state=1;
//        buildExecutable();
//       executable=listOfCommands.size()==0;
//    }

    public UserDefined(List<Object> param){
       System.out.println(" UserDefined constructor reached 2 " +param.size());
        //List<Command> values, Command inputs,Command command
        this.values=(List<slogo.model.backEndInternal.commands.Command>) param.get(0);
       this.inputs= (slogo.model.backEndInternal.commands.Command) param.get(1);
       this.command= (slogo.model.backEndInternal.commands.Command) param.get(2);
       state=1;

        System.out.println(" values " +values.size());
        System.out.println(" inputs " +inputs.execute().toString());
        System.out.println(" listOfCommands " +command.execute().toString());

        buildExecutable();
       executable=listOfCommands.size()==0;
    }





    @Override
    public Object execute() {
       if(executable){
           return 0.0;
       }
      return listOfCommands;
    }


    private void buildExecutable(){

       List<String> inputsList= (List<String>) inputs.execute();


       listOfCommands= (List<String>) command.execute();

       for(int i=0; i<inputsList.size(); i++){

           Collections.replaceAll(listOfCommands, inputsList.get(i),(String)values.get(i).execute());

       }
       listOfCommands.remove(0);// remove bracket
        listOfCommands.remove(listOfCommands.size()-1);

    }

    @Override
    public boolean isItExecutable() {
        return executable;
    }
}
