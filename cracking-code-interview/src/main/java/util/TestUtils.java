package util;

import java.util.Random;
import java.util.stream.IntStream;

public class TestUtils {
    public static int[] randomIntArray(int valueBound, int sizeBound) {
        var rand = new Random();
        return IntStream.generate(() -> rand.nextInt(valueBound))
                .limit(rand.nextInt(sizeBound))
                .toArray();
    }
}
