package slogo.model.turtle;

public class Line{

    private Coordinate start;
    private Coordinate end;
    private boolean drawn;

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
