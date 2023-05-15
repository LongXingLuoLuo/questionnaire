package com.longxingluoluo.questionnaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Project questionnaire
 * Created on 2023/5/15 下午 04:07
 *
 * @author 龙星洛洛
 */
@Controller
public class BasicController {
    /**
     * 登录界面
     * @return index.html
     */
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    /**
     * 后台主页
     * @return index.html
     */
    @GetMapping("index")
    public String indexPage(){
        return "index";
    }
}
