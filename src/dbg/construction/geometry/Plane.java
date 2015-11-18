package dbg.construction.geometry;

/**
 * @author bogdel on 18.11.15.
 */
public class Plane {

    private final CommonPlane commonPlane;
    private final long coordinate;

    public Plane(CommonPlane plane, long coordinate) {
        this.commonPlane = plane;
        this.coordinate = coordinate;
    }

    public CommonPlane getCommonPlane() {
        return commonPlane;
    }

    public long getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "commonPlane=" + commonPlane +
                ", coordinate=" + coordinate +
                '}';
    }
}
