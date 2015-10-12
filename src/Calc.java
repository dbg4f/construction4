import java.util.ArrayList;
import java.util.List;

/**
 * @author bogdel on 12.10.15.
 */
public class Calc {


    public final static GeoPoint[] POINTS = new GeoPoint[] {
            new GeoPoint(59, 21, 44.18, 24, 34, 26.94),
            new GeoPoint(59, 21, 44.43, 24, 34, 28.41),

            new GeoPoint(59, 21, 44.31, 24, 34, 28.48),
            new GeoPoint(59, 21, 43.60, 24, 34, 28.94),
            new GeoPoint(59, 21, 42.87, 24, 34, 29.41),

            new GeoPoint(59, 21, 42.34, 24, 34, 29.75),
            new GeoPoint(59, 21, 42.13, 24, 34, 28.52),
            new GeoPoint(59, 21, 42.31, 24, 34, 28.40),
            new GeoPoint(59, 21, 42.25, 24, 34, 28.00),
            new GeoPoint(59, 21, 43.65, 24, 34, 27.08),
            new GeoPoint(59, 21, 43.75, 24, 34, 27.21),

    };


    public static void main(String[] args) {

        checkLength();

        System.out.println("args = " + POINTS[1].metersTo(POINTS[2]));

        transform();

    }


    public static List<CartesianPoint> transform() {


        GeoPoint start = POINTS[8];

        List<CartesianPoint> cartesianPoints = new ArrayList<>();

        for (GeoPoint point : POINTS) {
            CartesianPoint point1 = point.toCartesianPoint(start).scale(10).shift(800);
            cartesianPoints.add(point1);
            System.out.println(point1.toDrawCommand());
        }


        return cartesianPoints;
    }


    private static void checkLength() {
        System.out.println("Math.cos(1.03) = " + Math.cos(1.03) * 111111);

        double x = 2.09 * 30.864166667;

        System.out.println("x = " + x);


        double y = 1.34 * 15.889444444;

        System.out.println("y = " + y);


        System.out.println("args = " + Math.sqrt(x*x + y*y));
    }


}
