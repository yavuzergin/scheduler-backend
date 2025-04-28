package org.yavuz.schedulerbackend.schedulersettings.DTO;

import lombok.Data;
import org.yavuz.schedulerbackend.schedulersettings.model.SchedulerSettings;

@Data
public class SchedulerSettingsDTO {
    private Long id;
    private int totalDurationMinutes;
    private int startHour;
    private int endHour;

    public SchedulerSettingsDTO(SchedulerSettings schedulerSettings){
        this.id = schedulerSettings.getId();
        this.totalDurationMinutes = schedulerSettings.getTotalDurationMinutes();
        this.startHour = schedulerSettings.getStartHour();
        this.endHour = schedulerSettings.getStartHour();
    }
}
