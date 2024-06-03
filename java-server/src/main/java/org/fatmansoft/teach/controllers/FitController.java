package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.payload.response.OptionItem;
import org.fatmansoft.teach.payload.response.OptionItemList;
import org.fatmansoft.teach.repository.CourseRepository;
import org.fatmansoft.teach.repository.FitRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fit")
public class FitController {
    @Autowired
    private FitRepository fitRepository;
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

    @PostMapping("/getCourseItemOptionList")
    public OptionItemList getCourseItemOptionList(@Valid @RequestBody DataRequest dataRequest) {
        List<Course> sList = courseRepository.findAll();  //数据库查询操作
        OptionItem item;
        List<OptionItem> itemList = new ArrayList();
        for (Course c : sList) {
            itemList.add(new OptionItem(c.getCourseId(),c.getCourseId()+"", c.getNum()+"-"+c.getName()));
        }
        return new OptionItemList(0, itemList);
    }
    @PostMapping("/getFitList")
    public DataResponse getFitList(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        if(studentId == null)
            studentId = 0;
        Integer courseId = dataRequest.getInteger("courseId");
        if(courseId == null)
            courseId = 0;
        List<Fit> sList = fitRepository.findByStudentCourse(studentId,courseId);  //数据库查询操作
        List dataList = new ArrayList();
        Map m;
        for (Fit s : sList) {
            m = new HashMap();
            m.put("fitId", s.getFitId()+"");
            m.put("stuNum",s.getStudent().getPerson().getNum());
            m.put("stuName",s.getStudent().getPerson().getName());
            m.put("courseId",s.getCourse().getCourseId()+"");
            m.put("courseNum",s.getCourse().getNum());
            m.put("courseName",s.getCourse().getName());
            m.put("courseCredit",+s.getCourse().getCredit());
            dataList.add(m);
        }
        return CommonMethod.getReturnData(dataList);
    }
    @PostMapping("/fitSave")
    public DataResponse fitSave(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        Integer courseId = dataRequest.getInteger("courseId");
        Integer fitId = dataRequest.getInteger("fitId");
        Optional<Fit> op;
        Fit s = null;
        if(fitId != null) {
            op= fitRepository.findById(fitId);
            if(op.isPresent())
                s = op.get();
        }
        if(s == null) {
            s = new Fit();
            s.setStudent(studentRepository.findById(studentId).get());
            s.setCourse(courseRepository.findById(courseId).get());
        }
        fitRepository.save(s);
        return CommonMethod.getReturnMessageOK();
    }
    @PostMapping("/fitDelete")
    public DataResponse fitDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer fitId = dataRequest.getInteger("fitId");
        Optional<Fit> op;
        Fit s = null;
        if(fitId != null) {
            op= fitRepository.findById(fitId);
            if(op.isPresent()) {
                s = op.get();
                fitRepository.delete(s);
            }
        }
        return CommonMethod.getReturnMessageOK();
    }
}