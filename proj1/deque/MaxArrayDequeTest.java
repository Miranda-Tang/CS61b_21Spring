package deque;

import jh61b.junit.In;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    private static class intComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class stringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    private static class stringComparator2 implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }

    public static intComparator getIntComparator() {
        return new intComparator();
    }

    public static stringComparator getStringComparator() {
        return new stringComparator();
    }

    public static stringComparator2 getStringComparator2() {
        return new stringComparator2();
    }

    @Test
    public void largerIntTest() {
        Comparator<Integer> cmp = getIntComparator();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(cmp);
        mad.addFirst(1);
        mad.addFirst(3);
        mad.addFirst(7);
        mad.addFirst(5);
        int maxInt = mad.max();
        assertEquals(7, maxInt);
    }

    @Test
    public void largerStringTest() {
        Comparator<String> cmp1 = getStringComparator();
        Comparator<String> cmp2 = getStringComparator2();
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(cmp1);
        mad.addLast("anna");
        mad.addFirst("elsa");
        mad.addLast("banana");
        String lastAppear = mad.max();
        assertEquals("elsa", lastAppear);
    }
}
