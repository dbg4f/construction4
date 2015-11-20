package dbg.construction.bricking;

import dbg.construction.geometry.Dimensions;
import dbg.construction.geometry.Point;



/**
 * @author bogdel on 18.11.15.
 */
public class Brick {

    private final String id;
    private final ParallelepipedBrickGeometry geometry;
    private final BrickOrientation orientation;
    private final Point center;

    public Brick(String id, ParallelepipedBrickGeometry geometry, BrickOrientation orientation, Point center) {
        this.id = id;
        this.geometry = geometry;
        this.orientation = orientation;
        this.center = center;
    }


    public Dimensions getDimensions() {
        return orientation.getDimensions(geometry);
    }


    public String getId() {
        return id;
    }

    public Point getCenter() {
        return center;
    }


    public ParallelepipedBrickGeometry getGeometry() {
        return geometry;
    }

    public BrickOrientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Brick{" +
                "id='" + id + '\'' +
                ", geometry=" + geometry +
                ", orientation=" + orientation +
                ", center=" + center +
                '}';
    }
}
