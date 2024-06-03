package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Practice 数据操作接口，主要实现Practice数据的查询操作
 * List<Practice> findByStudentStudentId(Integer studentId);  根据关联的Student的student_id查询获得List<Practice>对象集合,  命名规范
 */

@Repository
public interface PracticeRepository extends JpaRepository<Practice,Integer> {
    @Query(value="from Practice where (?1=0 or student.studentId=?1)")
    List<Practice> findByStudent(Integer studentId);


}
