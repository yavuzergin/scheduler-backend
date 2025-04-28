package org.yavuz.schedulerbackend.schedulersettings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yavuz.schedulerbackend.schedulersettings.DTO.SchedulerSettingsDTO;
import org.yavuz.schedulerbackend.schedulersettings.model.SchedulerSettings;
import org.yavuz.schedulerbackend.schedulersettings.service.SchedulerSettingsService;

@RestController
@RequestMapping("/settings")
public class SchedulerSettingsController {
    @Autowired
    private final SchedulerSettingsService schedulerSettingsService;

    public SchedulerSettingsController(SchedulerSettingsService schedulerSettingsService){
        this.schedulerSettingsService = schedulerSettingsService;
    }

    @PutMapping("/save-settings")
    public SchedulerSettingsDTO saveSettings (@RequestBody SchedulerSettingsDTO schedulerSettingsDTO){
        return schedulerSettingsService.saveSettings(schedulerSettingsDTO);
    }
}
