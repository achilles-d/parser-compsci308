package slogo.model.backEndInternal;

import slogo.model.Coordinate;
import slogo.model.Line;

import java.util.ArrayList;
import java.util.List;

public class LineAPI implements Line {

    private Coordinate start;
    private Coordinate end;

    public LineAPI(){
        start=new Coordinate();
        end=new Coordinate();
    }

    @Override
    public Coordinate getStart() {
        return start;
    }

    @Override
    public Coordinate getEnd() {
        return end;
    }

    @Override
    public void createLine(Coordinate start, Coordinate end) {
        this.start=start;
        this.end=end;
    }
}
