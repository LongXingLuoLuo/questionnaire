package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.*;
import com.longxingluoluo.questionnaire.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
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


    @Transactional(readOnly = true)
    public QuestionnaireAnswer findById(Long id) {
        return questionnaireAnswerDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<QuestionnaireAnswer> findAll() {
        return questionnaireAnswerDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<QuestionnaireAnswer> findAllByQuestionnaire(Questionnaire questionnaire) {
        return questionnaireAnswerDao.findAllByQuestionnaire(questionnaire);
    }

    @Transactional(readOnly = true)
    public List<QuestionnaireAnswer> findAllByProfessional(Professional professional) {
        return questionnaireAnswerDao.findAllByProfessional(professional);
    }

    @Transactional(readOnly = true)
    public List<QuestionnaireAnswer> findAllByGrade(Grade grade) {
        return questionnaireAnswerDao.findAllByGrade(grade);
    }

    public QuestionnaireAnswer addNewByAll(Questionnaire questionnaire, Grade grade, Professional professional, List<CurriculumEvaluation> curriculumEvaluationList,
                                           List<TeacherEvaluation> teacherEvaluationList, int selfEvaluation) {
        QuestionnaireAnswer questionnaireAnswer = new QuestionnaireAnswer();

        if (questionnaire == null || !questionnaireDao.existsById(questionnaire.id)) {
            return null;
        }
        questionnaireAnswer.setQuestionnaire(questionnaire);

        if (grade != null && grade.id != null) {
            if (gradeDao.existsById(grade.id)) {
                questionnaireAnswer.setGrade(grade);
            } else {
                return null;
            }
        } else {
            questionnaireAnswer.setGrade(null);
        }

        if (professional != null && professional.id != null) {
            if (professionalDao.existsById(professional.id)) {
                questionnaireAnswer.setProfessional(professional);
            } else {
                return null;
            }
        } else {
            questionnaireAnswer.setProfessional(null);
        }

        for (CurriculumEvaluation curriculumEvaluation : curriculumEvaluationList) {
            if (curriculumEvaluation == null || curriculumEvaluation.curriculum == null || !curriculumDao.existsById(curriculumEvaluation.curriculum.id)) {
                return null;
            }
            curriculumEvaluation.setId(null);
        }
        questionnaireAnswer.setCurriculumEvaluationList(curriculumEvaluationList);

        for (TeacherEvaluation teacherEvaluation : teacherEvaluationList) {
            if (teacherEvaluation == null || teacherEvaluation.teacher == null || !teacherDao.existsById(teacherEvaluation.teacher.id)) {
                return null;
            }
            teacherEvaluation.setId(null);
        }
        questionnaireAnswer.setTeacherEvaluationList(teacherEvaluationList);
        questionnaireAnswer.setSelfEvaluation(selfEvaluation);
        questionnaireAnswer = questionnaireAnswerDao.save(questionnaireAnswer);
        for (CurriculumEvaluation curriculumEvaluation :
                questionnaireAnswer.getCurriculumEvaluationList()
        ) {
            curriculumEvaluation.setQuestionnaireAnswer(questionnaireAnswer);
        }
        for (TeacherEvaluation teacherEvaluation :
                questionnaireAnswer.getTeacherEvaluationList()) {
            teacherEvaluation.setQuestionnaireAnswer(questionnaireAnswer);
        }
        return questionnaireAnswerDao.save(questionnaireAnswer);
    }

    public void deleteById(Long id) {
        questionnaireAnswerDao.deleteById(id);
    }
}
