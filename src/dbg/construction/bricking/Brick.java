package dbg.construction.bricking;

import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.Point;

/**
 * @author bogdel on 18.11.15.
 */
public class Brick {

    private final String id;
    private final ParallelepipedBrickGeometry geometry;
    private final CommonPlane faceOrientation;
    private final Point center;

    public Brick(String id, ParallelepipedBrickGeometry geometry, CommonPlane faceOrientation, Point center) {
        this.id = id;
        this.geometry = geometry;
        this.faceOrientation = faceOrientation;
        this.center = center;
    }

    public String getId() {
        return id;
    }

    public Point getCenter() {
        return center;
    }
}
