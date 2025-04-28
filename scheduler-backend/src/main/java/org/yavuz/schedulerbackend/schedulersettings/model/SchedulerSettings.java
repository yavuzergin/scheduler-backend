package org.yavuz.schedulerbackend.schedulersettings.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "scheduler_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "total_duration_minutes")
    private int totalDurationMinutes;
    @Column (name = "start_hour")
    private int startHour;
    @Column (name = "end_hour")
    private int endHour;
}
