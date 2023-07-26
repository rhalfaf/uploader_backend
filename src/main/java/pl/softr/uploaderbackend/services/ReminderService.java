package pl.softr.uploaderbackend.services;

import org.springframework.stereotype.Service;
import pl.softr.uploaderbackend.model.Reminder;
import pl.softr.uploaderbackend.repositiories.ReminderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public Reminder addReminder(Reminder reminder) {
       return reminderRepository.save(reminder);
    }

    public Optional<Reminder> getReminderById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return reminderRepository.findById(id);
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public List<Reminder> getRemindersByDate(LocalDate date) {
        return reminderRepository.findAllByRemindDate(date);
    }
}
