package dbg.construction.utils;

import java.util.*;

/**
 * @author bogdel on 18.11.15.
 */
public class CollectionUtils<T> {

    void assertAllDifferent(T... entities) {
        ArrayList<T> list = new ArrayList<>(Arrays.asList(entities));
        Set<T> set = new LinkedHashSet<>(list);

        if (set.size() != list.size()) {
            throw new IllegalArgumentException("All entities must be different: " + list + ", " + set);
        }

    }

}
