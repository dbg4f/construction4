package dbg.construction.geometry;

/**
 * @author bogdel on 18.11.15.
 */
public class Dimensions {

    private Point normalizedDimensions;

    public Dimensions(long x, long y, long z) {
        normalizedDimensions = new Point(x, y, z);
    }

    public long get(Axle axle) {
        return normalizedDimensions.get(axle);
    };

    @Override
    public String toString() {
        return "Dimensions{" +
                "normalizedDimensions=" + normalizedDimensions +
                '}';
    }
}
