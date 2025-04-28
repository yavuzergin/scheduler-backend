package org.yavuz.schedulerbackend.duties.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.yavuz.schedulerbackend.duties.model.Duty;

@Data
public class DutyDTO {
    private Long id;

    @JsonProperty("task_name")
    private String taskName;

    @JsonProperty("task_time")
    private int taskTime;

    @JsonProperty("color")
    private String taskColor;

    public DutyDTO() {
    }

    public DutyDTO(Duty duty) {
        this.id = duty.getId();
        this.taskName = duty.getTaskName();
        this.taskTime = duty.getTaskTime();
        this.taskColor = duty.getTaskColor();
    }
}