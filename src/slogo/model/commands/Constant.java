package slogo.model.commands;

public class Constant implements  Command<Double> {

    private Double input;

    public Constant(String input){
        this.input= Double.parseDouble(input);
    }

    @Override
    public Double execute() {
        return input;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
