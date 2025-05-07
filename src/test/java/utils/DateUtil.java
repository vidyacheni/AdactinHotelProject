package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String getTodayDate() {
        return LocalDate.now().format(formatter);
    }

    public static String getTomorrowDate() {
        return LocalDate.now().plusDays(1).format(formatter);
    }
}
