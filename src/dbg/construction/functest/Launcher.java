package dbg.construction.functest;

import dbg.construction.bricking.*;
import dbg.construction.bricking.draw.ProjectionHelper;
import dbg.construction.bricking.draw.SvgRect;
import dbg.construction.geometry.*;
import dbg.construction.utils.Pair;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author bogdel on 20.11.15.
 */
public class Launcher {

    public static void main(String[] args) {


        BrickFactory factory = new BrickFactory("A375", Registry.AEROC_375);

        PlaneSide leftSide      = new PlaneSide(new Plane(CommonPlane.YOZ, 1000), true);
        PlaneSide lowerPlate    = new PlaneSide(new Plane(CommonPlane.XOY, 1000), true);
        PlaneSide nearFace      = new PlaneSide(new Plane(CommonPlane.XOZ, 1000), true);

        Point startingPoint = new Point(1000, 1000, 1000);

        Map<Axis, Integer> dimensions = new LinkedHashMap<>();

        dimensions.put(Axis.X, 9);
        dimensions.put(Axis.Y, 8);
        dimensions.put(Axis.Z, 1);

        BrickingBox brickingBox = new BrickingBox(startingPoint, dimensions, factory);

        brickingBox.build();

        brickingBox.getAllBricks().forEach(brick1 -> System.out.println(ProjectionHelper.projection(brick1, CommonPlane.XOY, 0.1)));

/*


        Pair<PlaneSide> rowSidesLowerAndFace = new Pair<>(lowerPlate,nearFace);

        GlueHelper glueHelperFaceRowNear = new GlueHelper(rowSidesLowerAndFace);

        factory.setOrientation(Registry.ORIENTATION_ALONG_LENGTH);

        Brick[] faceRowNear = glueHelperFaceRowNear.glueRow(leftSide, factory, 9);

        Arrays.stream(faceRowNear).forEach(brick -> System.out.println(ProjectionHelper.projection(brick, CommonPlane.XOY, 0.1)));

        Brick firstBrick = faceRowNear[0];

        factory.setOrientation(Registry.ORIENTATION_ALONG_WIDTH);

        PlaneSide firstBrickSide = GlueHelper.findBrickPlaneSide(firstBrick, nearFace.getPlane().getCommonPlane(), true);

        Pair<PlaneSide> rowSidesLowerAndLeft = new Pair<>(lowerPlate,leftSide);

        GlueHelper glueHelperSideLeft = new GlueHelper(rowSidesLowerAndLeft);


        Brick[] leftRow = glueHelperSideLeft.glueRow(firstBrickSide, factory, 8);

        Arrays.stream(leftRow).forEach(brick -> System.out.println(ProjectionHelper.projection(brick, CommonPlane.XOY, 0.1)));


        System.out.println("produced = " + factory.producedCount());
*/

    }


}
