package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Student学生表实体类 保存每个学生的信息，
 * Integer studentId 用户表 student 主键 student_id
 * Person person 关联到该用户所用的Person对象，账户所对应的人员信息 person_id 关联 person 表主键 person_id
 * String major 专业
 * String className 班级
 *
 */
@Entity
@Table(	name = "teacher",
        uniqueConstraints = {
        })
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @OneToOne
    @JoinColumn(name="person_id")
    private Person person;

    @Size(max = 20)
    private String interest;

    @Size(max = 20)
    private String publication;
    @Size(max = 20)
    private String tuition;


    public Teacher() {
    }

    public Teacher(Integer teacherId, Person person, String interest, String publication, String tuition) {
        this.teacherId = teacherId;
        this.person = person;
        this.interest = interest;
        this.publication = publication;
        this.tuition = tuition;
    }

    /**
     * 获取
     * @return teacherId
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取
     * @return person
     */
    public Person getPerson() {
        return person;
    }

    /**
     * 设置
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 获取
     * @return interest
     */
    public String getInterest() {
        return interest;
    }

    /**
     * 设置
     * @param interest
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * 获取
     * @return publication
     */
    public String getPublication() {
        return publication;
    }

    /**
     * 设置
     * @param publication
     */
    public void setPublication(String publication) {
        this.publication = publication;
    }

    /**
     * 获取
     * @return tuition
     */
    public String getTuition() {
        return tuition;
    }

    /**
     * 设置
     * @param tuition
     */
    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String toString() {
        return "Teacher{teacherId = " + teacherId + ", person = " + person + ", interest = " + interest + ", publication = " + publication + ", tuition = " + tuition + "}";
    }
}
