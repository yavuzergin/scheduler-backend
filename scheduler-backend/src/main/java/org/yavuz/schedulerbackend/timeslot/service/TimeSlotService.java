package org.yavuz.schedulerbackend.timeslot.service;

import org.springframework.stereotype.Service;
import org.yavuz.schedulerbackend.exception.ResourceNotFoundException;
import org.yavuz.schedulerbackend.timeslot.DTO.TimeSlotDTO;
import org.yavuz.schedulerbackend.timeslot.model.TimeSlot;
import org.yavuz.schedulerbackend.timeslot.repository.TimeSlotRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;
    private static final int slotDurationMinutes = 5;
    private static final String[] days = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    public int getSlotDurationMinutes() {
        return slotDurationMinutes;
    }

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlotDTO> createTimeSlots() {
        timeSlotRepository.deleteAll();
        List<TimeSlot> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(0, 0);
        LocalTime endTime = LocalTime.of(23, 59);
        int startMinute = startTime.getHour() * 60 + startTime.getMinute(); // 0
        int endMinute = endTime.getHour() * 60 + endTime.getMinute(); // 1439

        for (String day : days) {
            int currentMinute = startMinute;
            while (currentMinute <= endMinute) {
                LocalTime currentTime = LocalTime.of(currentMinute / 60, currentMinute % 60);
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setDay(day);
                int startTimeValue = currentMinute;
                timeSlot.setStartTime(startTimeValue);
                int slotEndMinute = currentMinute + slotDurationMinutes;
                if (slotEndMinute > endMinute) {
                    slotEndMinute = endMinute;
                }
                LocalTime slotEndTime = LocalTime.of(slotEndMinute / 60, slotEndMinute % 60);
                int endTimeValue = slotEndMinute;
                timeSlot.setEndTime(endTimeValue);
                timeSlot.setIsAvailable(true);
                timeSlots.add(timeSlot);
                currentMinute += slotDurationMinutes;
            }
        }
        timeSlotRepository.saveAll(timeSlots);
        return timeSlots.stream().map(TimeSlotDTO::new).collect(Collectors.toList());
    }

    public void updateTimeSlotAvailability(Long id, boolean isAvailable) {
        TimeSlot timeSlot = timeSlotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + "id'sine ait zaman dilimi bulunamadı."));
        timeSlot.setIsAvailable(isAvailable);
        timeSlotRepository.save(timeSlot);
    }

    public List<TimeSlotDTO> getTimeSlots() {
        return timeSlotRepository.findAll().stream().map(TimeSlotDTO::new).collect(Collectors.toList());
    }
}