package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Author: Steve Levine
 * Date: 1/8/15
 */
public class Runner {

    public static interface Tree<T> {
        T getElement();

        Tree<T> getLeft();

        Tree<T> getRight();

        boolean isEmpty();

        static <T> List<T> depthFirst(final Tree<T> t, final List<T> a) {
            if (t.isEmpty()) return a;
            else {
                depthFirst(t.getLeft(), a);
                a.add(t.getElement());
                depthFirst(t.getRight(), a);
                return a;
            }
        }
    }

    public static class Empty<T> implements Tree<T> {

        @Override public T getElement() {
            throw new NoSuchElementException("Tree.Element");
        }

        @Override public Tree<T> getLeft() {
            throw new NoSuchElementException("Tree.Left");
        }

        @Override public Tree<T> getRight() {
            throw new NoSuchElementException("Tree.Right");
        }

        @Override public boolean isEmpty() {
            return true;
        }

        @Override public String toString() {
            return "Empty";
        }
    }

    public static class Node<T> implements Tree<T> {
        final T element;
        final Tree<T> left;
        final Tree<T> right;

        public Node(T element, Tree<T> left, Tree<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        @Override public boolean isEmpty() {
            return false;
        }

        @Override public T getElement() {
            return element;
        }

        @Override public Tree<T> getLeft() {
            return left;
        }

        @Override public Tree<T> getRight() {
            return right;
        }

        @Override public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        final Tree<Integer> simple =
                new Node<>(10,
                        new Node<>(5,
                                new Node<>(1, new Empty<>(), new Empty<>()),
                                new Empty<>()),
                        new Node<>(7,
                                new Node<>(4, new Empty<>(), new Empty<>()),
                                new Node<>(13,
                                        new Node<>(9, new Empty<>(), new Empty<>()),
                                        new Empty<>())));
        System.out.println(Tree.depthFirst(simple, new ArrayList<>()));

    }


}
