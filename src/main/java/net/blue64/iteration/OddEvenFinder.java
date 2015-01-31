package net.blue64.iteration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: selevine
 * Date: Jun 17, 2010
 * Time: 6:35:42 PM
 */

public class OddEvenFinder {

    enum Key {
        ODD, EVEN
    }

    public Map<Key, List<Integer>> findOddEven(List<Integer> list) {
        Map<Key, List<Integer>> results = new HashMap<Key, List<Integer>>();
        results.put(Key.ODD, new ArrayList<Integer>());
        results.put(Key.EVEN, new ArrayList<Integer>());
        for (int i : list) {
            if (i % 2 == 0)
                results.get(Key.EVEN).add(i);
            else
                results.get(Key.ODD).add(i);
        }
        return results;
    }
}
