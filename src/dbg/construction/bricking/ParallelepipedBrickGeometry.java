package dbg.construction.bricking;

/**
 * @author bogdel on 18.11.15.
 */
public class ParallelepipedBrickGeometry {

    private final long distanceBeds;
    private final long distanceHeader;
    private final long distanceFaces;

    public ParallelepipedBrickGeometry(long distanceBeds, long distanceHeader, long distanceFaces) {
        this.distanceBeds = distanceBeds;
        this.distanceHeader = distanceHeader;
        this.distanceFaces = distanceFaces;
    }

    public long getDistanceBeds() {
        return distanceBeds;
    }

    public long getDistanceHeader() {
        return distanceHeader;
    }

    public long getDistanceFaces() {
        return distanceFaces;
    }

    public long getSurfacesDistance(BrickSurface surfaces) {

        if (surfaces == BrickSurface.FACE) {
            return getDistanceFaces();
        }
        else if (surfaces == BrickSurface.HEADER) {
            return getDistanceHeader();
        }
        else if (surfaces == BrickSurface.BED) {
            return getDistanceBeds();
        }
        else {
            throw new IllegalArgumentException("Unknown surface: " + surfaces);
        }

    }


    @Override
    public String toString() {
        return "ParallelepipedBrickGeometry{" +
                "distanceBeds=" + distanceBeds +
                ", distanceHeader=" + distanceHeader +
                ", distanceFaces=" + distanceFaces +
                '}';
    }
}
