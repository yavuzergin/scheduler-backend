package org.yavuz.schedulerbackend.duties.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yavuz.schedulerbackend.duties.DTO.DutyDTO;
import org.yavuz.schedulerbackend.duties.model.Duty;
import org.yavuz.schedulerbackend.duties.service.DutyService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/duties")
public class DutyController {
    private final DutyService dutyService;

    public DutyController (DutyService dutyService) {
        this.dutyService = dutyService;
    }

    @PostMapping("/create-duty")
    public ResponseEntity<DutyDTO> createDuty(@RequestBody DutyDTO dutyDTO){
        DutyDTO dutyCreated = dutyService.createDuty(dutyDTO);
        return ResponseEntity.ok(dutyCreated);
    }

    @GetMapping("/list-duties")
    public List<Duty> getAllDuties(){
        return dutyService.getAllDuties();
    }


    @DeleteMapping("/delete-duty/{id}")
    public ResponseEntity<Void> deleteDuty(@PathVariable Long id){
        dutyService.deleteDuty(id);
        return ResponseEntity.noContent().build();
    }
}
