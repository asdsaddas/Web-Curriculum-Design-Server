package org.fatmansoft.teach.repository;
import org.fatmansoft.teach.models.Award;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
@Repository
public interface AwardRepository extends JpaRepository<Award,Integer>{
    @Query(value="from Award where (?1=0 or student.studentId=?1)" )
    List<Award> findByStudentId(Integer studentId);
}
