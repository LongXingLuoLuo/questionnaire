package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Teacher;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Teacher 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface TeacherDao extends Repository<Teacher, Long> {
    Teacher findById(Long id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    void deleteById(Long id);
}
