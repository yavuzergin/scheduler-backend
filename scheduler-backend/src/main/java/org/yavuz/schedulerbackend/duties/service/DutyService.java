package org.yavuz.schedulerbackend.duties.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yavuz.schedulerbackend.duties.DTO.DutyDTO;
import org.yavuz.schedulerbackend.duties.model.Duty;
import org.yavuz.schedulerbackend.duties.repository.DutyRepository;
import org.yavuz.schedulerbackend.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class DutyService {
    @Autowired
    private final DutyRepository dutyRepository;

    public DutyService(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    public DutyDTO createDuty (DutyDTO dutyDTO) {
        Duty duty = new Duty();
        duty.setTaskName(dutyDTO.getTaskName());
        duty.setTaskTime(dutyDTO.getTaskTime());
        duty.setTaskColor(dutyDTO.getTaskColor());
        Duty createdDuty = dutyRepository.save(duty);
        return new DutyDTO(createdDuty);
    }

    public List<Duty> getAllDuties() {
        return dutyRepository.findAll();
    }

    public void deleteDuty (Long id) {
        Duty duty = dutyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException( id + "id'li görev bulunamadı"));
        dutyRepository.deleteById(id);
    }


}
