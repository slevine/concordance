package lamdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

import static java.util.stream.Collectors.toList;

/**
 * Author: Steve Levine
 * Date: 1/8/15
 */
public class LambdaRunner {

    public static class MyInt {
        private final int i;

        public MyInt(int i) {
            this.i = i;
        }

        @Override public String toString() {
            return "MyInt{" +
                    "i=" + i +
                    '}';
        }
    }

    // Take an Int and transform it to an R (required to perform one-liner)
    public interface Transformer<R> {
        static <R> R p(final IntFunction<R> f, Integer i) {
            return f.apply(i);
        }
    }


    public static List<MyInt> mapIntToMyInt(final List<Integer> ints, final IntFunction<Integer> f) {
        return ints.stream()
                .map(f::apply)
                .map(MyInt::new)
                .collect(toList());
    }

    public static void main(String[] args) {
        System.out.println(Transformer.<Integer>p(x -> x + 1, 1));
        System.out.println(mapIntToMyInt(Arrays.asList(1,2,3,4), x -> x + 1));
    }


}
