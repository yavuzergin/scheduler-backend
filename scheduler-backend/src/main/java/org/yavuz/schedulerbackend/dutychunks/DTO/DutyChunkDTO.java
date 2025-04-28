package org.yavuz.schedulerbackend.dutychunks.DTO;

import lombok.Data;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;

@Data
public class DutyChunkDTO {
    private Long id;
    private Long parentId;
    private Long timeSlotsId;
    private int order;

    public DutyChunkDTO(DutyChunk dutyChunk){
        this.id = dutyChunk.getId();
        this.parentId = dutyChunk.getId();
        this.timeSlotsId = dutyChunk.getTimeSlotsId();
        this.order = dutyChunk.getOrder();

    }

}
