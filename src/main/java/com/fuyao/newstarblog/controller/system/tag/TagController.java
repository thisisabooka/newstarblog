package com.fuyao.newstarblog.controller.system.tag;

import com.fuyao.newstarblog.beans.system.Tag;
import com.fuyao.newstarblog.service.system.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文章标签控制器
 */
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = "tags")
    public ModelAndView list(@RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);
        Page<Tag> tagPage = tagService.list(request);

        mv.addObject("tagPage",tagPage);
        mv.setViewName("system/tag/list");
        return mv;
    }

}
