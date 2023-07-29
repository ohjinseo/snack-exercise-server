package com.soma.snackexercise.repository.mission;

import com.soma.snackexercise.domain.member.Member;
import com.soma.snackexercise.domain.mission.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    void deleteByMember(Member member);

    @Query("SELECT count(*) AS cnt" +
            "    FROM Mission m" +
            "    WHERE m.exgroup.id = :exgroupId AND :today <= m.createdAt AND m.createdAt < :nextday AND m.endAt IS NOT NULL" +
            "    GROUP BY m.member" +
            "    ORDER BY cnt DESC" +
            "    LIMIT 1")
    Integer findCurrentFinishedRelayCountByGroupId(@Param("exgroupId") Long exgroupId, @Param("today") LocalDateTime today, @Param("nextday") LocalDateTime nextday);

    @Query("SELECT m" +
            "   FROM Mission m JOIN FETCH m.member" +
            "   WHERE m.exgroup.id = :exgroupId AND :today <= m.createdAt AND m.createdAt < :nextday" +
            "   ORDER BY m.createdAt")
    List<Mission> findMissionsByGroupIdWithinDateRange(@Param("exgroupId") Long exgroupId, @Param("today") LocalDateTime today, @Param("nextday") LocalDateTime nextday);

    @Query("SELECT m" +
            "   FROM Mission m JOIN FETCH m.member" +
            "   WHERE m.exgroup.id = :exgroupId AND :today <= m.createdAt AND m.createdAt < :nextday AND m.startAt IS NOT NULL" +
            "   ORDER BY m.createdAt")
    List<Mission> findExecutedMissionsByGroupIdWithinDateRange(@Param("exgroupId") Long exgroupId, @Param("today") LocalDateTime today, @Param("nextday") LocalDateTime nextday);
}
