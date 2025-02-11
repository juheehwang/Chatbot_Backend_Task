package com.task.backend.repository;

import com.task.backend.entity.MemberActivity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberActivityRepository extends JpaRepository<MemberActivity,Long> {

    List<MemberActivity> findByTimestampBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
