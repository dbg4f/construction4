public class Angle {

    private final double rad;
    private final double deg;

    private Angle(double rad, double deg) {
        this.rad = rad;
        this.deg = deg;
    }

    public static Angle degrees(double degrees) {
        return new Angle(toRad(degrees), degrees);
    }

    public static Angle radians(double radians) {
        return new Angle(radians, toDegrees(radians));
    }

    public static double toRad(double degrees) {
        return Math.PI * degrees / 180.0;
    }

    public static double toDegrees(double radians) {
        return 180.0 * radians / Math.PI;
    }

    public double degrees() {
        return deg;
    }

    public double radians() {
        return rad;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "rad=" + rad +
                ", deg=" + deg +
                '}';
    }
}
