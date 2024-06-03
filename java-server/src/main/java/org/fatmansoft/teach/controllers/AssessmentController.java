package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.payload.response.OptionItem;
import org.fatmansoft.teach.payload.response.OptionItemList;
import org.fatmansoft.teach.repository.CourseRepository;
import org.fatmansoft.teach.repository.AssessmentRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @PostMapping("/getStudentItemOptionList")
    public OptionItemList getStudentItemOptionList(@Valid @RequestBody DataRequest dataRequest) {
        List<Student> sList = studentRepository.findStudentListByNumName("");  //数据库查询操作
        OptionItem item;
        List<OptionItem> itemList = new ArrayList();
        for (Student s : sList) {
            itemList.add(new OptionItem( s.getStudentId(),s.getStudentId()+"", s.getPerson().getNum()+"-"+s.getPerson().getName()));
        }
        return new OptionItemList(0, itemList);
    }
    @PostMapping("/getAssessmentList")
    public DataResponse getAssessmentList(@Valid @RequestBody DataRequest dataRequest) {
        Integer student1Id = dataRequest.getInteger("student1Id");
        Integer student2Id = dataRequest.getInteger("student2Id");
        if(student1Id == null)
            student1Id = 0;
        if(student2Id == null)
            student2Id = 0;
        List<Assessment> sList = assessmentRepository.findByStudent(student1Id,student2Id);  //数据库查询操作
        List dataList = new ArrayList();
        Map m;
        for (Assessment s : sList) {
            m = new HashMap();
            m.put("assessmentId", s.getAssessmentId()+"");
            m.put("stu1Num",s.getStudent1().getPerson().getNum());
            m.put("stu1Name",s.getStudent1().getPerson().getName());
            m.put("stu2Num",s.getStudent2().getPerson().getNum());
            m.put("stu2Name",s.getStudent2().getPerson().getName());
            m.put("content", s.getContent()+"");
            dataList.add(m);
        }
        return CommonMethod.getReturnData(dataList);
    }
    @PostMapping("/assessmentSave")
    public DataResponse assessmentSave(@Valid @RequestBody DataRequest dataRequest) {
        Integer student1Id = dataRequest.getInteger("student1Id");
        Integer student2Id = dataRequest.getInteger("student2Id");
        Integer assessmentId = dataRequest.getInteger("assessmentId");
        String content = dataRequest.getString("content");

        Optional<Assessment> op;
        Assessment s = null;
        if(assessmentId != null) {
            op= assessmentRepository.findById(assessmentId);
            if(op.isPresent())
                s = op.get();
        }
        if(s == null) {
            s = new Assessment();
            s.setStudent1(studentRepository.findById(student1Id).get());
            s.setStudent2(studentRepository.findById(student2Id).get());
        }
        s.setContent(content);
        assessmentRepository.save(s);
        return CommonMethod.getReturnMessageOK();
    }
    @PostMapping("/assessmentDelete")
    public DataResponse assessmentDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer assessmentId = dataRequest.getInteger("assessmentId");
        Optional<Assessment> op;
        Assessment s = null;
        if(assessmentId != null) {
            op= assessmentRepository.findById(assessmentId);
            if(op.isPresent()) {
                s = op.get();
                assessmentRepository.delete(s);
            }
        }
        return CommonMethod.getReturnMessageOK();
    }
}