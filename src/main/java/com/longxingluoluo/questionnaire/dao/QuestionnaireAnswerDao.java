package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.*;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * QuestionnaireAnswer 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface QuestionnaireAnswerDao extends Repository<QuestionnaireAnswer, Long> {
    /**
     * 根据 id 查询问卷答案
     *
     * @param id 根据 id 查询
     * @return 符合要求的问卷
     */
    QuestionnaireAnswer findById(Long id);

    /**
     * 查询指定问卷下的所有问卷答案
     *
     * @param questionnaire 指定的问卷
     * @return 问卷答案列表
     */
    List<QuestionnaireAnswer> findAllByQuestionnaire(Questionnaire questionnaire);

    /**
     * 查询指定专业下的所有问卷答案
     *
     * @param professional 指定的专业
     * @return 问卷答案列表
     */
    List<QuestionnaireAnswer> findAllByProfessional(Professional professional);

    /**
     * 查询指定年级下的所有问卷答案
     *
     * @param grade 指定的年级
     * @return 问卷答案列表
     */
    List<QuestionnaireAnswer> findAllByGrade(Grade grade);

    /**
     * 所有问卷答案列表
     *
     * @return 问卷答案列表
     */
    List<QuestionnaireAnswer> findAll();

    /**
     * 更新或保存问卷答案
     *
     * @param questionnaireAnswer 需要更新的问卷答案
     * @return 更新后的问卷答案
     */
    QuestionnaireAnswer save(QuestionnaireAnswer questionnaireAnswer);

    /**
     * 根据 id 删除指定的问卷答案
     *
     * @param id 指定的 id
     */
    void deleteById(Long id);
}
