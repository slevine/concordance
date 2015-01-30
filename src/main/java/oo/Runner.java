package oo;

/**
 * Created by IntelliJ IDEA.
 * Author: Steve Levine
 * Date: 1/4/15
 */
public class Runner {

    public interface Animal {
        String speak(String word);

        default int size() {
            return 80;
        }
    }

    public interface Domestic {
        default int size() {
            return 8;
        }
    }

    static public class Cat implements Animal, Domestic {

        public int size() {
            // Disambiguate between Animal, Domestic (solves multiple inheritance issue)
            return Animal.super.size();
        }

        public String mode() {
            return "ball";
        }

        @Override public String speak(String word) {
            return "CAT:" + word;
        }
    }

    static public class ESH extends Cat implements Animal, Domestic {
        public Object speak(Object word) {
            return "ESH:" + word;
        }
    }


    public static void main(String[] args) {
        Object a = new Cat();
        Object e = new ESH();

        System.out.println(a.getClass());
        System.out.println(e.getClass());

        System.out.println(((Animal) a).speak("hi"));      // speak is found in IF, but binds to first impl in chain
        System.out.println(((ESH) e).speak("hi"));         // a "string" binds to Cat instead of ESH
        System.out.println(((ESH) e).speak((Object) "hi")); // an "object" binds to overloaded ESH

        System.out.println(((Cat) a).mode());

        System.out.println(((Animal) a).size());     // a is Cat, last on chain is Domestic, thus 8
        System.out.println(((Domestic) a).size());   // a is Cat, last on chain is Domestic, thus 8

    }
}
