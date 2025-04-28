package org.yavuz.schedulerbackend.duties.DTO;

import lombok.Data;
import org.yavuz.schedulerbackend.duties.model.Duty;

@Data
public class DutyDTO {
    private Long id;
    private String taskName;
    private int taskTime;
    private String taskColor;

    public DutyDTO(Duty duty){
        this.id = duty.getId();
        this.taskName = duty.getTaskName();
        this.taskColor = duty.getTaskColor();
        this.taskTime = duty.getTaskTime();
    }

}


