package org.fatmansoft.teach.repository;
import org.fatmansoft.teach.models.Fit;
import org.fatmansoft.teach.models.Fit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FitRepository extends JpaRepository<Fit,Integer> {

    @Query(value="from Fit where (?1=0 or student.studentId=?1) and (?2=0 or course.courseId=?2)")
    List<Fit> findByStudentCourse(Integer studentId,Integer courseId);




}
