package org.yavuz.schedulerbackend.schedulersettings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.schedulerbackend.schedulersettings.model.SchedulerSettings;

@Repository
public interface SchedulerSettingsRepository extends JpaRepository<SchedulerSettings, Long> {
}
