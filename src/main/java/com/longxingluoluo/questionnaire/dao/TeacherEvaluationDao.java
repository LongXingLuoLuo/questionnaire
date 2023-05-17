package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Teacher;
import com.longxingluoluo.questionnaire.entity.TeacherEvaluation;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Teacher 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface TeacherEvaluationDao extends Repository<TeacherEvaluation, Long> {
    TeacherEvaluation findById(Long id);

    /**
     * 查询指定老师下的教师评分
     *
     * @param teacher 指定老师
     * @return 教师评分列表
     */
    List<TeacherEvaluation> findAllByTeacher(Teacher teacher);

    List<TeacherEvaluation> findAll();

    TeacherEvaluation save(TeacherEvaluation teacherEvaluation);

    void deleteById(Long id);
}
