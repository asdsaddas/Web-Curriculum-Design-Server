package org.fatmansoft.teach.models;
import javax.persistence.*;
@Entity
@Table(name="activity",
        uniqueConstraints = {
        })
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    private String name;
    private String date;
    private String profile;

    public Activity() {
    }

    public Activity(Integer activityId, Student student, String name, String date, String profile) {
        this.activityId = activityId;
        this.student = student;
        this.name = name;
        this.date = date;
        this.profile = profile;
    }

    /**
     * 获取
     * @return activityId
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 设置
     * @param activityId
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取
     * @return student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * 设置
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 获取
     * @return profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 设置
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String toString() {
        return "Activity{activityId = " + activityId + ", student = " + student + ", name = " + name + ", date = " + date + ", profile = " + profile + "}";
    }
}
