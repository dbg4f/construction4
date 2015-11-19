package dbg.construction.geometry;

import dbg.construction.utils.Pair;

/**
 * @author bogdel on 18.11.15.
 */
public enum CommonPlane {
    XOY {
        @Override
        public Axis getNormal() {
            return Axis.Z;
        }

        @Override
        public Pair<Axis> getAxles() {
            return new Pair<Axis>(Axis.X, Axis.Y);
        }
    },

    XOZ {
        @Override
        public Axis getNormal() {
            return Axis.Y;
        }

        @Override
        public Pair<Axis> getAxles() {
            return new Pair<Axis>(Axis.X, Axis.Z);
        }
    },

    YOZ {
        @Override
        public Axis getNormal() {
            return Axis.X;
        }

        @Override
        public Pair<Axis> getAxles() {
            return new Pair<Axis>(Axis.Y, Axis.Z);
        }
    };

    public abstract Axis getNormal();
    public abstract Pair<Axis> getAxles();


}
