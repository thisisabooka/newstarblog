package com.fuyao.newstarblog.controller.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsiteController {

    @RequestMapping(value = "/website")
    public String website(){
        return "website/index";
    }


    @RequestMapping(value = "/blog")
    public String blog(){
        return "website/blog";
    }
}
