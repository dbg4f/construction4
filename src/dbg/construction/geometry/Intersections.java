package dbg.construction.geometry;

import dbg.construction.utils.CollectionUtils;
import dbg.construction.utils.Triplet;

/**
 * @author bogdel on 20.11.15.
 */
public class Intersections {

    public static Point intersect(Triplet<PlaneSide> planeSideTriplet) {

        CollectionUtils.assertAllDifferent(planeSideTriplet.asList());

        Triplet<Long> intersectionPoint = orderedCoord(new Triplet<Plane>(
                planeSideTriplet.getFirst().getPlane(),
                planeSideTriplet.getSecond().getPlane(),
                planeSideTriplet.getThird().getPlane()
        ));

        return new Point(intersectionPoint);

    }

    public static Triplet<Long> orderedCoord(Triplet<Plane> planeTriplet) {

        Long x = null;
        Long y = null;
        Long z = null;

        for (Plane plane : planeTriplet.asList()) {
            if (plane.getCommonPlane().getNormal() == Axis.X) {
                x = plane.getCoordinate();
            }
            else if (plane.getCommonPlane().getNormal() == Axis.Y) {
                y = plane.getCoordinate();
            }
            else if (plane.getCommonPlane().getNormal() == Axis.Z) {
                z = plane.getCoordinate();
            }

        }

        Triplet<Long> longTriplet = new Triplet<Long>(x, y, z);
        longTriplet.assertNotEmpty();
        return longTriplet;

    }

    public static Triplet<Boolean> orderedAxisNormalDirections(Triplet<PlaneSide> planeTriplet) {

        Boolean x = null;
        Boolean y = null;
        Boolean z = null;

        for (PlaneSide planeSide : planeTriplet.asList()) {
            if (planeSide.getPlane().getCommonPlane().getNormal() == Axis.X) {
                x = planeSide.isNormalDirection();
            }
            else if (planeSide.getPlane().getCommonPlane().getNormal() == Axis.Y) {
                y = planeSide.isNormalDirection();
            }
            else if (planeSide.getPlane().getCommonPlane().getNormal() == Axis.Z) {
                z = planeSide.isNormalDirection();
            }

        }

        Triplet<Boolean> normalsTriplet = new Triplet<Boolean>(x, y, z);
        normalsTriplet.assertNotEmpty();
        return normalsTriplet;

    }


}
