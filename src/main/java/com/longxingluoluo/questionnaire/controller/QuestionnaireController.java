package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.*;
import com.longxingluoluo.questionnaire.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @RequestMapping("/get/all")
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
    @RequestMapping(value = "/visit/{id:\\d+}", method = RequestMethod.GET)
    public String visitQuestionnaire(@PathVariable("id")Long id, Model model){
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire == null) {
            return "error";
        }
        model.addAttribute("questionnaire", questionnaire);
        return "questionnaire";
    }

    @GetMapping(value = "/json/{id:\\d+}")
    @ResponseBody
    public String jsonById(@PathVariable("id") Long id){
        Questionnaire questionnaire = questionnaireService.findById(id);
        return JSONObject.toJSONString(questionnaire);
    }

    /**
     * @param questionnaireId 填写的调查问卷的id
     * @return 填写结果
     */
    @RequestMapping(value = "/answer/{questionnaireId}", method = RequestMethod.POST)
    @ResponseBody
    public String answerQuestionnaire(@PathVariable("questionnaireId")Long questionnaireId){
        return "感谢填写问卷 questionnaireId";
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
        JSONArray gradeListJSON = jsonParam.getJSONArray("gradeList");
        JSONArray professionalListJSON = jsonParam.getJSONArray("professionalList");
        JSONArray curriculumListJSON = jsonParam.getJSONArray("curriculumList");
        JSONArray teacherListJSON = jsonParam.getJSONArray("teacherList");
        List<Grade> gradeList = new ArrayList<>();
        List<Professional> professionalList = new ArrayList<>();
        List<Curriculum> curriculumList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        if (gradeListJSON != null){
            for (Object o : gradeListJSON) {
                Grade grade = new Grade();
                grade.setId(Long.valueOf(o.toString()));
                gradeList.add(grade);
            }
        }
        if (professionalListJSON != null){
            for (Object o : professionalListJSON) {
                Professional professional = new Professional();
                professional.setId(Long.valueOf(o.toString()));
                professionalList.add(professional);
            }
        }
        if (curriculumListJSON != null){
            for (Object o : curriculumListJSON) {
                Curriculum curriculum = new Curriculum();
                curriculum.setId(Long.valueOf(o.toString()));
                curriculumList.add(curriculum);
            }
        }
        if (teacherListJSON != null){
            for (Object o : teacherListJSON) {
                Teacher teacher = new Teacher();
                teacher.setId(Long.valueOf(o.toString()));
                teacherList.add(teacher);
            }
        }
        Questionnaire questionnaire = questionnaireService.addNewByAll(name, gradeList,professionalList, curriculumList, teacherList);
        System.out.println(questionnaire);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", questionnaire != null);
        jsonObject.put("questionnaire", questionnaire);
        return jsonObject.toJSONString();
    }
}
