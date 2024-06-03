package org.fatmansoft.teach.service;

import org.fatmansoft.teach.models.Person;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.util.ComDataUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {
    public Map getMapFromPerson(Person s) {
        Map m = new HashMap();
        if(s == null)
            return m;
        m.put("personId", s.getPersonId());
        m.put("num",s.getNum());
        m.put("name",s.getName());
        m.put("introduce",s.getIntroduce());
        return m;
    }

}
