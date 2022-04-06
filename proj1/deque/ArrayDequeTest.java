package deque;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void forEachTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("Anna");
        ad.addLast("Elsa");
        ad.addFirst("Snowman");
        List<String> listOfItems = new ArrayList<>();
        for (String x : ad) {
            listOfItems.add(x);
        }
        String result = "{" + String.join(", ", listOfItems) + "}";
        assertEquals("{Snowman, Anna, Elsa}", result);
    }

    @Test
    public void isEqualsTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("Anna");
        ad.addLast("Elsa");
        ad.addFirst("Snowman");
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("Snowman");
        ad1.addLast("Anna");
        ad1.addLast("Elsa");
        assertTrue("The result of the comparison should be true: ", ad.equals(ad1));

        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ad2.addFirst("Snowman");
        ad2.addLast("Anna");
        ad2.addFirst("Elsa");
        assertFalse("The result of the comparison should be false: ", ad.equals(ad2));

        ArrayDeque<String> ad3 = new ArrayDeque<>();
        ArrayDeque<String> ad4 = null;
        ArrayDeque<String> ad5 = new ArrayDeque<>();
        assertFalse("The result of the comparison should be false: ", ad3.equals(ad4));
        assertTrue("The result of the comparison should be true: ", ad3.equals(ad5));
    }
}
