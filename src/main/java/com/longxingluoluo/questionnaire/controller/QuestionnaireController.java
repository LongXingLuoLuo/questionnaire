package com.longxingluoluo.questionnaire.controller;

import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.entity.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 进入<a href="http://127.0.0.1:8090/questionnaire/id/1">调查问卷页面</a>
     * @param questionnaireId 调查问卷的id
     * @return 调查问卷页面
     */
    @RequestMapping(value = "/id/{questionnaireId}", method = RequestMethod.GET)
    public String visitQuestionnaire(@PathVariable("questionnaireId")Long questionnaireId, Model model){
        List<Curriculum> curriculumList = new ArrayList<>();
        curriculumList.add(new Curriculum(1L, "curriculum1"));
        curriculumList.add(new Curriculum(2L, "curriculum2"));
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher(1L, "teacher1"));
        teacherList.add(new Teacher(2L, "teacher2"));
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("curriculumList", curriculumList);
        model.addAttribute("teacherList", teacherList);
        return "questionnaire";
    }

    /**
     * 进入<a href="http://127.0.0.1:8090/questionnaire/id/1/answer">填写完成页面</a>
     * @param questionnaireId 填写的调查问卷的id
     * @return 填写结果
     */
    @RequestMapping(value = "/id/{questionnaireId}/answer", method = RequestMethod.POST)
    @ResponseBody
    public String answerQuestionnaire(@PathVariable("questionnaireId")Long questionnaireId){
        return "感谢填写问卷 questionnaireId";
    }
}
