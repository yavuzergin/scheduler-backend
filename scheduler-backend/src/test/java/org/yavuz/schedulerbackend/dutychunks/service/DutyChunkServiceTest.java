package org.yavuz.schedulerbackend.dutychunks.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.schedulerbackend.dutychunks.DTO.DutyChunkDTO;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;
import org.yavuz.schedulerbackend.dutychunks.repository.DutyChunkRepository;
import org.yavuz.schedulerbackend.timeslot.model.TimeSlot;
import org.yavuz.schedulerbackend.timeslot.repository.TimeSlotRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DutyChunkServiceTest {

    @Mock
    private DutyChunkRepository dutyChunkRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private DutyChunkService dutyChunkService;

    @Test
    void testAssignDutyToTimeSlot() {

        Long parentId = 1L;
        Long timeSlotsId = 2L;
        int order = 1;

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(timeSlotsId);
        timeSlot.setIsAvailable(true);
        when(timeSlotRepository.findById(timeSlotsId)).thenReturn(Optional.of(timeSlot));

        DutyChunk dutyChunk = new DutyChunk();
        dutyChunk.setParentId(parentId);
        dutyChunk.setTimeSlotsId(timeSlotsId);
        dutyChunk.setChunkOrder(order);
        when(dutyChunkRepository.save(any(DutyChunk.class))).thenReturn(dutyChunk);


        DutyChunkDTO result = dutyChunkService.assignDutyToTimeSlot(parentId, timeSlotsId, order);

        assertNotNull(result);
        /*assertEquals(parentId, result.getParentId());*/
        assertEquals(timeSlotsId, result.getTimeSlotsId());
        assertEquals(order, result.getChunkOrder());
        verify(timeSlotRepository, times(1)).findById(timeSlotsId);
        verify(dutyChunkRepository, times(1)).save(any(DutyChunk.class));
    }
    @Test
    void testDeleteDutyChunkFromTimeSlot() {
        Long timeSlotsId = 2L;

        DutyChunk dutyChunk = new DutyChunk();
        dutyChunk.setTimeSlotsId(timeSlotsId);
        List<DutyChunk> dutyChunks = Arrays.asList(dutyChunk);
        when(dutyChunkRepository.findByTimeSlotsId(timeSlotsId)).thenReturn(dutyChunks);

        dutyChunkService.deleteDutyChunkFromTimeSlot(timeSlotsId);

        verify(dutyChunkRepository, times(1)).findByTimeSlotsId(timeSlotsId);
        verify(dutyChunkRepository, times(1)).deleteAll(dutyChunks);
    }

    @Test
    void testListDutyChunks() {
        DutyChunk dutyChunk1 = new DutyChunk();
        dutyChunk1.setId(1L);
        dutyChunk1.setParentId(1L);
        dutyChunk1.setTimeSlotsId(2L);
        dutyChunk1.setChunkOrder(1);

        DutyChunk dutyChunk2 = new DutyChunk();
        dutyChunk2.setId(2L);
        dutyChunk2.setParentId(3L);
        dutyChunk2.setTimeSlotsId(4L);
        dutyChunk2.setChunkOrder(2);

        List<DutyChunk> dutyChunks = Arrays.asList(dutyChunk1, dutyChunk2);
        when(dutyChunkRepository.findAll()).thenReturn(dutyChunks);

        List<DutyChunkDTO> result = dutyChunkService.listDutyChunks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getParentId());
        assertEquals(2L, result.get(1).getParentId());
        verify(dutyChunkRepository, times(1)).findAll();
    }
}
