package org.yavuz.schedulerbackend.dutychunks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.schedulerbackend.dutychunks.model.DutyChunk;

import java.util.List;
@Repository
public interface DutyChunkRepository extends JpaRepository<DutyChunk, Long> {
    List<DutyChunk> findByTimeSlotsId(Long timeSlotsId);
}
