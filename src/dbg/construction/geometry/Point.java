package dbg.construction.geometry;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dbg.construction.utils.Triplet;

/**
 * @author bogdel on 18.11.15.
 */
public class Point {

    private final long x;
    private final long y;
    private final long z;

    public Point(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(Triplet<Long> triplet) {
        this(triplet.getFirst(), triplet.getSecond(), triplet.getThird());
    }

    public Triplet<Long> triplet() {
        return new Triplet<Long>(x, y, z);
    }

    public Triplet<Long> toDistances(Triplet<Boolean> directions) {
        return new Triplet<>(
                directions.getFirst() ? x : -x,
                directions.getSecond() ? y : -y,
                directions.getThird() ? z : -z);
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public Point moveTo(Dimensions dimensions, Triplet<Boolean> directions) {

        Triplet<Long> distances = dimensions.getNormalizedDimensions().toDistances(directions);

        return new Point(
                x + distances.getFirst(),
                y + distances.getSecond(),
                z + distances.getThird()
        );
    }

    public long get(Axis axle) {
        if (axle == Axis.X) {
            return getX();
        }
        else if (axle == Axis.Y) {
            return getY();
        }
        else if (axle == Axis.Z) {
            return getZ();
        }
        else {
            throw new IllegalArgumentException("Unknown axle " + axle);
        }

    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
