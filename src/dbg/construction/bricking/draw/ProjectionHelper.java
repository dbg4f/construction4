package dbg.construction.bricking.draw;

import dbg.construction.bricking.Brick;
import dbg.construction.geometry.CommonPlane;

/**
 * @author bogdel on 23.11.15.
 */
public class ProjectionHelper {


    public static String projection(Brick brick, CommonPlane commonPlane, double scale) {
        return new SvgRect(brick.getCenter(), brick.getDimensions(), commonPlane).toSvg(scale);
    }


}
