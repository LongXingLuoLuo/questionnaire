package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.Professional;
import com.longxingluoluo.questionnaire.service.ProfessionalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Project questionnaire
 * Created on 2023/5/18 下午 02:39
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/professional")
public class ProfessionalController {
    @Resource
    ProfessionalService professionalService;

    @GetMapping(value = "/get/all", produces = "application/json")
    @ResponseBody
    public String getAllProfessionals(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("professionalList", professionalService.findAll());
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/get/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String getProfessional(@PathVariable("id")Long id){
        JSONObject jsonObject = new JSONObject();
        Professional professional = professionalService.findById(id);
        jsonObject.put("professional", professional);
        return jsonObject.toJSONString();
    }

    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public String addNewProfessional(@RequestBody JSONObject jsonParam){
        JSONObject jsonObject = new JSONObject();
        String name = (String) jsonParam.get("name");
        if (name == null || name.trim().equals("")){
            jsonObject.put("professional", null);
            return jsonObject.toJSONString();
        }
        Professional professional = professionalService.addNewByName(name);
        jsonObject.put("msg", professional != null);
        return jsonObject.toJSONString();
    }

    @DeleteMapping(value = "/delete/{id:\\d+}", produces = "application/json")
    @ResponseBody
    public String deleteProfessional(@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        Professional professional = new Professional();
        professional.setId(id);
        if (!professionalService.existsById(professional)){
            jsonObject.put("msg", false);
        } else {
            professionalService.deleteById(id);
            jsonObject.put("msg", true);
        }
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/table/json/all", produces = "application/json")
    @ResponseBody
    public String getAllTableJson(){
        return JSONArray.toJSONString(professionalService.findAll());
    }
}
