package com.longxingluoluo.questionnaire;

import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.service.CurriculumEvaluationService;
import com.longxingluoluo.questionnaire.service.CurriculumService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QuestionnaireApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    CurriculumService curriculumService;
    @Resource
    CurriculumEvaluationService curriculumEvaluationService;
    @Test
    void CurriculumTest(){
        System.out.println(curriculumService.addNewByName("curriculum1"));
        System.out.println(curriculumService.findAll());
    }

    @Test
    void CurriculumEvaluationTest(){
        Curriculum curriculum = new Curriculum();
        curriculum.setId(2L);
        curriculum.setName("curriculum2");
        curriculumEvaluationService.addNewByCurriculumAndEvaluation(curriculum, 5);
    }
}
