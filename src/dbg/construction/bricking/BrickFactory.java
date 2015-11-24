package dbg.construction.bricking;

import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.Point;
import dbg.construction.utils.UniquePair;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author bogdel on 18.11.15.
 */
public class BrickFactory {

    private final String brickType;

    private final ParallelepipedBrickGeometry geometry;

    private static AtomicLong serialNumberSource = new AtomicLong();

    private final Point DEFAULT_CENTER = new Point(0, 0, 0);

    private BrickOrientation orientation = Registry.ORIENTATION_ALONG_LENGTH;

    public BrickFactory(String brickType, ParallelepipedBrickGeometry geometry) {
        this.brickType = brickType;
        this.geometry = geometry;
    }

    public void setOrientation(BrickOrientation orientation) {
        this.orientation = orientation;
    }

    public Brick newBrick(Point center) {
        return new Brick(brickType + " " + serialNumberSource.incrementAndGet(), geometry, orientation, center);
    }

    public Brick newBrick() {
        return newBrick(DEFAULT_CENTER);
    }

    public long producedCount() {
        return serialNumberSource.get();
    }

}
