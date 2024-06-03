package org.fatmansoft.teach.models;

import javax.persistence.*;
@Entity
@Table(	name = "award",
        uniqueConstraints = {
        })
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer awardId;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String awardName;
    private String awardTime;

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }
}

