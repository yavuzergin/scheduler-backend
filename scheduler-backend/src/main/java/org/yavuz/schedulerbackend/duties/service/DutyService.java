package org.yavuz.schedulerbackend.duties.service;

import org.springframework.stereotype.Service;
import org.yavuz.schedulerbackend.duties.DTO.DutyDTO;
import org.yavuz.schedulerbackend.duties.model.Duty;
import org.yavuz.schedulerbackend.duties.repository.DutyRepository;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;
import org.yavuz.schedulerbackend.dutychunks.repository.DutyChunkRepository;
import org.yavuz.schedulerbackend.exception.ResourceNotFoundException;
import org.yavuz.schedulerbackend.timeslot.repository.TimeSlotRepository;
import org.yavuz.schedulerbackend.timeslot.service.TimeSlotService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DutyService {
    private final DutyRepository dutyRepository;
    private final DutyChunkRepository dutyChunkRepository;
    private final TimeSlotService timeSlotService;

    public DutyService(DutyRepository dutyRepository, DutyChunkRepository dutyChunkRepository, TimeSlotService timeSlotService) {
        this.dutyRepository = dutyRepository;
        this.dutyChunkRepository = dutyChunkRepository;
        this.timeSlotService = timeSlotService;
    }


    public DutyDTO createDuty (DutyDTO dutyDTO) {

        Duty duty = new Duty();
        duty.setTaskName(dutyDTO.getTaskName());
        duty.setTaskTime(dutyDTO.getTaskTime());
        duty.setTaskColor(dutyDTO.getTaskColor());
        Duty createdDuty = dutyRepository.save(duty);

        int slotDurationMinutes = timeSlotService.getSlotDurationMinutes();
        int totalSlots = (int) Math.ceil((double) duty.getTaskTime() / slotDurationMinutes);
        int remainingMinutes = duty.getTaskTime();

        List<DutyChunk> dutyChunks = new ArrayList<>();
        for (int i = 0; i < totalSlots; i++) {
            DutyChunk dutyChunk = new DutyChunk();
            dutyChunk.setParentId(createdDuty.getId());
            dutyChunk.setChunkOrder(i + 1);
            int chunkDuration = Math.min(slotDurationMinutes, remainingMinutes);
            dutyChunk.setDuration(chunkDuration); //veritabanı icin gercek süre ayarlanması
            dutyChunks.add(dutyChunk);
            remainingMinutes -= chunkDuration;
        }
        dutyChunkRepository.saveAll(dutyChunks);

        return new DutyDTO(createdDuty);
    }

    public List<Duty> getAllDuties() {
        return dutyRepository.findAll();
    }

    public void deleteDuty (Long id) {
        Duty duty = dutyRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException( id + "id'li görev bulunamadı"));
        dutyChunkRepository.findAll().stream()
                .filter(chunk -> chunk.getParentId().equals(id))
                .forEach(chunk -> dutyChunkRepository.deleteById(chunk.getId()));
        dutyRepository.deleteById(id);
    }


}
