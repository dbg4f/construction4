package dbg.construction.bricking;

import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.Point;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author bogdel on 18.11.15.
 */
public class BrickFactory {

    private final String brickType;

    private final ParallelepipedBrickGeometry geometry;

    private static AtomicLong serialNumberSource = new AtomicLong();

    private final Point DEFAULT_CENTER = new Point(0, 0, 0);
    private final CommonPlane DEFAULT_FACE = CommonPlane.XOZ;

    public BrickFactory(String brickType, ParallelepipedBrickGeometry geometry) {
        this.brickType = brickType;
        this.geometry = geometry;
    }

    public Brick newBrick(CommonPlane faceOrientation, Point center) {
        return new Brick(brickType + " " + serialNumberSource.incrementAndGet(), geometry, faceOrientation, center);
    }

    public Brick newBrick() {
        return newBrick(DEFAULT_FACE, DEFAULT_CENTER);
    }

    public long producedCount() {
        return serialNumberSource.get();
    }

}
