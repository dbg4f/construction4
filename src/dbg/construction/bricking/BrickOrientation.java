package dbg.construction.bricking;

import dbg.construction.geometry.Axis;
import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.Dimensions;
import dbg.construction.utils.UniquePair;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bogdel on 20.11.15.
 */
public class BrickOrientation {

    private final UniquePair<BrickSurface> surfaces;
    private final UniquePair<CommonPlane> planes;

    public BrickOrientation(BrickSurface firstSurface, CommonPlane firstPlane, BrickSurface secondSurface, CommonPlane secondPlane) {
        surfaces = new UniquePair<>(firstSurface, secondSurface);
        planes = new UniquePair<>(firstPlane, secondPlane);
    }


    public BrickOrientation(UniquePair<BrickSurface> surfaces, UniquePair<CommonPlane> planes) {
        this.surfaces = surfaces;
        this.planes = planes;
    }

    public Dimensions getDimensions(ParallelepipedBrickGeometry geometry) {

        EnumSet<BrickSurface> remainingSurface = EnumSet.complementOf(EnumSet.of(surfaces.getFirst(), surfaces.getSecond()));
        EnumSet<CommonPlane> remainingPlane = EnumSet.complementOf(EnumSet.of(planes.getFirst(), planes.getSecond()));

        List<BrickSurface> brickSurfaces = surfaces.asList();
        brickSurfaces.add(remainingSurface.iterator().next());

        List<CommonPlane> brickPlanes = planes.asList();
        brickPlanes.add(remainingPlane.iterator().next());

        Map<Axis, Long> dimensions = new LinkedHashMap<>();

        for (int i=0; i<brickSurfaces.size(); i++) {

            BrickSurface surface = brickSurfaces.get(i);
            CommonPlane plane = brickPlanes.get(i);

            dimensions.put(plane.getNormal(), geometry.getSurfacesDistance(surface));

        }

        return new Dimensions(dimensions.get(Axis.X), dimensions.get(Axis.Y), dimensions.get(Axis.Z));

    }


    public UniquePair<BrickSurface> getSurfaces() {
        return surfaces;
    }

    public UniquePair<CommonPlane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "BrickOrientation{" +
                "surfaces=" + surfaces +
                ", planes=" + planes +
                '}';
    }
}
