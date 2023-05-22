package com.longxingluoluo.questionnaire;

import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.*;
import com.longxingluoluo.questionnaire.service.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest
class QuestionnaireApplicationTests {
    private String TAG = "TEST: ";

    @Test
    void contextLoads() {
    }

    @Resource
    CurriculumService curriculumService;
    @Resource
    TeacherService teacherService;
    @Resource
    GradeService gradeService;
    @Resource
    ProfessionalService professionalService;
    @Resource
    QuestionnaireService questionnaireService;
    @Resource
    QuestionnaireAnswerService questionnaireAnswerService;


    @Test
    void CurriculumTest() {
        curriculumService.deleteById(4L);
    }

    /**
     * 测试当 Curriculum 删除时, QuestionnaireAnswer, CurriculumEvaluation 是否删除
     */
    @Test
    void questionnaireAnswerDeleteTest() {
//        questionnaireAnswerService.deleteById(1L);
    curriculumService.deleteById(1L);
    }

    @Test
    void jsonFindAllTest(){
//        List<QuestionnaireAnswer> questionnaireAnswerList = questionnaireAnswerDao.findAll();
        System.out.println(JSONObject.toJSONString(questionnaireAnswerService.findAll()));
    }

    @Test
    void initTestDatabase(){
        List<Curriculum> curriculumList = new ArrayList<>();
        curriculumList.add(curriculumService.addNewByName("大学物理"));
        curriculumList.add(curriculumService.addNewByName("电子技术实习"));
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherService.addNewByName("张三"));
        teacherList.add(teacherService.addNewByName("李四"));
        List<Grade> gradeList = new ArrayList<>();
        gradeList.add(gradeService.addNewByName("本科2022"));
        gradeList.add(gradeService.addNewByName("本科2021"));
        gradeList.add(gradeService.addNewByName("本科2020"));
        gradeList.add(gradeService.addNewByName("本科2019"));
        gradeList.add(gradeService.addNewByName("本科2018"));
        gradeList.add(gradeService.addNewByName("本科2017"));
        List<Professional> professionalList = new ArrayList<>();
        professionalList.add(professionalService.addNewByName("计算机科学技术"));
        professionalList.add(professionalService.addNewByName("物联网"));
        professionalList.add(professionalService.addNewByName("信息安全"));
        questionnaireService.addNewByAll("调查问卷-1",gradeList, professionalList, curriculumList, teacherList);
    }

    @Test
    void questionnaireAnswerAddTest(){
        List<CurriculumEvaluation> curriculumEvaluationList = new ArrayList<>();
        CurriculumEvaluation curriculumEvaluation = new CurriculumEvaluation();
        Curriculum curriculum = new Curriculum();
        curriculum.setId(2L);
        curriculumEvaluation.setCurriculum(curriculum);
        curriculumEvaluation.setEvaluation(15);
        curriculumEvaluationList.add(curriculumEvaluation);
        List<TeacherEvaluation> teacherEvaluationList = new ArrayList<>();
        TeacherEvaluation teacherEvaluation = new TeacherEvaluation();
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacherEvaluation.setTeacher(teacher);
        teacherEvaluation.setEvaluation(20);
        teacherEvaluationList.add(teacherEvaluation);
        Grade grade = new Grade();
        grade.setId(1L);
        Professional professional = new Professional();
        professional.setId(1L);
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(1L);
        int selfEvaluation = 10;
        questionnaireAnswerService.addNewByAll(questionnaire, grade, professional, curriculumEvaluationList, teacherEvaluationList, selfEvaluation);
    }

    @Test
    void sortQuestionnaireAnswerByQuestionnaire(){
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(1L);
        List<QuestionnaireAnswer> questionnaireAnswerList = questionnaireAnswerService.findAllByQuestionnaire(questionnaire);
        log.info(JSONObject.toJSONString(questionnaireAnswerList));
    }

    @Test
    void questionnaireDelete(){
        questionnaireService.deleteById(5L);
    }
}
