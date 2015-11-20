package dbg.construction.utils;

import java.util.*;

/**
 * @author bogdel on 18.11.15.
 */
public class CollectionUtils {

    public static void assertAllDifferent(Object... entities) {
        assertAllDifferent(Arrays.asList(entities));
    }

    public static void assertAllDifferent(List<?> entities) {

        Set<?> set = new LinkedHashSet<>(entities);

        if (set.size() != entities.size()) {
            throw new IllegalArgumentException("All entities must be different: " + entities + ", " + set);
        }

    }

}
