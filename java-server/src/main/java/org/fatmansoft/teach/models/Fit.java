package org.fatmansoft.teach.models;

import javax.persistence.*;
/**
 * Fit 成绩表实体类  保存成绩的的基本信息信息，
 * Integer fitId 人员表 fit 主键 fit_id
 * Student student 关联学生 student_id 关联学生的主键 student_id
 * Course course 关联课程 course_id 关联课程的主键 course_id
 * Integer mark 成绩
 * Integer ranking 排名
 */
@Entity
@Table(	name = "fit",
        uniqueConstraints = {
        })
public class Fit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fitId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Integer getFitId() {
        return fitId;
    }

    public void setFitId(Integer fitId) {
        this.fitId = fitId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}