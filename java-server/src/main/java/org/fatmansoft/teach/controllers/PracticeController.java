package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.*;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.payload.response.OptionItem;
import org.fatmansoft.teach.payload.response.OptionItemList;
import org.fatmansoft.teach.repository.CourseRepository;
import org.fatmansoft.teach.repository.PracticeRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/practice")
public class PracticeController {
    @Autowired
    private PracticeRepository practiceRepository;
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
    @PostMapping("/getPracticeList")
    public DataResponse getPracticeList(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        if(studentId == null)
            studentId = 0;
        List<Practice> sList = practiceRepository.findByStudent(studentId);  //数据库查询操作
        List dataList = new ArrayList();
        Map m;
        for (Practice s : sList) {
            m = new HashMap();
            m.put("practiceId", s.getPracticeId()+"");
            m.put("stuNum",s.getStudent().getPerson().getNum());
            m.put("stuName",s.getStudent().getPerson().getName());
            m.put("practiceName", s.getName()+"");
            m.put("practiceDate", s.getDate()+"");
            m.put("practiceProfile", s.getProfile()+"");
            dataList.add(m);
        }
        return CommonMethod.getReturnData(dataList);
    }
    @PostMapping("/practiceSave")
    public DataResponse practiceSave(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        Integer practiceId = dataRequest.getInteger("practiceId");
        String practiceName = dataRequest.getString("practiceName");
        String practiceDate = dataRequest.getString("practiceDate");
        String practiceProfile = dataRequest.getString("practiceProfile");

        Optional<Practice> op;
        Practice s = null;
        if(practiceId != null) {
            op= practiceRepository.findById(practiceId);
            if(op.isPresent())
                s = op.get();
        }
        if(s == null) {
            s = new Practice();
            s.setStudent(studentRepository.findById(studentId).get());
        }
        s.setName(practiceName);
        s.setDate(practiceDate);
        s.setProfile(practiceProfile);
        practiceRepository.save(s);
        return CommonMethod.getReturnMessageOK();
    }
    @PostMapping("/practiceDelete")
    public DataResponse practiceDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer practiceId = dataRequest.getInteger("practiceId");
        Optional<Practice> op;
        Practice s = null;
        if(practiceId != null) {
            op= practiceRepository.findById(practiceId);
            if(op.isPresent()) {
                s = op.get();
                practiceRepository.delete(s);
            }
        }
        return CommonMethod.getReturnMessageOK();
    }
}