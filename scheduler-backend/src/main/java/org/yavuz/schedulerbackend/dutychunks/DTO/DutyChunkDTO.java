package org.yavuz.schedulerbackend.dutychunks.DTO;

import lombok.Data;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;

@Data
public class DutyChunkDTO {
    private Long id;
    private Long parentId;
    private Long timeSlotsId;
    private int chunkOrder;
    private int duration;


    public DutyChunkDTO() {

    }

    public DutyChunkDTO(DutyChunk dutyChunk){
        this.id = dutyChunk.getId();
        this.parentId = dutyChunk.getParentId();
        this.timeSlotsId = dutyChunk.getTimeSlotsId();
        this.chunkOrder = dutyChunk.getChunkOrder();
        this.duration = dutyChunk.getDuration();

    }

}
