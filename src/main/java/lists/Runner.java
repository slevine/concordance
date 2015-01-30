package lists;

import java.util.NoSuchElementException;

/**
 * Author: Steve Levine
 * Date: 1/8/15
 */
public class Runner {
    public static interface List<T> {
        T head();

        List<T> tail();

        Boolean isEmpty();

        static <T> String mkString(final List<T> l, final String sep) {
            if (l.isEmpty()) return "NIL";
            else return l.head() + sep + mkString(l.tail(), sep);
        }

        static <T> T nth(final int n, final List<T> l) {
            if (n == 1) return l.head();
            else return nth(n-1, l.tail());
        }
    }

    public static class Empty<T> implements List<T> {

        @Override public T head() {
            throw new NoSuchElementException("List.head");
        }

        @Override public List<T> tail() {
            throw new NoSuchElementException("List.tail");
        }

        @Override public Boolean isEmpty() {
            return true;
        }
    }

    public static class Cons<T> implements List<T> {

        final private T head;
        final private List<T> tail;

        public Cons(T head, List<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override public T head() {
            return head;
        }

        @Override public List<T> tail() {
            return tail;
        }

        @Override public Boolean isEmpty() {
            return false;
        }
    }

    public static void main(String[] args) {
        final List<Integer> oneTwoThree = new Cons<>(1,new Cons<>(2, new Cons<>(3, new Empty<>())));
        System.out.println(List.mkString(oneTwoThree, "::"));
        System.out.println(List.nth(3, oneTwoThree));
    }

}
