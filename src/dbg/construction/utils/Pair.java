package dbg.construction.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bogdel on 18.11.15.
 */
public class Pair <T> {

    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public List<T> asList() {
        return new ArrayList<T>() {
            {
                add(first);
                add(second);
            }
        };
    }

}
