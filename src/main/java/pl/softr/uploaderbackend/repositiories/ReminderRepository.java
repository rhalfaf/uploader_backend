package pl.softr.uploaderbackend.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.softr.uploaderbackend.model.Reminder;

import java.time.LocalDate;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findAllByRemindDate(LocalDate date);
}
