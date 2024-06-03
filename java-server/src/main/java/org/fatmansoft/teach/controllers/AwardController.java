package org.fatmansoft.teach.controllers;

import org.fatmansoft.teach.models.Award;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.payload.request.DataRequest;
import org.fatmansoft.teach.payload.response.DataResponse;
import org.fatmansoft.teach.payload.response.OptionItem;
import org.fatmansoft.teach.payload.response.OptionItemList;
import org.fatmansoft.teach.repository.AwardRepository;
import org.fatmansoft.teach.repository.StudentRepository;
import org.fatmansoft.teach.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/award")

public class AwardController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AwardRepository awardRepository;

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

    @PostMapping("/getAwardList")
    public DataResponse getAwardList(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        if(studentId == null)
            studentId = 0;
        List<Award> sList = awardRepository.findByStudentId(studentId);  //数据库查询操作
        List dataList = new ArrayList();
        Map m;
        for (Award s : sList) {
            m = new HashMap();
            m.put("awardId", s.getAwardId()+"");
            m.put("studentId",s.getStudent().getStudentId()+"");
            m.put("stuNum",s.getStudent().getPerson().getNum());
            m.put("stuName",s.getStudent().getPerson().getName());
            m.put("awardName",s.getAwardName());
            m.put("awardTime",s.getAwardTime());
            dataList.add(m);
        }
        return CommonMethod.getReturnData(dataList);
    }

    @PostMapping("/awardSave")
    public DataResponse awardSave(@Valid @RequestBody DataRequest dataRequest) {
        Integer studentId = dataRequest.getInteger("studentId");
        Integer awardId = dataRequest.getInteger("awardId");
        String name = dataRequest.getString("awardName");
        String time = dataRequest.getString("awardTime");
        Optional<Award> op;
        Award s = null;
        if(awardId != null) {
            op= awardRepository.findById(awardId);
            if(op.isPresent())
                s = op.get();
        }
        if(s == null) {
            s = new Award();
            s.setStudent(studentRepository.findById(studentId).get());
        }
        s.setAwardTime(time);
        s.setAwardName(name);
        awardRepository.save(s);
        return CommonMethod.getReturnMessageOK();
    }

    @PostMapping("/awardDelete")
    public DataResponse awardDelete(@Valid @RequestBody DataRequest dataRequest) {
        Integer awardId = dataRequest.getInteger("awardId");
        Optional<Award> op;
        Award s = null;
        if(awardId != null) {
            op= awardRepository.findById(awardId);
            if(op.isPresent()) {
                s = op.get();
                awardRepository.delete(s);
            }
        }
        return CommonMethod.getReturnMessageOK();
    }
}
