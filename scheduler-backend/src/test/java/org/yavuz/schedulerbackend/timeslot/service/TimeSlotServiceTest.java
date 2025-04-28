package org.yavuz.schedulerbackend.timeslot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.schedulerbackend.timeslot.DTO.TimeSlotDTO;
import org.yavuz.schedulerbackend.timeslot.model.TimeSlot;
import org.yavuz.schedulerbackend.timeslot.repository.TimeSlotRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeSlotServiceTest {
    @Mock
    private TimeSlotRepository timeSlotRepository;
    @InjectMocks
    private TimeSlotService timeSlotService;
    @Test
    void testCreateTimeSlots(){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDay("Monday");
        timeSlot.setStartTime(0);
        timeSlot.setEndTime(5);
        timeSlot.setIsAvailable(true);

        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setDay("Monday");
        timeSlot2.setStartTime(5);
        timeSlot2.setEndTime(10);
        timeSlot2.setIsAvailable(true);

        List<TimeSlot> mockTimeSlots = Arrays.asList(timeSlot, timeSlot2);
        when(timeSlotRepository.saveAll(anyList())).thenReturn(mockTimeSlots);
        List<TimeSlotDTO> result = timeSlotService.createTimeSlots();
        assertNotNull(result);
        assertTrue(result.size() > 0);
        verify(timeSlotRepository, times(1)).deleteAll();
        verify(timeSlotRepository, times(1)).saveAll(anyList());
    }
    @Test
    void testUpdateTimeSlotAvailability() {
        Long timeSlotId = 1L;
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(timeSlotId);
        timeSlot.setDay("Monday");
        timeSlot.setStartTime(0);
        timeSlot.setEndTime(5);
        timeSlot.setIsAvailable(true);

        when(timeSlotRepository.findById(timeSlotId)).thenReturn(Optional.of(timeSlot));
        when(timeSlotRepository.save(any(TimeSlot.class))).thenReturn(timeSlot);
        timeSlotService.updateTimeSlotAvailability(timeSlotId, false);
        assertFalse(timeSlot.getIsAvailable());
        verify(timeSlotRepository, times(1)).findById(timeSlotId);
        verify(timeSlotRepository, times(1)).save(timeSlot);
    }
    @Test
    void testGetTimeSlots() {
        // Arrange
        TimeSlot timeSlot1 = new TimeSlot();
        timeSlot1.setDay("Monday");
        timeSlot1.setStartTime(0);
        timeSlot1.setEndTime(5);
        timeSlot1.setIsAvailable(true);
        TimeSlot timeSlot2 = new TimeSlot();
        timeSlot2.setDay("Tuesday");
        timeSlot2.setStartTime(5);
        timeSlot2.setEndTime(10);
        timeSlot2.setIsAvailable(true);
        List<TimeSlot> mockTimeSlots = Arrays.asList(timeSlot1, timeSlot2);
        when(timeSlotRepository.findAll()).thenReturn(mockTimeSlots);
        List<TimeSlotDTO> result = timeSlotService.getTimeSlots();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(timeSlotRepository, times(1)).findAll();
    }

}