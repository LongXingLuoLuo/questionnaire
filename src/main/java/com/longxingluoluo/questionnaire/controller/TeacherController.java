package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.Teacher;
import com.longxingluoluo.questionnaire.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Project questionnaire
 * Created on 2023/5/18 下午 12:19
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    TeacherService teacherService;

    @GetMapping(value = "/get/all", produces = "application/json")
    @ResponseBody
    public String getAllTeachers(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacherList", teacherService.findAll());
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/get/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String getTeacher(@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        Teacher teacher = teacherService.findById(id);
        jsonObject.put("teacher", teacher);
        return jsonObject.toJSONString();
    }

    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public String addNewTeacher(@RequestBody JSONObject jsonParam){
        JSONObject jsonObject = new JSONObject();
        String name = (String) jsonParam.get("name");
        if (name == null || name.trim().equals("")){
            jsonObject.put("teacher", null);
            return jsonObject.toJSONString();
        }
        Teacher teacher = teacherService.addNewByName(name);
        jsonObject.put("msg", teacher != null);
        return jsonObject.toJSONString();
    }

    @DeleteMapping(value = "/delete/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String deleteTeacher(@PathVariable("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Teacher teacher = new Teacher();
        teacher.setId(id);
        if (!teacherService.existsById(teacher)) {
            jsonObject.put("msg", false);
        } else {
            teacherService.deleteById(id);
            jsonObject.put("msg", true);
        }
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/table/json/all", produces = "application/json")
    @ResponseBody
    public String getAllTableJson(){
        return JSONArray.toJSONString(teacherService.findAll());
    }
}
