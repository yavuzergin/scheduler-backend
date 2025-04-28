package org.yavuz.schedulerbackend.timeslot.DTO;

import lombok.Data;
import org.yavuz.schedulerbackend.timeslot.model.TimeSlot;

@Data
public class TimeSlotDTO {
    private Long id;
    private String day;
    private int startTime;
    private int endTime;
    private Boolean isAvailable;

    public TimeSlotDTO(TimeSlot timeSlot){
        this.id = timeSlot.getId();
        this.day = timeSlot.getDay();
        this.startTime = timeSlot.getStartTime();
        this.endTime = timeSlot.getEndTime();
        this.isAvailable = timeSlot.getIsAvailable();
    }
}
