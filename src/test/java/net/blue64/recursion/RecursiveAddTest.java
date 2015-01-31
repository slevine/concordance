package net.blue64.recursion;

import net.blue64.recursion.RecursiveAdd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Unit test for simple RecursiveAdd.
 */

public class RecursiveAddTest {

    private RecursiveAdd ra;
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
        ra = new RecursiveAdd();
    }

    @After
    public void after() {
        ra = null;
        list = null;
    }

    @Test
    public void testAdd() {
        assertEquals(100, ra.add(list));
    }

    @Test
    public void testAddNull() {
        assertEquals(0, ra.add(null));
    }
}
