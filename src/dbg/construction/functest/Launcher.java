package dbg.construction.functest;

import dbg.construction.bricking.Brick;
import dbg.construction.bricking.BrickFactory;
import dbg.construction.bricking.GlueHelper;
import dbg.construction.bricking.Registry;
import dbg.construction.bricking.draw.ProjectionHelper;
import dbg.construction.bricking.draw.SvgRect;
import dbg.construction.geometry.CommonPlane;
import dbg.construction.geometry.Plane;
import dbg.construction.geometry.PlaneSide;
import dbg.construction.utils.Pair;

/**
 * @author bogdel on 20.11.15.
 */
public class Launcher {

    public static void main(String[] args) {


        BrickFactory factory = new BrickFactory("A375", Registry.AEROC_375);

        PlaneSide startFace = new PlaneSide(new Plane(CommonPlane.YOZ, 1000), true);

        Pair<PlaneSide> rowSides = new Pair<>(
                new PlaneSide(new Plane(CommonPlane.XOY, 1000), true),
                new PlaneSide(new Plane(CommonPlane.XOZ, 1000), true)
        );

        GlueHelper glueHelper = new GlueHelper(rowSides);


        Brick[] row = glueHelper.glueRow(startFace, factory, 8);

        for (Brick brick : row) {

            //System.out.println(brick);

            SvgRect svgRect = new SvgRect(brick.getCenter(), brick.getDimensions(), CommonPlane.XOZ);

            System.out.println(svgRect.toSvg(0.1));


        }





/*
        Brick brick1 = glueHelper.glue(factory.newBrick(), startFace);

        System.out.println("positionedBrick = " + brick1);

        PlaneSide mountBrickSide = GlueHelper.findBrickPlaneSide(brick1, startFace.getPlane().getCommonPlane(), startFace.isNormalDirection());

        Brick brick2 = glueHelper.glue(factory.newBrick(), mountBrickSide);

        System.out.println("brick2 = " + brick2);
*/



    }

}
