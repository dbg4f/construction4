import java.io.FileWriter;
import java.io.IOException;
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


    public static void main(String[] args) throws IOException {

        checkLength();

        transform();

    }


    public static final String HTML_TEMPLATE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "\n" +
            "<canvas id=\"myCanvas\" width=\"1000\" height=\"1000\" style=\"border:1px solid #d3d3d3;\">\n" +
            "    Your browser does not support the HTML5 canvas tag.</canvas>\n" +
            "\n" +
            "<script>\n" +
            "    var c = document.getElementById(\"myCanvas\");\n" +
            "    var ctx = c.getContext(\"2d\");\n" +
            "\n" +
            "%s" +
            "</script>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";

    public static List<CartesianPoint> transform() throws IOException {


        StringBuffer buf = new StringBuffer();

        GeoPoint start = POINTS[5];

        List<CartesianPoint> cartesianPoints = new ArrayList<>();

        for (GeoPoint point : POINTS) {
            CartesianPoint point1 = pointTransform(start, point);
            cartesianPoints.add(point1);
            buf.append(point1.toDrawCommand());
        }

        buf.append(pointTransform(start, POINTS[0]).toDrawCommand());

        saveToFile(buf);



        return cartesianPoints;
    }

    private static void saveToFile(StringBuffer buf) throws IOException {
        FileWriter writer = new FileWriter("draw.html");
        writer.write(String.format(HTML_TEMPLATE, buf.toString()));
        writer.close();
    }

    private static CartesianPoint pointTransform(GeoPoint start, GeoPoint point) {
        return point.toCartesianPoint(start)
                .scale(8)
                .shift(-200, 800)
                .rotate(Angle.degrees(-71.5))
                .shift(-300, -300)
                .rotate(Angle.degrees(90))
                .shift(700, -300)
                .scale(1.5)
                ;
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
