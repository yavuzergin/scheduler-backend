package org.yavuz.schedulerbackend.dutychunks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yavuz.schedulerbackend.duties.model.Duty;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;

import java.util.List;

public interface DutyChunkRepository extends JpaRepository<DutyChunk, Long> {
    List<DutyChunk> findByTimeSlotsId(Long timeSlotsId);
    List<DutyChunk> findByParentId(Long parentId);
}
