package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.ProfessionalDao;
import com.longxingluoluo.questionnaire.entity.Professional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Professional 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class ProfessionalService {
    @Resource
    ProfessionalDao professionalDao;

    public Professional addNewByName(String name) {
        Professional professional = new Professional();
        professional.setName(name);
        return professionalDao.save(professional);
    }

    @Transactional(readOnly = true)
    public List<Professional> findAll() {
        return professionalDao.findAll();
    }

    @Transactional(readOnly = true)
    public Professional findById(Long id) {
        return professionalDao.findById(id);
    }

    public void deleteById(Long id) {
        professionalDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Professional professional) {
        return professional != null && professionalDao.existsById(professional.id);
    }
}
