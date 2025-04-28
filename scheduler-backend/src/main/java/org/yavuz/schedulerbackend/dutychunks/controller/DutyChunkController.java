package org.yavuz.schedulerbackend.dutychunks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yavuz.schedulerbackend.dutychunks.DTO.DutyChunkDTO;
import org.yavuz.schedulerbackend.dutychunks.service.DutyChunkService;

import java.util.List;

@RestController
@RequestMapping("/dutychunks")
public class DutyChunkController {
    private final DutyChunkService dutyChunkService;
    public DutyChunkController(DutyChunkService dutyChunkService){
        this.dutyChunkService = dutyChunkService;
    }

    @PostMapping("/assign-duty-chunk")
    public DutyChunkDTO assignDutyToTimeSlot(@RequestBody DutyChunkDTO dutyChunkDTO){
        return dutyChunkService.assignDutyToTimeSlot(dutyChunkDTO.getParentId(), dutyChunkDTO.getTimeSlotsId(), dutyChunkDTO.getChunkOrder());
    }

    @DeleteMapping("/delete/{timeSlotsId}")
    public ResponseEntity<Void> deleteDutyChunkFromTimeSlot(@PathVariable Long timeSlotsId) {
        dutyChunkService.deleteDutyChunkFromTimeSlot(timeSlotsId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/list")
    public ResponseEntity<List<DutyChunkDTO>> listDutyChunks() {
        List<DutyChunkDTO> dutyChunks = dutyChunkService.listDutyChunks();
        return ResponseEntity.ok(dutyChunks);
    }



}
