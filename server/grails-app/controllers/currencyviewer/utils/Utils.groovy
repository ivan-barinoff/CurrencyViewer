package currencyviewer.utils

import java.time.format.DateTimeFormatter

import static java.time.format.DateTimeFormatter.ofPattern

final class Utils {
    public static final String DD_MM_YYYY = "dd.MM.yyyy"

    static DateTimeFormatter patternDDmmYYYY() {
        ofPattern(DD_MM_YYYY)
    }
}
