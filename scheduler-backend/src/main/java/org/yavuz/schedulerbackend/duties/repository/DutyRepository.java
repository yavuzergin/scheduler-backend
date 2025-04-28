package org.yavuz.schedulerbackend.duties.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yavuz.schedulerbackend.duties.model.Duty;
@Repository
public interface DutyRepository extends JpaRepository<Duty, Long>{
}
