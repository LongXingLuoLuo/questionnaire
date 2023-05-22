package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.CurriculumDao;
import com.longxingluoluo.questionnaire.dao.QuestionnaireDao;
import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.entity.CurriculumEvaluation;
import com.longxingluoluo.questionnaire.entity.Questionnaire;
import com.longxingluoluo.questionnaire.entity.QuestionnaireAnswer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Curriculum 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class CurriculumService {
    @Resource
    CurriculumDao curriculumDao;
    @Resource
    QuestionnaireDao questionnaireDao;

    public Curriculum addNewByName(String name){
        Curriculum curriculum = new Curriculum();
        curriculum.setName(name);
        return  curriculumDao.save(curriculum);
    }

    @Transactional(readOnly = true)
    public List<Curriculum> findAll(){
        return curriculumDao.findAll();
    }

    @Transactional(readOnly = true)
    public Curriculum findById(Long id){
        return curriculumDao.findById(id);
    }

    /**
     * 课程删除，
     * 从包含此课程选项的问卷移除此课程，并更新
     * @param id 需要删除的课程 id
     */
    public void deleteById(Long id){
        if (id == null){
            return;
        }
        Curriculum curriculum = curriculumDao.findById(id);
        if (curriculum == null){
            return;
        }
        for (Questionnaire questionnaire : curriculum.getQuestionnaireList()) {
            questionnaire.getCurriculumList().remove(curriculum);
            questionnaireDao.save(questionnaire);
        }
        for (CurriculumEvaluation curriculumEvaluation : curriculum.getCurriculumEvaluationList()) {
            curriculumEvaluation.setCurriculum(null);
            QuestionnaireAnswer questionnaireAnswer = curriculumEvaluation.getQuestionnaireAnswer();
            if (questionnaireAnswer != null){
                questionnaireAnswer.getCurriculumEvaluationList().remove(curriculumEvaluation);
            }
        }
        curriculumDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Curriculum curriculum){
        return curriculum!=null && curriculumDao.existsById(curriculum.id);
    }
}
