package org.yavuz.schedulerbackend.timeslot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yavuz.schedulerbackend.timeslot.DTO.TimeSlotDTO;
import org.yavuz.schedulerbackend.timeslot.service.TimeSlotService;

import java.util.List;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    public TimeSlotController (TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping("/create-timeslot")
    public List<TimeSlotDTO> createTimeSlots(){
        return timeSlotService.createTimeSlots();
    }
    @GetMapping("/list-timeslots")
    public List<TimeSlotDTO> getAllTimeSlots() {
        return timeSlotService.getTimeSlots();
    }
    @PutMapping("/timeslots-availability/{id}")
    public ResponseEntity<Void> updateTimeSlotAvailability(@PathVariable Long id, @RequestBody boolean isAvailable) {
        timeSlotService.updateTimeSlotAvailability(id, isAvailable);
        return ResponseEntity.ok().build();
    }
}
