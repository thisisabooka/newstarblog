package com.fuyao.newstarblog.controller.system.article;

import com.fuyao.newstarblog.beans.system.Admin;
import com.fuyao.newstarblog.beans.system.Article;
import com.fuyao.newstarblog.beans.system.Category;
import com.fuyao.newstarblog.beans.system.Tag;
import com.fuyao.newstarblog.service.system.ArticleService;
import com.fuyao.newstarblog.service.system.CategoryService;
import com.fuyao.newstarblog.service.system.TagService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 文章控制器
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;


//    文章列表
    @GetMapping(value = "/articles")
    public ModelAndView list(@RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);
        Page<Article> articlePage = articleService.list(request);

//        System.out.println(articlePage.getTotalPages());
        mv.addObject("totalPages",articlePage.getTotalPages());  //总页数
        mv.addObject("currentPage",articlePage.getNumber()+1);  //当前页码

        mv.addObject("articlePage",articlePage);
        mv.setViewName("system/article/list");
        return mv;
    }



//    模糊查询
    @GetMapping(value = "/fuzzysearch")
    public ModelAndView fuzzysearch(@RequestParam("author") String author,
                                    @RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);
        Page<Article> articlePage = articleService.fuzzysearch(request, "%" + author + "%");

        mv.addObject("totalPages",articlePage.getTotalPages());  //总页数
        mv.addObject("currentPage",articlePage.getNumber()+1);  //当前页码

        mv.addObject("articlePage",articlePage);
        mv.setViewName("system/article/list");
        return mv;
    }



//    来到文章添加页面
    @GetMapping(value = "/article")
    public String toAddPage(Map<String,Object> map){
        List<Category> categoryList = categoryService.list();
        List<Tag> tagList = tagService.list();
        map.put("categories",categoryList);
        map.put("tags",tagList);
        return "system/article/add";
    }


//    添加一篇博文
    @PostMapping(value = "/article")
    public ModelAndView add(Article article){

        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        article.setAuthor(admin.getNickname());  //作者

        Article a = articleService.add(article);

        /*System.out.println(a.getAuthor());
        System.out.println(a.getCategoryType());
        System.out.println(a.getTagType());*/

        if(a==null){
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "呜呜，博文添加失败！");
            mv.addObject("url", "/articles");
            mv.setViewName("common/error");
            return mv;
        }else{
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "恭喜你，博文添加成功！");
            mv.addObject("url", "/articles");
            mv.setViewName("common/success");
            return mv;
        }
    }



//    查看某一篇具体博文
    @GetMapping(value = "/look/{id}")
    public String look(@PathVariable("id") Integer id,Map<String,Object> map){
        Article article = articleService.look(id);
        Tag tag = tagService.findByTagType(article.getTagType());
        Category category = categoryService.findByCategoryType(article.getCategoryType());
        map.put("article",article);
        map.put("tag",tag);
        map.put("category",category);
        return "system/article/look";
    }


//    修改：审核，未审核
    @GetMapping(value = "/examineandverify/{id}")
    public String examineandverify(@PathVariable("id") Integer id){
        articleService.update(id);
        return "redirect:/articles";
    }


//    来到博文修改页面
    @GetMapping(value = "/article/{id}")
    public String update(@PathVariable("id") Integer id,Map<String,Object> map){
        Article article = articleService.look(id);
        map.put("article",article);
        map.put("categories",categoryService.list());  //分类组
        map.put("tags",tagService.list());  //标签组
        return "system/article/update";
    }


//    修改文章
    @PutMapping(value = "/article")
    public ModelAndView update(Article article){
        Article a = articleService.update(article);
        if(a==null){
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：id为 "+article.getId()+" 的文章修改失败了");
            mv.addObject("url", "/articles");
            mv.setViewName("common/error");
            return mv;
        }else{
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：id为 "+article.getId()+" 的文章修改成功了");
            mv.addObject("url", "/articles");
            mv.setViewName("common/success");
            return mv;
        }
    }


//    单个删除
    @DeleteMapping(value = "/article/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        articleService.delete(id);
        mv.addObject("msg","id为 "+id+" 的文章删除成功！");
        mv.addObject("url","/articles");
        mv.setViewName("common/success");
        return mv;
    }



//    批量删除选中的id
    @PostMapping(value = "/delSelectedArticle")
    public ModelAndView delSelected(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

//        1，获取所有的id
        String[] ids = request.getParameterValues("aid");
//        2，调用service删除
        articleService.delSelectedArticle(ids);
//        3，返回视图
        mv.addObject("msg","小星提示：批量删除文章成功了！");
        mv.addObject("url","/articles");
        mv.setViewName("common/success");

        return mv;
    }


}
