package org.yavuz.schedulerbackend.dutychunks.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task_chunks")
@Data
public class DutyChunk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "time_slots_id")
    private Long timeSlotsId;
    @Column(name = "order")
    private int order;
}
