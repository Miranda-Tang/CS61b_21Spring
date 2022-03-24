package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> al = new AListNoResizing<>();
      BuggyAList<Integer> bl = new BuggyAList<>();
      al.addLast(3);
      al.addLast(6);
      al.addLast(9);
      bl.addLast(3);
      bl.addLast(6);
      bl.addLast(9);

      assertEquals(al.size(), bl.size());

      assertEquals(al.removeLast(), bl.removeLast());
      assertEquals(al.removeLast(), bl.removeLast());
      assertEquals(al.removeLast(), bl.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        BuggyAList<Integer> bL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                bL.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
                assertEquals(L.getLast(), bL.getLast());

            } else if (operationNumber == 1) {
                // size
//                System.out.println("size: " + L.size());
                assertEquals(L.size(), bL.size());

            } else if (operationNumber == 2) {
                if (L.size() > 0) {
//                    System.out.println("last: " + L.getLast());
                }
            } else {
                if (L.size() > 0) {
                    int last = L.removeLast();
                    int blast = bL.removeLast();
                    assertEquals(last, blast);
                }
            }
        }
    }
}
