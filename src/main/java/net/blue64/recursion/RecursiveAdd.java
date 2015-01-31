package net.blue64.recursion;

import java.util.List;

public class RecursiveAdd {

    /**
     * Given a List, add the numbers in a recursive manor
     *
     * @param intList
     * @return
     */
    public int add(List<Integer> intList) {
        if (intList == null)
            return 0;
        //return 0;

        //TODO: Remove below here before giving to candidate
        return _add(intList, 0, 0);
    }

    //TODO: Remove below here before giving to candidate
    private int _add(List<Integer> list, int position, int sum) {
        if (position >= list.size() - 1)
            return sum + list.get(position);
        else
            return _add(list, ++position, sum + list.get(--position));
    }
}