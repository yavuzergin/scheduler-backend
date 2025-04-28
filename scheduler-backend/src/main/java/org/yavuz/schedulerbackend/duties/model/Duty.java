package org.yavuz.schedulerbackend.duties.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Data
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_time")
    private int taskTime;
    @Column(name = "color")
    private String taskColor;
}
