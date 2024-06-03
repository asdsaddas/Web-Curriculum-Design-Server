package org.fatmansoft.teach.models;
import javax.persistence.*;
@Entity
@Table(name="assessment",
        uniqueConstraints = {
        })
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assessmentId;
    @ManyToOne
    @JoinColumn(name = "student1_id")
    private Student student1;
    @ManyToOne
    @JoinColumn(name = "student2_id")
    private Student student2;
    private String content;

    public Assessment() {
    }

    public Assessment(Integer assessmentId, Student student1, Student student2, String content) {
        this.assessmentId = assessmentId;
        this.student1 = student1;
        this.student2 = student2;
        this.content = content;
    }

    /**
     * 获取
     * @return assessmentId
     */
    public Integer getAssessmentId() {
        return assessmentId;
    }

    /**
     * 设置
     * @param assessmentId
     */
    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    /**
     * 获取
     * @return student1
     */
    public Student getStudent1() {
        return student1;
    }

    /**
     * 设置
     * @param student1
     */
    public void setStudent1(Student student1) {
        this.student1 = student1;
    }

    /**
     * 获取
     * @return student2
     */
    public Student getStudent2() {
        return student2;
    }

    /**
     * 设置
     * @param student2
     */
    public void setStudent2(Student student2) {
        this.student2 = student2;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Assessment{assessmentId = " + assessmentId + ", student1 = " + student1 + ", student2 = " + student2 + ", content = " + content + "}";
    }
}
