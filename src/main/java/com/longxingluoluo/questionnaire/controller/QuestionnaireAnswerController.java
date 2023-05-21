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

    @PostMapping(value = "/add")
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
        System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    @RequestMapping("/table/questionnaire/{id:\\d+}")
    @ResponseBody
    public String getAllQuestionnaireAnswerByQuestionnaire(@PathVariable("id") Long id) {
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
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @RequestMapping("/table/{id:\\d+}")
    public String showQuestionnaireAnswerTable(@PathVariable("id") Long id, Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire == null){
            return "error";
        }
        List<QuestionnaireAnswer> questionnaireAnswerList = new ArrayList<>();
        model.addAttribute("questionnaire", questionnaire);
        return "questionnaire_answer_table";
    }
}
