package org.yavuz.schedulerbackend.schedulersettings.service;

import org.yavuz.schedulerbackend.schedulersettings.DTO.SchedulerSettingsDTO;
import org.yavuz.schedulerbackend.schedulersettings.model.SchedulerSettings;
import org.yavuz.schedulerbackend.schedulersettings.repository.SchedulerSettingsRepository;

public class SchedulerSettingsService {
    private final SchedulerSettingsRepository schedulerSettingsRepository;

    public SchedulerSettingsService(SchedulerSettingsRepository schedulerSettingsRepository){
        this.schedulerSettingsRepository = schedulerSettingsRepository;
    }

    public SchedulerSettingsDTO saveSettings(SchedulerSettingsDTO schedulerSettingsDTO){
        SchedulerSettings settings = new SchedulerSettings();
        settings.setTotalDurationMinutes(schedulerSettingsDTO.getTotalDurationMinutes());
        settings.setStartHour(schedulerSettingsDTO.getStartHour());
        settings.setEndHour(schedulerSettingsDTO.getEndHour());
        return new SchedulerSettingsDTO(settings);
    }
}
