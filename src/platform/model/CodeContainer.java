package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeContainer implements Comparable<CodeContainer> {
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    private final int id;
    private String code;
    private String date;

    public CodeContainer() {
        id = COUNTER.getAndIncrement();
        code = "public static void main(String[] args) {\n" +
             "    // Code goes here\n" +
             "}";
        date = LocalDateTime.now().format(FORMATTER);
    }

    public static LocalDateTime parse(String dateIn) {
        return LocalDateTime.parse(dateIn, FORMATTER);
    }

    @JsonIgnore
    public int getId() {
        return id;
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

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("id = %d%ncode:%n%s%ndate = %s%n", id, code, date);

    }

    public int compareTo(CodeContainer that) {
        LocalDateTime thisDate = parse(date);
        LocalDateTime thatDate = parse(that.getDate());

        // Descending
        if (thisDate.isBefore(thatDate)) {
            return 1;
        } else if (thisDate.isAfter(thatDate)) {
            return -1;
        }

        // Dates are the same, sort by ID ascending
        return that.getId() - id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeContainer that = (CodeContainer) o;
        return id == that.id && code.equals(that.code) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, date);
    }
}
