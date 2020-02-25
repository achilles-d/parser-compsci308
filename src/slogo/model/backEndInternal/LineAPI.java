package slogo.model.backEndInternal;

import slogo.model.Coordinate;
import slogo.model.Line;

import java.util.ArrayList;
import java.util.List;

public class LineAPI implements Line {
    private List<List<Coordinate>> points;

    public LineAPI(){
        points=new ArrayList<>();
    }
    @Override
    public List<List<Coordinate>> getLineEndpoints() {

        return points;
    }

    @Override
    public void createLine(Coordinate start, Coordinate end) {
        List<Coordinate> line=new ArrayList<>();
        line.add(start);
        line.add(end);
        points.add(line);
    }
}
