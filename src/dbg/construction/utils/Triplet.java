package dbg.construction.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bogdel on 20.11.15.
 */
public class Triplet <T> {

    private final T first;
    private final T second;
    private final T third;

    public Triplet(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    public void assertNotEmpty() {
        assertNotEmpty(first,   "first");
        assertNotEmpty(second,  "second");
        assertNotEmpty(third,   "third");
    }

    private void assertNotEmpty(T value, String name) {
        if (null == value) {
            throw new IllegalStateException("Value " + name + " must not be null in " + toString());
        }
    }

    public List<T> asList() {
        return new ArrayList<T>() {
            {
                add(first);
                add(second);
                add(third);
            }
        };
    }


    @Override
    public String toString() {
        return "Triplet{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
