package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.*;
import com.longxingluoluo.questionnaire.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Questionnaire 的 service 层
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

    @Transactional(readOnly = true)
    public Questionnaire findById(Long id) {
        return questionnaireDao.findById(id);
    }

    @Transactional(readOnly = true)
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
        questionnaire.setId(null);
        questionnaire.setName(name);
        questionnaire.setGradeList(gradeList);
        questionnaire.setProfessionalList(professionalList);
        questionnaire.setCurriculumList(curriculumList);
        questionnaire.setTeacherList(teacherList);
        return questionnaireDao.save(questionnaire);
    }

    public void deleteById(Long id){
        questionnaireDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id){
        return questionnaireDao.existsById(id);
    }
}
