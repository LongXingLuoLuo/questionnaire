package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.ProfessionalDao;
import com.longxingluoluo.questionnaire.dao.QuestionnaireAnswerDao;
import com.longxingluoluo.questionnaire.dao.QuestionnaireDao;
import com.longxingluoluo.questionnaire.entity.Professional;
import com.longxingluoluo.questionnaire.entity.Questionnaire;
import com.longxingluoluo.questionnaire.entity.QuestionnaireAnswer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Professional 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class ProfessionalService {
    @Resource
    ProfessionalDao professionalDao;
    @Resource
    QuestionnaireAnswerDao questionnaireAnswerDao;
    @Resource
    QuestionnaireDao questionnaireDao;

    public Professional addNewByName(String name) {
        Professional professional = new Professional();
        professional.setName(name);
        return professionalDao.save(professional);
    }

    @Transactional(readOnly = true)
    public List<Professional> findAll() {
        return professionalDao.findAll();
    }

    @Transactional(readOnly = true)
    public Professional findById(Long id) {
        return professionalDao.findById(id);
    }

    public void deleteById(Long id) {
        Professional professional = professionalDao.findById(id);
        if (professional == null){
            return;
        }
        for (QuestionnaireAnswer questionnaireAnswer: professional.questionnaireAnswerList) {
            questionnaireAnswer.setProfessional(null);
            questionnaireAnswerDao.save(questionnaireAnswer);
        }
        for (Questionnaire questionnaire: professional.getQuestionnaireList()) {
            questionnaire.getProfessionalList().remove(professional);
            questionnaireDao.save(questionnaire);
        }
        professionalDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Professional professional) {
        return professional != null && professionalDao.existsById(professional.id);
    }
}
