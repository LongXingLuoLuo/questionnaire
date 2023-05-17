package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.ProfessionalDao;
import com.longxingluoluo.questionnaire.entity.Professional;
import org.springframework.stereotype.Service;

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

    public List<Professional> findAll() {
        return professionalDao.findAll();
    }

    public Professional findById(Long id) {
        return professionalDao.findById(id);
    }

    public void deleteById(Long id) {
        professionalDao.deleteById(id);
    }

    public boolean existsById(Professional professional) {
        return professional != null && professionalDao.existsById(professional.id);
    }
}
