package dbg.construction.geometry;

/**
 * @author bogdel on 18.11.15.
 */
public class PlaneSide {

    private final Plane plane;
    private final boolean normalDirection;

    public PlaneSide(Plane plane, boolean normalDirection) {
        this.plane = plane;
        this.normalDirection = normalDirection;
    }

    public Plane getPlane() {
        return plane;
    }

    public boolean isNormalDirection() {
        return normalDirection;
    }

    @Override
    public String toString() {
        return "PlaneSide{" +
                "plane=" + plane +
                ", normalDirection=" + normalDirection +
                '}';
    }
}
