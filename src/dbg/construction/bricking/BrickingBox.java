package dbg.construction.bricking;

import dbg.construction.geometry.*;
import dbg.construction.utils.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author bogdel on 25.11.15.
 */
public class BrickingBox {

    private final Point startingPoint;

    private final Map<Axis, Integer> dimensions;

    private final BrickFactory factory;

    private ArrayList<BrickingBelt> belts = new ArrayList<>();

    public BrickingBox(Point startingPoint, Map<Axis, Integer> dimensions, BrickFactory factory) {
        this.startingPoint = startingPoint;
        this.dimensions = dimensions;
        this.factory = factory;
    }



    public void build() {

        Triplet<PlaneSide> startingPlanes = new Triplet<>(
                getPlaneSide(Axis.X),
                getPlaneSide(Axis.Y),
                getPlaneSide(Axis.Z)
        );

        for (int i=0; i<dimensions.get(Axis.Z); i++) {
            BrickingBelt brickingBelt = new BrickingBelt(factory, dimensions.get(Axis.X), dimensions.get(Axis.Y), selectOrientation(i), startingPlanes);
            brickingBelt.build();
            belts.add(brickingBelt);
        }

    }

    private PlaneSide getPlaneSide(Axis axis) {
        return new PlaneSide(new Plane(axis.normalFor(), startingPoint.get(axis)), true);
    }

    private BrickOrientation selectOrientation(int i) {
        return i % 2 == 0 ? Registry.ORIENTATION_ALONG_LENGTH : Registry.ORIENTATION_ALONG_WIDTH;
    }


    public List<Brick> getAllBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (BrickingBelt belt : belts) {
            bricks.addAll(belt.getAll());
        }

        return bricks;
    }

    @Override
    public String toString() {
        return "BrickingBox{" +
                "startingPoint=" + startingPoint +
                ", dimensions=" + dimensions +
                ", factory=" + factory +
                ", belts=" + belts +
                '}';
    }
}
