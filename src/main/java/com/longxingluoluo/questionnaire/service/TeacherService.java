package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.TeacherDao;
import com.longxingluoluo.questionnaire.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/17 下午 11:53
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

    public Teacher addNewByName(Teacher teacher) {
        teacher.setId(null);
        if (teacher.name == null || teacher.name.trim().equals("")) {
            return null;
        }
        return teacherDao.save(teacher);
    }

    public void deleteById(Long id) {
        teacherDao.deleteById(id);
    }

    public boolean existsById(Teacher teacher) {
        return teacher != null && teacherDao.existsById(teacher.id);
    }
}
