package dbg.construction.bricking;

import dbg.construction.geometry.*;
import dbg.construction.utils.Pair;
import dbg.construction.utils.Triplet;

/**
 * @author bogdel on 20.11.15.
 */
public class GlueHelper {

    private final Pair<PlaneSide> rowPlaneSides;

    public GlueHelper(Pair<PlaneSide> rowPlaneSides) {
        this.rowPlaneSides = rowPlaneSides;
    }

    public Brick glue(Brick brick, PlaneSide glueTo) {
        return glue(brick, new Triplet<>(rowPlaneSides, glueTo));
    }

    public static BrickOrientation toggleOrientation(BrickOrientation orientation) {
        return orientation == Registry.ORIENTATION_ALONG_LENGTH ? Registry.ORIENTATION_ALONG_WIDTH : Registry.ORIENTATION_ALONG_LENGTH;
    }

    public Brick[] glueRow(PlaneSide startPlane, BrickFactory factory, int count) {

        Brick[] row = new Brick[count];

        PlaneSide currentPlane = startPlane;

        for (int i=0; i<count; i++) {

            Brick newBrick = glue(factory.newBrick(), currentPlane);

            row[i] = newBrick;

            currentPlane = findBrickPlaneSide(newBrick, startPlane.getPlane().getCommonPlane(), startPlane.isNormalDirection());

        }

        return row;

    }

    private Brick glue(Brick brick, Triplet<PlaneSide> glueTo) {

        Point refPoint = Intersections.intersect(glueTo);

        Triplet<Boolean> directions = Intersections.orderedAxisNormalDirections(glueTo);

        Dimensions half = brick.getDimensions().half();

        return moveBrick(brick, refPoint, directions, half);

    }

    private Brick moveBrick(Brick brick, Point refPoint, Triplet<Boolean> directions, Dimensions half) {
        return new Brick(brick.getId(), brick.getGeometry(), brick.getOrientation(), refPoint.moveTo(half, directions));
    }

    public static PlaneSide findBrickPlaneSide(Brick brick, CommonPlane commonPlane, boolean sideRespectingNormal) {

        Point center = brick.getCenter();
        Axis commonPlaneNormal = commonPlane.getNormal();
        long dimensionAlongAxis = brick.getDimensions().get(commonPlaneNormal);

        long centerSurplus = dimensionAlongAxis / 2;

        long coordinate = center.get(commonPlaneNormal) + (sideRespectingNormal ? centerSurplus : -centerSurplus);

        return new PlaneSide(new Plane(commonPlane, coordinate), sideRespectingNormal);

    }



}
