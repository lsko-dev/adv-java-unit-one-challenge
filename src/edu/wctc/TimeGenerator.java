package edu.wctc;

import java.util.concurrent.ThreadLocalRandom;

public class TimeGenerator {
    static int getCheckInTime() {
        return ThreadLocalRandom.current().nextInt(7, 12 + 1);
    }

    static int getCheckOutTime() {
        return ThreadLocalRandom.current().nextInt(12, 23);
    }
}
