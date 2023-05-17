package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.entity.CurriculumEvaluation;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * CurriculumEvaluation 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface CurriculumEvaluationDao extends Repository<CurriculumEvaluation, Long> {
    CurriculumEvaluation findById(Long id);

    List<CurriculumEvaluation> findAll();

    /**
     * 查询指定课程下的所有课程评分
     * @param curriculum curriculum
     * @return 课程评分列表
     */
    List<CurriculumEvaluation> findByCurriculum(Curriculum curriculum);

    CurriculumEvaluation save(CurriculumEvaluation curriculumEvaluation);

    void deleteById(Long id);
}
