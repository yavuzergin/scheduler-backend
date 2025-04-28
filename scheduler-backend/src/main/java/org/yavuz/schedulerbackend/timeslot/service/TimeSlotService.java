package org.yavuz.schedulerbackend.timeslot.service;

import org.springframework.stereotype.Service;
import org.yavuz.schedulerbackend.exception.ResourceNotFoundException;
import org.yavuz.schedulerbackend.timeslot.DTO.TimeSlotDTO;
import org.yavuz.schedulerbackend.timeslot.model.TimeSlot;
import org.yavuz.schedulerbackend.timeslot.repository.TimeSlotRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private static final int slotDurationMinutes = 5;
    private static final String[] days = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlotDTO> createTimeSlots() {
        List<TimeSlot> timeSlots = new ArrayList<>();
        for(String day : days) {
            LocalTime time = LocalTime.of(0, 0);
            while (time.isBefore(LocalTime.of(23,59))) {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setDay(day);
                timeSlot.setStartTime(time.getHour() * 60 + time.getMinute());
                timeSlot.setEndTime(time.plusMinutes(slotDurationMinutes).getHour() * 60 + time.plusMinutes(slotDurationMinutes).getMinute());
                timeSlot.setIsAvailable(true);
                timeSlots.add(timeSlot);
                time =  time.plusMinutes(slotDurationMinutes);
            }
        }
        timeSlotRepository.saveAll(timeSlots);
        return timeSlots.stream().map(TimeSlotDTO::new).collect(Collectors.toList());
    }

    public void updateTimeSlotAvailability (Long id, boolean isAvailable) {
        TimeSlot timeSlot = timeSlotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + "id'sine ait zaman dilimi bulunamadı."));
        timeSlot.setIsAvailable((isAvailable));
        timeSlotRepository.save(timeSlot);
    }
    public List <TimeSlotDTO> getTimeSlots() {
        return timeSlotRepository.findAll().stream().map(TimeSlotDTO::new).collect(Collectors.toList());

    }

}