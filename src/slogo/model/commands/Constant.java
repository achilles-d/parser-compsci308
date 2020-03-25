package slogo.model.commands;

public class Constant implements  Command<Double> {

    private Double input;

    /**
     * Command Constructor
     * @param input String value of double input
     */
    public Constant(String input){
        this.input= Double.parseDouble(input);
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
        return input;
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return true;
    }
}
