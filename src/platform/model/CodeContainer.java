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
public class CodeContainer {
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss.A";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Id
    private final String id;
    private String code;
    private String date;
    private int time;
    private int originalTime;
    private int views;
    private boolean hasViewRestriction;

    public static LocalDateTime parse(String dateIn) {
        return LocalDateTime.parse(dateIn, FORMATTER);
    }

    public CodeContainer() {
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();
        code = "";
        date = LocalDateTime.now().format(FORMATTER);
        time = 0;
        originalTime = 0;
        views = 0;
        hasViewRestriction = false;
    }

    public void updateTime() {
        LocalDateTime creationDate = parse(date);
        LocalDateTime now = LocalDateTime.now();
        time = originalTime - (int)creationDate.until(now, ChronoUnit.SECONDS);
    }

    public void decrementViews() {
        views--;
    }

    @JsonIgnore
    public boolean isHidden() {
        return (originalTime > 0 && time <= 0) || (hasViewRestriction && views <= 0);
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

    @JsonIgnore
    public int getOriginalTime() {
        return originalTime;
    }

    public void setOriginalTime(int originalTime) {
        this.originalTime = originalTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @JsonIgnore
    public boolean hasViewRestriction() {
        return hasViewRestriction;
    }

    public void setHasViewRestriction(boolean hasViewRestriction) {
        this.hasViewRestriction = hasViewRestriction;
    }

    @Override
    public String toString() {
        return String.format("CodeContainer{ id = %s, date = %s, time = %d, hasViewRestriction = %s%n" +
                "code:%n%s%n" +
                "}",
                id, date, time, hasViewRestriction, code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeContainer that = (CodeContainer) o;

        return code.equals(that.code) && date.equals(that.date) && time == that.time && views == that.views;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, date, time, views);
    }
}
