package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.*;
import com.longxingluoluo.questionnaire.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/13 下午 07:16
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Resource
    QuestionnaireService questionnaireService;
    @Resource
    GradeService gradeService;
    @Resource
    ProfessionalService professionalService;
    @Resource
    TeacherService teacherService;
    @Resource
    CurriculumService curriculumService;

    @RequestMapping(value = "/get/all", produces = "application/json")
    @ResponseBody
    public String getAllQuestionnaires(){
        List<Questionnaire> questionnaireList = questionnaireService.findAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questionnaireList", questionnaireList);
        return jsonObject.toJSONString();
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

    @GetMapping(value = "/json/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String jsonById(@PathVariable("id") Long id){
        Questionnaire questionnaire = questionnaireService.findById(id);
        return JSONObject.toJSONString(questionnaire);
    }

    /**
     * 进入添加新的问卷界面
     * @param model 包含所有年级，专业，老师，课程
     * @return 添加新的问卷界面
     */
    @GetMapping(value = "/add")
    public String addNewQuestionnaire(Model model){
        model.addAttribute("gradeList", gradeService.findAll());
        model.addAttribute("professionalList", professionalService.findAll());
        model.addAttribute("teacherList", teacherService.findAll());
        model.addAttribute("curriculumList", curriculumService.findAll());
        return "questionnaire_add";
    }

    @PostMapping(value = "/doAdd", produces = "application/json")
    @ResponseBody
    public String doAddQuestionnaire(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam.toJSONString());
        String name = (String) jsonParam.get("name");
        JSONArray gradeJSONArray = jsonParam.getJSONArray("gradeList");
        JSONArray professionalJSONArray = jsonParam.getJSONArray("professionalList");
        JSONArray curriculumJSONArray = jsonParam.getJSONArray("curriculumList");
        JSONArray teacherJSONArray = jsonParam.getJSONArray("teacherList");
        List<Grade> gradeList = new ArrayList<>();
        List<Professional> professionalList = new ArrayList<>();
        List<Curriculum> curriculumList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        if (gradeJSONArray != null){
            for (Object o : gradeJSONArray) {
                Grade grade = new Grade();
                grade.setId(Long.valueOf(o.toString()));
                gradeList.add(grade);
            }
        }
        if (professionalJSONArray != null){
            for (Object o : professionalJSONArray) {
                Professional professional = new Professional();
                professional.setId(Long.valueOf(o.toString()));
                professionalList.add(professional);
            }
        }
        if (curriculumJSONArray != null){
            for (Object o : curriculumJSONArray) {
                Curriculum curriculum = new Curriculum();
                curriculum.setId(Long.valueOf(o.toString()));
                curriculumList.add(curriculum);
            }
        }
        if (teacherJSONArray != null){
            for (Object o : teacherJSONArray) {
                Teacher teacher = new Teacher();
                teacher.setId(Long.valueOf(o.toString()));
                teacherList.add(teacher);
            }
        }
        Questionnaire questionnaire = questionnaireService.addNewByAll(name, gradeList,professionalList, curriculumList, teacherList);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", questionnaire != null);
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/table/json/all", produces = "application/json")
    @ResponseBody
    public String getAllTableJson(HttpServletRequest request){
        JSONArray jsonArray = new JSONArray();
        List<Questionnaire> questionnaireList = questionnaireService.findAll();
        for (Questionnaire questionnaire :
                questionnaireList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", questionnaire.getId());
            jsonObject.put("name", questionnaire.getName());
            jsonObject.put("visit-url", questionnaire.getVisitUrl(request));
            jsonObject.put("answer-url", "/questionnaire_answer/table/" + questionnaire.getId());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @DeleteMapping(value = "/delete/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String deleteQuestionnaireById(@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        if (!questionnaireService.existsById(id)){
            jsonObject.put("msg", false);
        } else {
            questionnaireService.deleteById(id);
            jsonObject.put("msg", true);
        }
        return jsonObject.toJSONString();
    }
}
