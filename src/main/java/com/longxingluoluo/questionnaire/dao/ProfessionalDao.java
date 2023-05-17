package com.longxingluoluo.questionnaire.dao;

import com.longxingluoluo.questionnaire.entity.Professional;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Professional 的 dao 层
 *
 * @author 龙星洛洛
 */
public interface ProfessionalDao extends Repository<Professional, Long> {
    Professional findById(Long id);

    List<Professional> findAll();

    void deleteById(Long id);

    Professional save(Professional professional);

    boolean existsById(Long id);
}
