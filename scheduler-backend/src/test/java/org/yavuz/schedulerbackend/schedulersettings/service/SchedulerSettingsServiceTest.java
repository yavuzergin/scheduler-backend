package org.yavuz.schedulerbackend.schedulersettings.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.schedulerbackend.schedulersettings.DTO.SchedulerSettingsDTO;
import org.yavuz.schedulerbackend.schedulersettings.model.SchedulerSettings;
import org.yavuz.schedulerbackend.schedulersettings.repository.SchedulerSettingsRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchedulerSettingsServiceTest {

    @Mock
    private SchedulerSettingsRepository schedulerSettingsRepository;

    @InjectMocks
    private SchedulerSettingsService schedulerSettingsService;

    @Test
    void testSaveSettings() {
        SchedulerSettingsDTO settingsDTO = new SchedulerSettingsDTO();
        settingsDTO.setTotalDurationMinutes(15);
        settingsDTO.setStartHour(9);
        settingsDTO.setEndHour(17);

        SchedulerSettings settings = new SchedulerSettings();
        settings.setTotalDurationMinutes(settingsDTO.getTotalDurationMinutes());
        settings.setStartHour(settingsDTO.getStartHour());
        settings.setEndHour(settingsDTO.getEndHour());

        when(schedulerSettingsRepository.save(any(SchedulerSettings.class))).thenReturn(settings);

        SchedulerSettingsDTO result = schedulerSettingsService.saveSettings(settingsDTO);

        assertNotNull(result);
        assertEquals(15, result.getTotalDurationMinutes());
        assertEquals(9, result.getStartHour());
        assertEquals(17, result.getEndHour());
        verify(schedulerSettingsRepository, times(1)).save(any(SchedulerSettings.class));
    }
}