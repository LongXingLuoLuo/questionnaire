package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Grade;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 *  Grade 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface GradeDao extends Repository<Grade, Long> {
    /**
     * 根据 id 在数据库中查找 Grade
     * @param id 指定 id
     * @return 数据库中符合要求的 Grade
     */
    Grade findById(Long id);

    /**
     * 更新或者保存 grade
     * @param grade 需要更改的 grade
     * @return 更新或者保存后的 grade
     */
    Grade save(Grade grade);

    /**
     * 查找所有的 grade
     * @return 数据库中所有的 id 列表
     */
    List<Grade> findAll();

    /**
     * 根据 id 删除数据库中指定 Grade
     * @param id 指定id
     */
    void deleteById(Long id);

    boolean existsById(Long id);
}
