package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.service.CurriculumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Project questionnaire
 * Created on 2023/5/18 上午 11:50
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/curriculum")
public class CurriculumController {
    @Resource
    CurriculumService curriculumService;

    @GetMapping(value = "/get/all", produces = "application/json")
    @ResponseBody
    public String getAllCurriculums(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("curriculumList", curriculumService.findAll());
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/get/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String getCurriculums(@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        Curriculum curriculum = curriculumService.findById(id);
        jsonObject.put("curriculum", curriculum);
        return jsonObject.toJSONString();
    }

    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public String addNewCurriculum(@RequestBody JSONObject jsonParam){
        JSONObject jsonObject = new JSONObject();
        String name = (String) jsonParam.get("name");
        if (name == null || name.trim().equals("")){
            jsonObject.put("curriculum", null);
            return jsonObject.toJSONString();
        }
        Curriculum curriculum = curriculumService.addNewByName(name);
        jsonObject.put("msg", curriculum != null);
        return jsonObject.toJSONString();
    }

    @DeleteMapping(value = "/delete/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String deleteCurriculum(@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        Curriculum curriculum = new Curriculum();
        curriculum.setId(id);
        if (!curriculumService.existsById(curriculum)){
            jsonObject.put("msg", false);
        } else {
            curriculumService.deleteById(id);
            jsonObject.put("msg", true);
        }
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/table/json/all", produces = "application/json")
    @ResponseBody
    public String getAllTableJson(){
        return JSONArray.toJSONString(curriculumService.findAll());
    }
}
