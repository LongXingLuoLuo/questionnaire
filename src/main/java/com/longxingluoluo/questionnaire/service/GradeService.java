package com.longxingluoluo.questionnaire.service;

import com.longxingluoluo.questionnaire.dao.GradeDao;
import com.longxingluoluo.questionnaire.dao.QuestionnaireAnswerDao;
import com.longxingluoluo.questionnaire.dao.QuestionnaireDao;
import com.longxingluoluo.questionnaire.entity.Grade;
import com.longxingluoluo.questionnaire.entity.Questionnaire;
import com.longxingluoluo.questionnaire.entity.QuestionnaireAnswer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Grade 的 service 层
 *
 * @author 龙星洛洛
 */
@Service
public class GradeService {
    @Resource
    GradeDao gradeDao;
    @Resource
    QuestionnaireAnswerDao questionnaireAnswerDao;
    @Resource
    QuestionnaireDao questionnaireDao;

    /**
     * 添加新的 grade
     *
     * @param name grade 名字
     * @return 添加后的grade
     */
    public Grade addNewByName(String name) {
        if (name == null || name.trim().equals("")) {
            return null;
        }
        Grade grade = new Grade();
        grade.setName(name);
        return gradeDao.save(grade);
    }

    /**
     * 获取所有的 grade
     *
     * @return 数据库中所有的 grade
     */
    @Transactional(readOnly = true)
    public List<Grade> findAll() {
        return gradeDao.findAll();
    }

    /**
     * 根据 id 查找 grade
     *
     * @param id 指定的id
     * @return 符合要求的 grade
     */
    @Transactional(readOnly = true)
    public Grade findById(Long id) {
        return gradeDao.findById(id);
    }

    /**
     * 根据 id 删除 grade
     *
     * @param id 需要删除的 grade 的 id
     */
    public void deleteById(Long id) {
        Grade grade = gradeDao.findById(id);
        if (grade == null){
            return;
        }
        for (QuestionnaireAnswer questionnaireAnswer:
             grade.questionnaireAnswerList) {
            questionnaireAnswer.setGrade(null);
            questionnaireAnswerDao.save(questionnaireAnswer);
        }
        for (Questionnaire questionnaire:
             grade.getQuestionnaireList()) {
            questionnaire.getGradeList().remove(grade);
            questionnaireDao.save(questionnaire);
        }
        gradeDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Grade grade) {
        return grade!= null && gradeDao.existsById(grade.id);
    }
}
