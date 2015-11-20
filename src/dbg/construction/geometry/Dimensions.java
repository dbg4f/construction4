package dbg.construction.geometry;

/**
 * @author bogdel on 18.11.15.
 */
public class Dimensions {

    private Point normalizedDimensions;

    public Dimensions(long x, long y, long z) {
        normalizedDimensions = new Point(x, y, z);
    }

    public long get(Axis axis) {
        return normalizedDimensions.get(axis);
    };

    public Dimensions half() {
        return new Dimensions(normalizedDimensions.getX()/2, normalizedDimensions.getY()/2, normalizedDimensions.getZ()/2);
    }

    public Point getNormalizedDimensions() {
        return normalizedDimensions;
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "normalizedDimensions=" + normalizedDimensions +
                '}';
    }
}
