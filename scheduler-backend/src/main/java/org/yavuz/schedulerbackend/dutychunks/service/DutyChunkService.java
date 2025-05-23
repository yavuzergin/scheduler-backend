package org.yavuz.schedulerbackend.dutychunks.service;

import org.springframework.stereotype.Service;
import org.yavuz.schedulerbackend.dutychunks.DTO.DutyChunkDTO;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;
import org.yavuz.schedulerbackend.dutychunks.repository.DutyChunkRepository;
import org.yavuz.schedulerbackend.exception.ResourceNotFoundException;
import org.yavuz.schedulerbackend.timeslot.model.TimeSlot;
import org.yavuz.schedulerbackend.timeslot.repository.TimeSlotRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DutyChunkService {
    private final DutyChunkRepository dutyChunkRepository;
    private final TimeSlotRepository timeSlotRepository;

    public  DutyChunkService(DutyChunkRepository dutyChunkRepository, TimeSlotRepository timeSlotRepository) {
        this.dutyChunkRepository = dutyChunkRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public DutyChunkDTO assignDutyToTimeSlot(Long parentId, Long timeSlotsId, int order) {
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotsId)
                .orElseThrow(() -> new ResourceNotFoundException("Zaman dilimi bulunamadı."));
        if (!timeSlot.getIsAvailable()) {
            throw new IllegalStateException("Bu zaman dilimi müsait değil.");
        }

        DutyChunk dutyChunk = new DutyChunk();
        dutyChunk.setParentId(parentId);
        dutyChunk.setTimeSlotsId(timeSlotsId);
        dutyChunk.setChunkOrder(order);
        DutyChunk savedDutyChunk = dutyChunkRepository.save(dutyChunk);
        return new DutyChunkDTO(savedDutyChunk);
    }

    //deleteById kullanılabilir?
    public void deleteDutyChunkFromTimeSlot(Long timeSlotsId) {
        dutyChunkRepository.deleteAll(dutyChunkRepository.findByTimeSlotsId(timeSlotsId));
    }

    public List<DutyChunkDTO> listDutyChunks() {
        List<DutyChunk> dutyChunks = dutyChunkRepository.findAll();
        return dutyChunks.stream().map(DutyChunkDTO::new).collect(Collectors.toList());
    }

}
