package dbg.construction.bricking;

import dbg.construction.geometry.Dimensions;
import dbg.construction.geometry.Intersections;
import dbg.construction.geometry.PlaneSide;
import dbg.construction.geometry.Point;
import dbg.construction.utils.Triplet;

/**
 * @author bogdel on 20.11.15.
 */
public class GlueHelper {

    public Brick glue(Brick brick, Triplet<PlaneSide> glueTo) {

        Point refPoint = Intersections.intersect(glueTo);

        Triplet<Boolean> directions = Intersections.orderedAxisNormalDirections(glueTo);

        Dimensions half = brick.getDimensions().half();

        return new Brick(brick.getId(), brick.getGeometry(), brick.getOrientation(), refPoint.moveTo(half, directions));



    }



}
