package com.fuyao.newstarblog.controller.website.blog;

import com.fuyao.newstarblog.beans.system.Tag;
import com.fuyao.newstarblog.service.system.ArticleService;
import com.fuyao.newstarblog.service.system.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 前台门户网站博文控制器
 */
@Controller
public class BlogController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/blog")
    public ModelAndView blog(){
        ModelAndView mv = new ModelAndView();


        List<Tag> tagList = tagService.list();  //标签云
        mv.addObject("tagList",tagList);

        mv.setViewName("website/blog");
        return mv;
    }
}
