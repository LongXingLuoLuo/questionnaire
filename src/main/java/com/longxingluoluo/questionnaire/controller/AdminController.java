package com.longxingluoluo.questionnaire.controller;

import com.alibaba.fastjson.JSONObject;
import com.longxingluoluo.questionnaire.entity.Curriculum;
import com.longxingluoluo.questionnaire.service.CurriculumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Project questionnaire
 * Created on 2023/5/22 下午 07:52
 *
 * @author 龙星洛洛
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * 后台管理界面
     * @return management.html
     */
    @GetMapping("/management")
    public String managementPage(){
        return "management";
    }
}
