package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.*;
import com.longxingluoluo.questionnaire.service.QuestionnaireAnswerService;
import com.longxingluoluo.questionnaire.service.QuestionnaireService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/19 上午 10:46
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/questionnaire_answer")
public class QuestionnaireAnswerController {
    @Resource
    QuestionnaireAnswerService questionnaireAnswerService;
    @Resource
    QuestionnaireService questionnaireService;

    @RequestMapping("/get/questionnaire/{id:\\d+}")
    @ResponseBody
    public String getAllQuestionnaireAnswerByQuestionnaire(@PathVariable("id")Long id){
        if (!questionnaireService.existsById(id)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", false);
            return jsonObject.toJSONString();
        }

        Questionnaire questionnaire = questionnaireService.findById(id);
        List<QuestionnaireAnswer> questionnaireAnswerList = questionnaireAnswerService.findAllByQuestionnaire(questionnaire);
        JSONArray jsonArray = new JSONArray();
        for (QuestionnaireAnswer questionnaireAnswer:questionnaireAnswerList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("professional", questionnaireAnswer.getProfessional().getName());
            jsonObject.put("grade", questionnaireAnswer.getGrade().getName());
            for (CurriculumEvaluation curriculumEvaluation : questionnaireAnswer.curriculumEvaluationList){
                jsonObject.put("curriculum-evaluation-" + curriculumEvaluation.curriculum.getId(), curriculumEvaluation.getEvaluation());
            }
            for (TeacherEvaluation teacherEvaluation : questionnaireAnswer.teacherEvaluationList){
                jsonObject.put("teacher-evaluation-" + teacherEvaluation.teacher.getId(), teacherEvaluation.getEvaluation());
            }
            jsonObject.put("self-evaluation", questionnaireAnswer.getSelfEvaluation());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
