package net.blue64.iteration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;


/**
 * Unit test for OddEvenFinder
 */

public class OddEvenFinderTest {

    private OddEvenFinder oddEvenFinder;
    private List<Integer> list;

    @Before
    public void setup() {
        oddEvenFinder = new OddEvenFinder();
        list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
        }};
    }

    @After
    public void after() {
        oddEvenFinder = new OddEvenFinder();
        list = null;
    }

    @Test
    public void testFindOddEvens() {
        Map<OddEvenFinder.Key, List<Integer>> results = oddEvenFinder.findOddEven(list);
        assertNotNull("Results list null", results);
        List<Integer> odds = results.get(OddEvenFinder.Key.ODD);
        List<Integer> evens = results.get(OddEvenFinder.Key.EVEN);
        assertNotNull(odds);
        assertNotNull(evens);
        assertTrue(odds.contains(1));
        assertTrue(odds.contains(3));
        assertTrue(odds.contains(5));
        assertTrue(evens.contains(2));
        assertTrue(evens.contains(4));
        assertTrue(evens.contains(6));
    }
}