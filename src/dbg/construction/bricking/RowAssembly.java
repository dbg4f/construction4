package dbg.construction.bricking;

import dbg.construction.geometry.Axis;
import dbg.construction.geometry.PlaneSide;
import dbg.construction.geometry.Point;
import dbg.construction.utils.Pair;

/**
 * @author bogdel on 18.11.15.
 */
public class RowAssembly {

    private final Pair<PlaneSide> rowPlanes;
    private final PlaneSide initialPlane;

    public RowAssembly(Point start, Axis growDirection) {

        rowPlanes = null;
        initialPlane = null;
    }
}
