package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.payload.response.OptionItem;
import org.fatmansoft.teach.payload.response.OptionItemList;
import org.fatmansoft.teach.repository.CourseRepository;
import org.fatmansoft.teach.repository.ActivityRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;
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
    @PostMapping("/getActivityList")
    public DataResponse getActivityList(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        if(studentId == null)
            studentId = 0;
        List<Activity> sList = activityRepository.findByStudent(studentId);  //数据库查询操作
        List dataList = new ArrayList();
        Map m;
        for (Activity s : sList) {
            m = new HashMap();
            m.put("activityId", s.getActivityId()+"");
            m.put("stuNum",s.getStudent().getPerson().getNum());
            m.put("stuName",s.getStudent().getPerson().getName());
            m.put("activityName", s.getName()+"");
            m.put("activityDate", s.getDate()+"");
            m.put("activityProfile", s.getProfile()+"");
            dataList.add(m);
        }
        return CommonMethod.getReturnData(dataList);
    }
    @PostMapping("/activitySave")
    public DataResponse activitySave(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        Integer activityId = dataRequest.getInteger("activityId");
        String activityName = dataRequest.getString("activityName");
        String activityDate = dataRequest.getString("activityDate");
        String activityProfile = dataRequest.getString("activityProfile");

        Optional<Activity> op;
        Activity s = null;
        if(activityId != null) {
            op= activityRepository.findById(activityId);
            if(op.isPresent())
                s = op.get();
        }
        if(s == null) {
            s = new Activity();
            s.setStudent(studentRepository.findById(studentId).get());
        }
        s.setName(activityName);
        s.setDate(activityDate);
        s.setProfile(activityProfile);
        activityRepository.save(s);
        return CommonMethod.getReturnMessageOK();
    }
    @PostMapping("/activityDelete")
    public DataResponse activityDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer activityId = dataRequest.getInteger("activityId");
        Optional<Activity> op;
        Activity s = null;
        if(activityId != null) {
            op= activityRepository.findById(activityId);
            if(op.isPresent()) {
                s = op.get();
                activityRepository.delete(s);
            }
        }
        return CommonMethod.getReturnMessageOK();
    }
}