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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    /**
     * 为 bootstrap-table 提供的数据接口
     * @param id 问卷 id
     * @return 问卷的所有填写结果的 json 格式
     */
    @GetMapping(value = "/table/json/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String getTableJsonByQuestionnaire(@PathVariable("id") Long id) {
        if (!questionnaireService.existsById(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", false);
            return jsonObject.toJSONString();
        }

        Questionnaire questionnaire = questionnaireService.findById(id);
        List<QuestionnaireAnswer> questionnaireAnswerList = questionnaireAnswerService.findAllByQuestionnaire(questionnaire);
        JSONArray jsonArray = new JSONArray();
        for (QuestionnaireAnswer questionnaireAnswer : questionnaireAnswerList) {
            JSONObject jsonObject = new JSONObject();
            if (questionnaireAnswer.getProfessional() == null) {
                jsonObject.put("professional", null);
            } else {
                jsonObject.put("professional", questionnaireAnswer.getProfessional().getName());
            }

            if (questionnaireAnswer.getGrade() == null) {
                jsonObject.put("grade", null);
            } else {
                jsonObject.put("grade", questionnaireAnswer.getGrade().getName());
            }

            for (CurriculumEvaluation curriculumEvaluation : questionnaireAnswer.curriculumEvaluationList) {
                jsonObject.put("curriculum-evaluation-" + curriculumEvaluation.curriculum.getId(), curriculumEvaluation.getEvaluation());
            }
            for (TeacherEvaluation teacherEvaluation : questionnaireAnswer.teacherEvaluationList) {
                jsonObject.put("teacher-evaluation-" + teacherEvaluation.teacher.getId(), teacherEvaluation.getEvaluation());
            }
            jsonObject.put("self-evaluation", questionnaireAnswer.getSelfEvaluation());
            jsonObject.put("id", questionnaireAnswer.getId());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @GetMapping("/table/{id:\\d+}")
    public String showQuestionnaireAnswerTable(@PathVariable("id") Long id, Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire == null){
            return "error";
        }
        List<QuestionnaireAnswer> questionnaireAnswerList = new ArrayList<>();
        model.addAttribute("questionnaire", questionnaire);
        return "questionnaire_answer_table";
    }

    @GetMapping(value = "/export/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public void exportByQuestionnaire(@PathVariable("id") Long id, HttpServletResponse response){
        if (!questionnaireService.existsById(id)){
            return;
        }
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(id);
        try{
            questionnaireAnswerService.exportExcel(questionnaire, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/delete/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String deleteQuestionnaireAnswerById(@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        if (!questionnaireAnswerService.existsById(id)){
            jsonObject.put("msg", false);
        } else {
            questionnaireAnswerService.deleteById(id);
            jsonObject.put("msg", true);
        }
        return jsonObject.toJSONString();
    }

}
