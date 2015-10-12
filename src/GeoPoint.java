/**
 * @author bogdel on 12.10.15.
 */
public class GeoPoint {

    // degrees
    public final double lat;
    public final double lon;

    public static final long LAT_METERS_DEGREE = 111111;

    public GeoPoint(int latDeg, int latMin, double latSec, int lonDeg, int lonMin, double lonSec) {
        lat = latDeg + latMin/60.0 + latSec/(60.0*60.0);
        lon = lonDeg + lonMin/60.0 + lonSec/(60.0*60.0);
    }

    public static double latMeters(double lat1, double lat2) {
        return (lat2 - lat1) * LAT_METERS_DEGREE;
    }

    public static double lonMeters(double lon1, double lon2, double pointLat) {
        double a = toRad(pointLat);
        return (lon2 - lon1) * LAT_METERS_DEGREE * Math.cos(a);
    }

    public CartesianPoint toCartesianPoint(GeoPoint reference) {
        return new CartesianPoint(latMeters(lat, reference.lat), lonMeters(lon, reference.lon, lat));
    }

    public double metersTo(GeoPoint point2) {

        double dLatM = latMeters(point2.lat, lat);
        double dLonM = lonMeters(point2.lon, lon, lat);

        System.out.println("dLatM = " + dLatM);

        System.out.println("dLonM = " + dLonM);

        return Math.sqrt(dLatM * dLatM + dLonM * dLonM);

    }

    public static double toRad(double degrees) {
        return Math.PI * degrees / 180.0;
    }



}
