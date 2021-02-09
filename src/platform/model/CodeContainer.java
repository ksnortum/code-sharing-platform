package platform.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeContainer {
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public static LocalDateTime parse(String dateIn) {
        return LocalDateTime.parse(dateIn, FORMATTER);
    }

    private String code;
    private final String date;

    public CodeContainer() {
        code = "//Code goes here";
        date = LocalDateTime.now().format(FORMATTER);
    }

    public CodeContainer(String code) {
        this.code = code;
        this.date = LocalDateTime.now().format(FORMATTER);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("code:%n%s%ndate = %s%n", code, date);

    }
}
