package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
            return instance;
        }

    public LocalDate currentDate() {
            return LocalDate.now();
    }


    public static String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    private static String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

}
