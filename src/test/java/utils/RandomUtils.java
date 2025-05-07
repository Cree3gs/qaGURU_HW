package utils;

import java.util.concurrent.ThreadLocalRandom;

import static utils.RandomUtilsFromLesson.getRandomString;

public class RandomUtils {

    public static void main(String[] args) {
        System.out.println(getRandomString(10));
        System.out.println(getRandomInt(1, 30));
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // +3 (263) 253 - 66 - 12
    public static String getRandomPhone() {
        return String.format("%s %s %s %s %s", getRandomInt(1, 9), getRandomInt(100, 999),
                             getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }
}
