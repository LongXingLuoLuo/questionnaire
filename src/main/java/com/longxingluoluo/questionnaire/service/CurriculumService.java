package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.CurriculumDao;
import com.longxingluoluo.questionnaire.entity.Curriculum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Curriculum 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class CurriculumService {
    @Resource
    CurriculumDao curriculumDao;

    public Curriculum addNewByName(String name){
        Curriculum curriculum = new Curriculum();
        curriculum.setName(name);
        return  curriculumDao.save(curriculum);
    }

    @Transactional(readOnly = true)
    public List<Curriculum> findAll(){
        return curriculumDao.findAll();
    }

    @Transactional(readOnly = true)
    public Curriculum findById(Long id){
        return curriculumDao.findById(id);
    }

    public void deleteById(Long id){
        curriculumDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Curriculum curriculum){
        return curriculum!=null && curriculumDao.existsById(curriculum.id);
    }
}
