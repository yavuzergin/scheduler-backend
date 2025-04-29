package org.yavuz.schedulerbackend.duties.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yavuz.schedulerbackend.duties.DTO.DutyDTO;
import org.yavuz.schedulerbackend.duties.model.Duty;
import org.yavuz.schedulerbackend.duties.repository.DutyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DutyServiceTest {
    @Mock
    private DutyRepository dutyRepository;
    @InjectMocks
    private DutyService dutyService;

    @Test
    void createDuty(){
        DutyDTO dutyDTO = new DutyDTO();
        dutyDTO.setTaskName("Math");
        dutyDTO.setTaskTime(120);
        dutyDTO.setTaskColor("Blue");

        Duty duty = new Duty();
        duty.setTaskName("Math");
        duty.setTaskTime(120);
        duty.setTaskColor("Blue");

        when(dutyRepository.save(any(Duty.class))).thenReturn(duty);
        dutyService.createDuty(dutyDTO);
        assertNotNull(duty);
        assertEquals("Math", duty.getTaskName());
        assertEquals(120, duty.getTaskTime());
        assertEquals("Blue", duty.getTaskColor());
        verify(dutyRepository, times(1)).save(any(Duty.class));
    }

    @Test
    void getAllDuties(){
        Duty duty = new Duty();
        duty.setId(1L);
        duty.setTaskName("Math");

        Duty duty2= new Duty();
        duty2.setId(2L);
        duty2.setTaskName("Music");

        List<Duty> duties = Arrays.asList(duty, duty2);
        when(dutyRepository.findAll()).thenReturn(duties);
        List<Duty> result = dutyService.getAllDuties();
        assertEquals(2, result.size());
        assertEquals("Math", result.get(0).getTaskName());
        verify(dutyRepository, times(1)).findAll();
    }
    @Test
    void deleteDuty(){
        Duty duty = new Duty();
        duty.setId(2L);
        when(dutyRepository.findById(2L)).thenReturn(Optional.of(duty));
        dutyService.deleteDuty(2L);
        verify(dutyRepository, times(1)).deleteById(2L);
    }

}