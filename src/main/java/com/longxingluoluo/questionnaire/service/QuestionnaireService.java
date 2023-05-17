package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.*;
import com.longxingluoluo.questionnaire.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/17 下午 11:24
 *
 * @author 龙星洛洛
 */
@Service
public class QuestionnaireService {
    @Resource
    QuestionnaireDao questionnaireDao;
    @Resource
    GradeDao gradeDao;
    @Resource
    ProfessionalDao professionalDao;
    @Resource
    CurriculumDao curriculumDao;
    @Resource
    TeacherDao teacherDao;

    public Questionnaire findById(Long id) {
        return questionnaireDao.findById(id);
    }

    public List<Questionnaire> findAll() {
        return questionnaireDao.findAll();
    }

    public Questionnaire addNewByAll(String name, List<Grade> gradeList, List<Professional> professionalList,
                                List<Curriculum> curriculumList, List<Teacher> teacherList) {
        if (name == null || name.trim().equals("")){
            return null;
        }
        for (Grade grade: gradeList){
            if (grade == null || !gradeDao.existsById(grade.id)){
                return null;
            }
        }
        for (Professional professional: professionalList){
            if (professional == null || !professionalDao.existsById(professional.id)){
                return null;
            }
        }
        for (Curriculum curriculum: curriculumList){
            if (curriculum == null || !curriculumDao.existsById(curriculum.id)){
                return null;
            }
        }
        for (Teacher teacher: teacherList){
            if (teacher == null || !teacherDao.existsById(teacher.id)){
                return null;
            }
        }
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setGradeList(gradeList);
        questionnaire.setProfessionalList(professionalList);
        questionnaire.setCurriculumList(curriculumList);
        questionnaire.setTeacherList(teacherList);
        return questionnaireDao.save(questionnaire);
    }
}
