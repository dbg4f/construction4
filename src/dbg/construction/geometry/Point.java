package dbg.construction.geometry;

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

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
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
