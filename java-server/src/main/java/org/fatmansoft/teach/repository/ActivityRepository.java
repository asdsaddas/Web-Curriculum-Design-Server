package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Activity 数据操作接口，主要实现Activity数据的查询操作
 * List<Activity> findByStudentStudentId(Integer studentId);  根据关联的Student的student_id查询获得List<Activity>对象集合,  命名规范
 */

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {
    @Query(value="from Activity where (?1=0 or student.studentId=?1)")
    List<Activity> findByStudent(Integer studentId);


}
