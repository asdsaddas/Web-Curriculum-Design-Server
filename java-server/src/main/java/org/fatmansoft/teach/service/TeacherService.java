package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.Person;
import org.fatmansoft.teach.models.Teacher;
import org.fatmansoft.teach.util.ComDataUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {
    public Map getMapFromTeacher(Teacher s) {
        Map m = new HashMap();
        Person p;
        if(s == null)
            return m;
        m.put("teacherId", s.getTeacherId());
        m.put("interest", s.getInterest());
        m.put("publication", s.getPublication());
        m.put("tuition", s.getTuition());
        p = s.getPerson();
        if(p == null)
            return m;
        m.put("personId", p.getPersonId());
        m.put("name",p.getName());
        m.put("dept",p.getDept());
        m.put("gender",p.getGender());
        m.put("genderName", ComDataUtil.getInstance().getDictionaryLabelByValue("XBM", p.getGender())); //性别类型的值转换成数据类型名
        m.put("birthday", p.getBirthday());  //时间格式转换字符串
        m.put("phone",p.getPhone());
        m.put("address",p.getAddress());
        m.put("introduce",p.getIntroduce());
        return m;
    }

}
