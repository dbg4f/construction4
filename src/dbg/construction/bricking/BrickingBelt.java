package dbg.construction.bricking;

import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.PlaneSide;
import dbg.construction.utils.Pair;
import dbg.construction.utils.Triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bogdel on 25.11.15.
 */
public class BrickingBelt {

    private final BrickFactory factory;

    private final int countX;
    private final int countY;
    private final BrickOrientation firstBrickOrientation;
    private final Triplet<PlaneSide> startingPlanes;
    private final List<Brick> bricks = new ArrayList<>();


    public BrickingBelt(BrickFactory factory, int countX, int countY, BrickOrientation firstBrickOrientation, Triplet<PlaneSide> startingPlanes) {
        this.factory = factory;
        this.countX = countX;
        this.countY = countY;
        this.firstBrickOrientation = firstBrickOrientation;
        this.startingPlanes = startingPlanes;
    }

    public List<Brick> getAll() {
        return new ArrayList<>(bricks);
    }

    public void build() {

        PlaneSide lowerPlate = getPlaneSide(CommonPlane.XOY);
        PlaneSide leftSide   = getPlaneSide(CommonPlane.YOZ);
        PlaneSide nearFace = getPlaneSide(CommonPlane.XOZ);

        Pair<PlaneSide> rowSidesLowerAndFace = new Pair<>(lowerPlate,nearFace);

        GlueHelper glueHelperFaceRowNear = new GlueHelper(rowSidesLowerAndFace);

        factory.setOrientation(firstBrickOrientation);


        if (firstBrickOrientation == Registry.ORIENTATION_ALONG_LENGTH) {

            Brick[] faceRowNear = glueHelperFaceRowNear.glueRow(leftSide, factory, countX);

            bricks.addAll(Arrays.asList(faceRowNear));


            Brick lastBrick = faceRowNear[faceRowNear.length - 1];
            PlaneSide lastFaceBrickSide = GlueHelper.findBrickPlaneSide(lastBrick, leftSide.getPlane().getCommonPlane(), true);

            factory.setOrientation(GlueHelper.toggleOrientation(firstBrickOrientation));
            Pair<PlaneSide> rowSidesLowerAndRight = new Pair<>(lowerPlate,lastFaceBrickSide);
            GlueHelper glueHelperSideRight = new GlueHelper(rowSidesLowerAndRight);

            Brick[] rightSideRow = glueHelperSideRight.glueRow(nearFace, factory, countY);

            bricks.addAll(Arrays.asList(rightSideRow));

            Brick firstBrickFaceNear = faceRowNear[0];
            PlaneSide firstFaceBrickSide = GlueHelper.findBrickPlaneSide(firstBrickFaceNear, nearFace.getPlane().getCommonPlane(), true);


            Pair<PlaneSide> rowSidesLowerAndLeft = new Pair<>(lowerPlate,leftSide);
            GlueHelper glueHelperSideLeft = new GlueHelper(rowSidesLowerAndLeft);

            Brick[] leftSideRow = glueHelperSideLeft.glueRow(firstFaceBrickSide, factory, countY);

            bricks.addAll(Arrays.asList(leftSideRow));

            factory.setOrientation(GlueHelper.toggleOrientation(firstBrickOrientation));
            Brick lastBrickLeftRow = leftSideRow[leftSideRow.length - 1];
            PlaneSide lastLeftRowBrickSide = GlueHelper.findBrickPlaneSide(lastBrickLeftRow, nearFace.getPlane().getCommonPlane(), true);
            Pair<PlaneSide> rowSidesFaceFar = new Pair<>(lowerPlate,lastLeftRowBrickSide);

            GlueHelper glueHelperFaceFar = new GlueHelper(rowSidesFaceFar);

            Brick[] faceRowFar = glueHelperFaceFar.glueRow(leftSide, factory, countX);

            bricks.addAll(Arrays.asList(faceRowFar));








        }
        else {

        }


        System.out.println("produced = " + factory.producedCount());

    }

    private PlaneSide getPlaneSide(CommonPlane plane) {
        return startingPlanes.asList().stream().filter(planeSide -> planeSide.getPlane().getCommonPlane() == plane).findFirst().get();
    }


}
