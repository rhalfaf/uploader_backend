package pl.softr.uploaderbackend.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.softr.uploaderbackend.Utils;
import pl.softr.uploaderbackend.model.Reminder;
import pl.softr.uploaderbackend.services.ReminderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("reminder")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping("add")
    public ResponseEntity<Reminder> addReminder(@RequestBody Reminder reminder) {
        return ResponseEntity.ok(reminderService.addReminder(reminder));
    }

    @GetMapping
    public ResponseEntity<Reminder> getReminderById(@RequestParam Long id) {
        return ResponseEntity.of(reminderService.getReminderById(id));
    }

    @GetMapping("byDate")
    public ResponseEntity<List<Reminder>> getRemindersByDate(
            @RequestParam @DateTimeFormat(pattern = Utils.DATE_FORMAT) LocalDate date) {
        return ResponseEntity.ok(reminderService.getRemindersByDate(date));
    }

    @GetMapping("all")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        return ResponseEntity.ok(reminderService.getAllReminders());
    }
}
