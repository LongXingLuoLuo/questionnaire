package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.TeacherDao;
import com.longxingluoluo.questionnaire.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Teacher 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class TeacherService {
    @Resource
    TeacherDao teacherDao;

    public Teacher findById(Long id) {
        return teacherDao.findById(id);
    }

    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    public Teacher addNewByName(String name) {
        if (name == null || name.trim().equals("")) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setName(name);
        return teacherDao.save(teacher);
    }

    public void deleteById(Long id) {
        teacherDao.deleteById(id);
    }

    public boolean existsById(Teacher teacher) {
        return teacher != null && teacherDao.existsById(teacher.id);
    }
}
