package dbg.construction.geometry;

import dbg.construction.utils.Pair;

/**
 * @author bogdel on 18.11.15.
 */
public enum CommonPlane {
    XOY {
        @Override
        public Axle getNormal() {
            return Axle.Z;
        }

        @Override
        public Pair<Axle> getAxles() {
            return new Pair<Axle>(Axle.X, Axle.Y);
        }
    },

    XOZ {
        @Override
        public Axle getNormal() {
            return Axle.Y;
        }

        @Override
        public Pair<Axle> getAxles() {
            return new Pair<Axle>(Axle.X, Axle.Z);
        }
    },

    YOZ {
        @Override
        public Axle getNormal() {
            return Axle.X;
        }

        @Override
        public Pair<Axle> getAxles() {
            return new Pair<Axle>(Axle.Y, Axle.Z);
        }
    };

    public abstract Axle getNormal();
    public abstract Pair<Axle> getAxles();


}
