package dbg.construction.bricking.draw;

import dbg.construction.geometry.Axis;
import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.Dimensions;
import dbg.construction.geometry.Point;
import dbg.construction.utils.Pair;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author bogdel on 23.11.15.
 */
public class SvgRect {

    private final long x;
    private final long y;
    private final long width;
    private final long height;

    private SvgRect(long x, long y, long width, long height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public SvgRect(Point centerPoint, Dimensions dimensions, CommonPlane cuttingPlane) {

        Pair<Axis> axises = cuttingPlane.getAxles();

        x = centerPoint.get(axises.getFirst()) - dimensions.half().get(axises.getFirst());
        y = centerPoint.get(axises.getSecond()) - dimensions.half().get(axises.getSecond());

        width = dimensions.get(axises.getFirst());
        height = dimensions.get(axises.getSecond());

    }

    public long[] asArray() {
        return new long[] {x, y, width, height};
    }

    public long[] scaledArray(double scale) {

        long[] array = asArray();

        return Arrays.stream(array).map(i -> (long)(i * scale)).toArray();
    }

    public String toSvg(double scale) {


        long[] longs = scaledArray(scale);
        return String.format("<rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" style=\"fill:blue;stroke:black;stroke-width:1;fill-opacity:0.1;stroke-opacity:0.9\" />", longs[0], longs[1], longs[2], longs[3]);
    }

    private Object[] getObjects(double scale) {
        return new Object[]{Math.round(x * scale), Math.round(y * scale), Math.round(width * scale), Math.round(height * scale)};
    }

    @Override
    public String toString() {
        return "SvgRect{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
