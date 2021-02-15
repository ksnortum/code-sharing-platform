package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "code_container")
public class CodeContainer implements Comparable<CodeContainer> {
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Id
    private final String id;
    private String code;
    private String date;
    private int time;
    private int views;
    private int numberOfTimesViewed;

    public static LocalDateTime parse(String dateIn) {
        return LocalDateTime.parse(dateIn, FORMATTER);
    }

    public CodeContainer() {
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();
        code = "public static void main(String[] args) {\n" +
               "    // Code goes here\n" +
               "}";
        date = LocalDateTime.now().format(FORMATTER);
        time = 0;
        views = 0;
        numberOfTimesViewed = 0;
    }

    public long remainingTime() {
        LocalDateTime creationDate = parse(date);
        LocalDateTime now = LocalDateTime.now();

        return time - creationDate.until(now, ChronoUnit.SECONDS);
    }

    public int remainingViews() {
        return views - numberOfTimesViewed;
    }

    public void incrementNumberOfTimesViewed() {
        numberOfTimesViewed++;
    }

    @JsonIgnore
    public boolean isHidden() {
        return (time > 0 && remainingTime() <= 0) || (views > 0 && remainingViews() <= 0);
    }

    @JsonIgnore
    public String getId() {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @JsonIgnore
    public int getNumberOfTimesViewed() {
        return numberOfTimesViewed;
    }

    public void setNumberOfTimesViewed(int numberOfTimesViewed) {
        this.numberOfTimesViewed = numberOfTimesViewed;
    }

    @Override
    public String toString() {
        return String.format("CodeContainer{ id = %s%n" +
                "code:%n%s%n" +
                "date = %s, time = %d, views = %d, numberOfTimesView = %d }",
                id, code, date, time, views, numberOfTimesViewed);
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

        // Dates are the same, sort by ID ascending, TODO what does this mean with UUIDs?
        return id.compareTo(that.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeContainer that = (CodeContainer) o;

        // TODO The UUIDs will never be the same, remove id.equals(that.id)?
        return id.equals(that.id) && code.equals(that.code) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, date);
    }
}
