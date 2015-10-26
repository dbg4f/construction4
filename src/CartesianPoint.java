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
        return shift(delta, delta);
    }

    public CartesianPoint shift(double deltaX, double deltaY) {
        return new CartesianPoint(x + deltaX, y + deltaY);
    }

    public CartesianPoint scaleX(double factorX) {
        return new CartesianPoint(x * factorX, y);
    }

    public CartesianPoint scaleY(double factorY) {
        return new CartesianPoint(x, y * factorY);
    }

    public CartesianPoint scale(double factor) {
        return new CartesianPoint(x * factor, y * factor);
    }

    public CartesianPoint rotate(Angle angle) {
        double xx = x*Math.cos(angle.radians()) - y*Math.sin(angle.radians());
        double yy = x*Math.sin(angle.radians()) + y*Math.cos(angle.radians());
        return new CartesianPoint(xx, yy);
    }

    public String toDrawCommand() {
        return String.format("ctx.lineTo(%d,%d);ctx.stroke();\n", (int)x, (int)y);
    }

    @Override
    public String toString() {
        return "CartesianPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
