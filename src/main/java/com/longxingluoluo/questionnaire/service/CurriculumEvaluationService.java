package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.CurriculumDao;
import com.longxingluoluo.questionnaire.dao.CurriculumEvaluationDao;
import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.entity.CurriculumEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  CurriculumEvaluation 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class CurriculumEvaluationService {
    @Resource
    CurriculumEvaluationDao curriculumEvaluationDao;
    @Resource
    CurriculumDao curriculumDao;

    public CurriculumEvaluation addNewByCurriculumAndEvaluation(Curriculum curriculum, int evaluation){
        if (curriculum == null || !curriculumDao.existsById(curriculum.id)){
            return null;
        }
        CurriculumEvaluation curriculumEvaluation = new CurriculumEvaluation();
        curriculumEvaluation.setCurriculum(curriculum);
        curriculumEvaluation.setEvaluation(evaluation);
        return curriculumEvaluationDao.save(curriculumEvaluation);
    }

    public CurriculumEvaluation findById(Long id){
        return curriculumEvaluationDao.findById(id);
    }

    public List<CurriculumEvaluation> findAll(){
        return curriculumEvaluationDao.findAll();
    }

    public List<CurriculumEvaluation> findAllByCurriculum(Curriculum curriculum){
        return curriculumEvaluationDao.findByCurriculum(curriculum);
    }

    public void deleteById(Long id){
        curriculumEvaluationDao.deleteById(id);
    }
}
