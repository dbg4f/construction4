/**
 * @author bogdel on 12.10.15.
 */
public class CartesianPoint {

    public final double x;
    public final double y;


    public CartesianPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CartesianPoint shift(double delta) {
        return new CartesianPoint(x + delta, y + delta);
    }

    public CartesianPoint scale(double factor) {
        return new CartesianPoint(x * factor, y * factor);
    }

    public String toDrawCommand() {
        return String.format("ctx.lineTo(%d,%d);ctx.storoke();\n", (int)x, (int)y);
    }

    @Override
    public String toString() {
        return "CartesianPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
