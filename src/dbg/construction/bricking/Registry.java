package dbg.construction.bricking;

import dbg.construction.geometry.CommonPlane;
import dbg.construction.utils.UniquePair;

/**
 * @author bogdel on 18.11.15.
 */
public class Registry {

    public static final ParallelepipedBrickGeometry AEROC_375 = new ParallelepipedBrickGeometry(200, 600, 375);

    public static final BrickOrientation ORIENTATION_ALONG_LENGTH = new BrickOrientation(
            new UniquePair<>(BrickSurface.FACE, BrickSurface.BED),
            new UniquePair<>(CommonPlane.XOZ, CommonPlane.XOY)
    );

    public static final BrickOrientation ORIENTATION_ALONG_WIDTH = new BrickOrientation(
            new UniquePair<>(BrickSurface.FACE, BrickSurface.BED),
            new UniquePair<>(CommonPlane.YOZ, CommonPlane.XOY)
    );




}
