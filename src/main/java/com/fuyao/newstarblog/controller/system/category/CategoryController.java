package com.fuyao.newstarblog.controller.system.category;

import com.fuyao.newstarblog.beans.system.Category;
import com.fuyao.newstarblog.service.system.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 文章分类控制器
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories")
    public ModelAndView list(@RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);
        Page<Category> categoryPage = categoryService.list(request);

        mv.addObject("categoryPage",categoryPage);
        mv.setViewName("system/category/list");
        return mv;
    }


//    模糊查询
    @GetMapping(value = "/searchlikecategoryname")
    public ModelAndView findCategoriesByCategoryNameLike(@RequestParam(value = "categoryName") String categoryName,
                                                         @RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);
        Page<Category> categoryPage =
                categoryService.findCategoriesByCategoryNameLike(request,"%"+categoryName+"%");

        mv.addObject("categoryPage",categoryPage);
        mv.setViewName("system/category/list");
        return mv;
    }


//    来到分类添加页面（添加修改二合一页面）
    @GetMapping(value = "/category")
    public String toAddPage(){
        return "system/category/add";
    }


//    添加分类
    @PostMapping(value = "/category")
    public ModelAndView add(Category category){
        Category c = categoryService.add(category);

        if(c==null){
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：呜呜，文章分类添加失败！");
            mv.addObject("url", "/categories");
            mv.setViewName("common/error");  //common：公共的；普遍的，常见的
            return mv;
        }else{
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：恭喜您，文章分类添加成功！");
            mv.addObject("url", "/categories");
            mv.setViewName("common/success");
            return mv;
        }
    }



//    来到分类修改页面
    @GetMapping(value = "/category/{id}")
    public String toEditPage(@PathVariable("id") Integer id,
                             Map<String,Object> map){
        map.put("category",categoryService.findCategoryById(id));
        return "system/category/add";
    }


//    修改分类信息
    @PutMapping(value = "/category")
    public ModelAndView update(Category category){
        Category c = categoryService.update(category);

        if(c==null){
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：呜呜，id为 "+category.getId()+" 的文章分类修改失败！");
            mv.addObject("url", "/categories");
            mv.setViewName("common/error");  //common：公共的；普遍的，常见的
            return mv;
        }else{
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：恭喜您，id为 "+category.getId()+" 的文章分类修改成功！");
            mv.addObject("url", "/categories");
            mv.setViewName("common/success");
            return mv;
        }
    }


//    删除一个分类
    @DeleteMapping(value = "/category/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        categoryService.delete(id);
        mv.addObject("msg","小星提示：删除id为 "+id+" 的文章分类成功！");
        mv.addObject("url","/roles");
        mv.setViewName("common/success");
        return mv;
    }


//    批量删除选中的id
    @PostMapping(value = "/delSelectedCategory")
    public ModelAndView delSelectedCategory(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        //        1，获取所有的id
        String[] ids = request.getParameterValues("cid");
        //        2，调用service删除

        categoryService.delSelectedCategory(ids);
        //        3，返回视图
        mv.addObject("msg","小星提示：批量删除文章分类成功了！");
        mv.addObject("url","/categories");
        mv.setViewName("common/success");

        return mv;
    }

}
