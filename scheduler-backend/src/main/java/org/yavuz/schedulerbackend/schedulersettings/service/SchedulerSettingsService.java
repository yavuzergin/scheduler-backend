package org.yavuz.schedulerbackend.schedulersettings.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yavuz.schedulerbackend.schedulersettings.DTO.SchedulerSettingsDTO;
import org.yavuz.schedulerbackend.schedulersettings.model.SchedulerSettings;
import org.yavuz.schedulerbackend.schedulersettings.repository.SchedulerSettingsRepository;
@Service
public class SchedulerSettingsService {
    private final SchedulerSettingsRepository schedulerSettingsRepository;

    public SchedulerSettingsService(SchedulerSettingsRepository schedulerSettingsRepository){
        this.schedulerSettingsRepository = schedulerSettingsRepository;
    }

    @Transactional
    public SchedulerSettingsDTO saveSettings(SchedulerSettingsDTO schedulerSettingsDTO){
        SchedulerSettings settings = new SchedulerSettings();
        settings.setTotalDurationMinutes(schedulerSettingsDTO.getTotalDurationMinutes());
        settings.setStartHour(schedulerSettingsDTO.getStartHour());
        settings.setEndHour(schedulerSettingsDTO.getEndHour());
        settings = schedulerSettingsRepository.save(settings);
        return new SchedulerSettingsDTO(settings);
    }
}
