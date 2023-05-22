package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.*;
import com.longxingluoluo.questionnaire.service.QuestionnaireAnswerService;
import com.longxingluoluo.questionnaire.service.QuestionnaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/15 下午 04:07
 *
 * @author 龙星洛洛
 */
@Controller
public class BasicController {
    @Resource
    QuestionnaireService questionnaireService;
    @Resource
    QuestionnaireAnswerService questionnaireAnswerService;

    /**
     * 登录界面
     * @return login.html
     */
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**
     * 后台主页
     * @return index.html
     */
    @GetMapping(value = "/index")
    public String indexPage(){
        return "index";
    }

    /**
     * @param id 调查问卷的 id
     * @return 调查问卷页面
     */
    @GetMapping(value = "/visit/{id:\\d+}")
    public String visitQuestionnaire(@PathVariable("id")Long id, Model model){
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire == null) {
            return "error";
        }
        model.addAttribute("questionnaire", questionnaire);
        return "questionnaire";
    }

    @PostMapping(value = "/answer", produces = "application/json")
    @ResponseBody
    public String addNewQuestionnaireAnswer(@RequestBody JSONObject jsonParam) {
        System.out.println(jsonParam.toJSONString());
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(jsonParam.getLong("questionnaireId"));

        Grade grade = new Grade();
        grade.setId(jsonParam.getLong("gradeId"));

        Professional professional = new Professional();
        professional.setId(jsonParam.getLong("professionalId"));

        JSONArray curriculumEvaluationJSONArray = jsonParam.getJSONArray("curriculumEvaluationList");
        List<CurriculumEvaluation> curriculumEvaluationList = new ArrayList<>();
        for (Object o : curriculumEvaluationJSONArray) {
            CurriculumEvaluation curriculumEvaluation = new CurriculumEvaluation();
            curriculumEvaluation.setEvaluation(((JSONObject) o).getInteger("evaluation"));
            Curriculum curriculum = new Curriculum();
            curriculum.setId(((JSONObject) o).getLong("id"));
            curriculumEvaluation.setCurriculum(curriculum);
            curriculumEvaluationList.add(curriculumEvaluation);
        }

        JSONArray teacherEvaluationJSONArray = jsonParam.getJSONArray("teacherEvaluationList");
        List<TeacherEvaluation> teacherEvaluationList = new ArrayList<>();
        for (Object o : teacherEvaluationJSONArray) {
            TeacherEvaluation teacherEvaluation = new TeacherEvaluation();
            teacherEvaluation.setEvaluation(((JSONObject) o).getInteger("evaluation"));
            Teacher teacher = new Teacher();
            teacher.setId(((JSONObject) o).getLong("id"));
            teacherEvaluation.setTeacher(teacher);
            teacherEvaluationList.add(teacherEvaluation);
        }
        int selfEvaluation = jsonParam.getInteger("selfEvaluation");
        QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerService.addNewByAll(questionnaire, grade, professional, curriculumEvaluationList, teacherEvaluationList, selfEvaluation);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", questionnaireAnswer != null);
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/thanks")
    public String thanksPage(){
        return "thanks";
    }
}
