package net.blue64.threads;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Unit test for simple RecursiveAdd.
 */

public class ThreadedAddTest {

    private ThreadedAdd ta;
    private List<Integer> list;

    @Before
    public void setup() {
        list = new ArrayList<Integer>() {{
            add(50);
            add(20);
            add(10);
            add(15);
            add(5);
        }};
        ta = new ThreadedAdd();
    }

    @After
    public void after() {
        ta = null;
        list = null;
    }

    @Test
    public void testAdd() {
        assertEquals(100, ta.add(list));
    }

    @Test
    public void testAddNull() {
        assertEquals(0, ta.add(null));
    }
}