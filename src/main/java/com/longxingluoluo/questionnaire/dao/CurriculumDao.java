package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Curriculum;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Curriculum 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface CurriculumDao extends Repository<Curriculum, Long> {
    Curriculum findById(Long id);

    List<Curriculum> findAll();

    Curriculum save(Curriculum curriculum);

    void deleteById(Long id);

    /**
     * 查询指定 id 的 课程 是否存在
     * @param id 指定 id
     * @return 该课程是否存在
     */
    boolean existsById(Long id);
}
