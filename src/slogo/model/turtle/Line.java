package slogo.model.turtle;

public class Line{

    private Coordinate start;
    private Coordinate end;
    private boolean drawn;

    /**
     *
     * @param start the start of the line
     * @param end the end of the line
     * The purpose of this class is to define  a line with methods that control it
     */
    public Line(Coordinate start, Coordinate end){
        this.start=start;
        this.end=end;
        drawn = false;
    }


    public Coordinate getStart() {
        return start;
    }


    public Coordinate getEnd() {
        return end;
    }

    public boolean isDrawn(){
        return drawn;
    }

    public void drewLine()
    {
        drawn = true;
    }


    public void createLine(Coordinate start, Coordinate end) {
        this.start=start;
        this.end=end;
    }
}
