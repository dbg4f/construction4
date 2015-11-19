package dbg.construction.geometry;

/**
 * @author bogdel on 18.11.15.
 */
public enum Axis {
    X {
        @Override
        public CommonPlane normalFor() {
            return CommonPlane.YOZ;
        }
    },
    Y {
        @Override
        public CommonPlane normalFor() {
            return CommonPlane.XOZ;
        }
    },
    Z {
        @Override
        public CommonPlane normalFor() {
            return CommonPlane.XOY;
        }
    };

    public abstract CommonPlane normalFor();
}
