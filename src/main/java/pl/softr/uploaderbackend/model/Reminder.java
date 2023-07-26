package pl.softr.uploaderbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.softr.uploaderbackend.Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "REMINDERS")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String message;
    @Column
    @JsonFormat(pattern = Utils.DATE_FORMAT)
    private LocalDate remindDate;
    @Column
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime remindTime;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(LocalDate remindDate) {
        this.remindDate = remindDate;
    }

    public LocalTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(LocalTime remindTime) {
        this.remindTime = remindTime;
    }
}
