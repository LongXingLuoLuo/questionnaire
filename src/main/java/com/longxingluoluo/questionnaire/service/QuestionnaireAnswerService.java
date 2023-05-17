package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.*;
import com.longxingluoluo.questionnaire.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/17 下午 09:01
 * QuestionnaireAnswer 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class QuestionnaireAnswerService {
    @Resource
    QuestionnaireAnswerDao questionnaireAnswerDao;
    @Resource
    QuestionnaireDao questionnaireDao;
    @Resource
    ProfessionalDao professionalDao;
    @Resource
    GradeDao gradeDao;
    @Resource
    CurriculumDao curriculumDao;
    @Resource
    TeacherDao teacherDao;

    public QuestionnaireAnswer findById(Long id) {
        return questionnaireAnswerDao.findById(id);
    }

    public List<QuestionnaireAnswer> findAll() {
        return questionnaireAnswerDao.findAll();
    }

    public List<QuestionnaireAnswer> findAllByQuestionnaire(Questionnaire questionnaire) {
        return questionnaireAnswerDao.findAllByQuestionnaire(questionnaire);
    }

    public List<QuestionnaireAnswer> findAllByProfessional(Professional professional) {
        return questionnaireAnswerDao.findAllByProfessional(professional);
    }

    public List<QuestionnaireAnswer> findAllByGrade(Grade grade) {
        return questionnaireAnswerDao.findAllByGrade(grade);
    }

    public QuestionnaireAnswer addNewByAll(Questionnaire questionnaire, Grade grade, Professional professional, List<CurriculumEvaluation> curriculumEvaluationList,
                                           List<TeacherEvaluation> teacherEvaluationList, int selfEvaluation) {
        if (questionnaire != null && !questionnaireDao.existsById(questionnaire.id)) {
            return null;
        }
        if (grade != null && !gradeDao.existsById(grade.id)) {
            return null;
        }
        if (professional != null && !professionalDao.existsById(professional.id)) {
            return null;
        }
        for (CurriculumEvaluation curriculumEvaluation : curriculumEvaluationList) {
            if (curriculumEvaluation == null || curriculumEvaluation.curriculum == null || !curriculumDao.existsById(curriculumEvaluation.curriculum.id)) {
                return null;
            }
            curriculumEvaluation.setId(null);
        }
        for (TeacherEvaluation teacherEvaluation : teacherEvaluationList) {
            if (teacherEvaluation == null || teacherEvaluation.teacher == null || !teacherDao.existsById(teacherEvaluation.teacher.id)) {
                return null;
            }
            teacherEvaluation.setId(null);
        }
        QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();
        questionnaireAnswer.setQuestionnaire(questionnaire);
        questionnaireAnswer.setGrade(grade);
        questionnaireAnswer.setProfessional(professional);
        questionnaireAnswer.setCurriculumEvaluationList(curriculumEvaluationList);
        questionnaireAnswer.setTeacherEvaluationList(teacherEvaluationList);
        return questionnaireAnswerDao.save(questionnaireAnswer);
    }

    public void deleteById(Long id) {
        questionnaireAnswerDao.deleteById(id);
    }
}
