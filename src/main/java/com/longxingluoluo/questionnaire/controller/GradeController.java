package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.Grade;
import com.longxingluoluo.questionnaire.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/16 下午 01:05
 * Grade 的 controller 层
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/grade")
public class GradeController {
    @Resource
    GradeService gradeService;

    /**
     * 获取全部的 grade
     *
     * @return 全部的 grade
     */
    @GetMapping(value = "/get/all", produces = "application/json")
    @ResponseBody
    public String allGrades() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gradeList", gradeService.findAll());
        return jsonObject.toJSONString();
    }

    /**
     * 获取指定 id 的年级的信息
     *
     * @param id 年级的 id
     * @return 获取指定 id 的年级的信息
     */
    @GetMapping(value = "/get/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String getGrade(@PathVariable("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Grade grade = gradeService.findById(id);
        jsonObject.put("grade", grade);
        return jsonObject.toJSONString();
    }

    /**
     * 添加新年级
     * @param jsonParam 传递数据 {"name": "本科2022"}
     * @return 添加后的年级数据
     */
    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public String addNewGrade(@RequestBody JSONObject jsonParam) {
        JSONObject jsonObject = new JSONObject();
        String name = (String) jsonParam.get("name");
        if (name == null || name.trim().equals("")){    // name 为空
            jsonObject.put("msg", false);
            return jsonObject.toJSONString();
        }
        Grade grade = gradeService.addNewByName(name);
        jsonObject.put("msg", grade != null);
        return jsonObject.toJSONString();
    }

    /**
     * 删除指定id 的年级数据
     *
     * @param id 需要删除的id
     * @return 成功信息
     */
    @DeleteMapping(value = "/delete/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String deleteGrade(@PathVariable("id") Long id) {
        JSONObject jsonObject = new JSONObject();
        Grade grade = new Grade();
        grade.setId(id);
        if (!gradeService.existsById(grade)) {
            // 目标 grade 不存在
            jsonObject.put("msg", false);
        } else {
            gradeService.deleteById(id);
            jsonObject.put("msg", true);
        }
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/table/json/all", produces = "application/json")
    @ResponseBody
    public String getAllTableJson(){
        return JSONArray.toJSONString(gradeService.findAll());
    }
}
