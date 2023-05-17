package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Questionnaire;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Questionnaire 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface QuestionnaireDao extends Repository<Questionnaire, Long> {
    Questionnaire findById(Long id);

    List<Questionnaire> findAll();

    Questionnaire save(Questionnaire questionnaire);

    void deleteById(Long id);

    boolean existsById(Long id);
}
