package dbg.construction.utils;

/**
 * @author bogdel on 20.11.15.
 */
public class UniquePair<T> extends Pair<T> {
    public UniquePair(T first, T second) {
        super(first, second);
        CollectionUtils.assertAllDifferent(asList());
    }
}
