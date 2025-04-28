package org.yavuz.schedulerbackend.timeslot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "timeslots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private String day;
    @Column(name = "start_time")
    private int startTime;
    @Column(name = "end_time")
    private int endTime;
    @Column(name = "is_available")
    private Boolean isAvailable;

}
