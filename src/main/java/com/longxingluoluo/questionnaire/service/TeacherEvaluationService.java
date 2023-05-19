package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.TeacherDao;
import com.longxingluoluo.questionnaire.dao.TeacherEvaluationDao;
import com.longxingluoluo.questionnaire.entity.Teacher;
import com.longxingluoluo.questionnaire.entity.TeacherEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TeacherEvaluation 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class TeacherEvaluationService {
    @Resource
    TeacherEvaluationDao teacherEvaluationDao;
    @Resource
    TeacherDao teacherDao;

    public TeacherEvaluation findById(Long id){
        return teacherEvaluationDao.findById(id);
    }

    public List<TeacherEvaluation> findAll(){
        return teacherEvaluationDao.findAll();
    }

    public List<TeacherEvaluation> findAllByTeacher(Teacher teacher){
        return teacherEvaluationDao.findAllByTeacher(teacher);
    }

    public TeacherEvaluation addNewByTeacherAndEvaluation(Teacher teacher, int evaluation){
        if (teacher == null || !teacherDao.existsById(teacher.id)){
            return null;
        }
        TeacherEvaluation teacherEvaluation = new TeacherEvaluation();
        teacherEvaluation.setEvaluation(evaluation);
        teacherEvaluation.setTeacher(teacher);
        return teacherEvaluationDao.save(teacherEvaluation);
    }

    public void deleteById(Long id){
        teacherEvaluationDao.deleteById(id);
    }
}
