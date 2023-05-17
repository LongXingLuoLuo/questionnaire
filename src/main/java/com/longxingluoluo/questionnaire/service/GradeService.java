package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.GradeDao;
import com.longxingluoluo.questionnaire.entity.Grade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/15 下午 10:41
 * Grade 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class GradeService {
    @Resource
    GradeDao gradeDao;

    /**
     * 添加新的 grade
     *
     * @param name grade 名字
     * @return 添加后的grade
     */
    public Grade addNewGradeByName(String name) {
        Grade grade = new Grade();
        grade.setName(name);
        return gradeDao.save(grade);
    }

    /**
     * 获取所有的 grade
     *
     * @return 数据库中所有的 grade
     */
    public List<Grade> findAllGrades() {
        return gradeDao.findAll();
    }

    /**
     * 根据 id 查找 grade
     *
     * @param id 指定的id
     * @return 符合要求的 grade
     */
    public Grade findGradeNyId(Long id) {
        return gradeDao.findById(id);
    }

    /**
     * 根据 id 删除 grade
     *
     * @param id 需要删除的 grade 的 id
     */
    public void deleteById(Long id) {
        gradeDao.deleteById(id);
    }
}
