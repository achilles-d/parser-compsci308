package slogo.model.backEndInternal;

import slogo.model.Coordinate;
import slogo.model.Line;

import java.util.ArrayList;
import java.util.List;

public class LineAPI implements Line {

    private Coordinate start;
    private Coordinate end;
    private boolean drawn;

    public LineAPI(Coordinate start, Coordinate end){
        this.start=start;
        this.end=end;
        drawn = false;
    }

    @Override
    public Coordinate getStart() {
        return start;
    }

    @Override
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

    @Override
    public void createLine(Coordinate start, Coordinate end) {
        this.start=start;
        this.end=end;
    }
}
