package com.fuyao.newstarblog.controller.system.role;

import com.fuyao.newstarblog.beans.system.Role;
import com.fuyao.newstarblog.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 角色控制器
 */
/*@Controller
@ResponseBody*/
//@RestController
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

//    查看角色列表信息
    @GetMapping(value = "/roles")
    public ModelAndView list(@RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);  //SpringData-Jpa默认从第0页开始
        Page<Role> rolePage = roleService.list(request);

        System.out.println(rolePage.getContent());  //内容；content
//        System.out.println(rolePage.getNumber());  //第几页；number
//        System.out.println(rolePage.getTotalPages());  //总页数；totalPages
//        System.out.println(rolePage.getTotalElements());  //总记录数；totalElements

        mv.addObject("rolePage",rolePage);
        mv.setViewName("system/role/list");
        return mv;
    }



//    根据 角色中文名称 模糊查询 角色信息
    @GetMapping(value = "/searchlikename")
    public ModelAndView findRolesByChineseNameIsLike(@RequestParam(value = "chineseName"/*,required = false*/) String chineseName,
                                                     @RequestParam(value = "pn",defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "size",defaultValue = "2") Integer pageSize){
        ModelAndView mv = new ModelAndView();

        PageRequest request = new PageRequest(pageNum-1,pageSize);
        Page<Role> rolePage = roleService.findRolesByChineseNameLike(request,"%"+chineseName+"%");  //注意%%
                                                    //注意：在Controller层添加  %%  才可以真正起作用！

        mv.addObject("rolePage",rolePage);
        mv.setViewName("system/role/list");
        return mv;
    }



//    来到角色添加页面
    @GetMapping(value = "/role")
    public String toAddPage(){
        return "system/role/add";
    }



//    添加一个角色信息
    @PostMapping(value = "/role")
    public ModelAndView add(Role role){
        /*System.out.println("Role："+role.getChineseName()+","+role.getEnglishName()+","
                            +role.getDescription()+","+role.getRoleType());*/
        Role r = roleService.insert(role);

        if(r==null){
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：呜呜，后台管理系统角色添加失败！");
            mv.addObject("url", "/roles");
            mv.setViewName("common/error");  //common：公共的；普遍的，常见的
            return mv;
        }else{
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：恭喜您，后台管理系统角色添加成功！");
            mv.addObject("url", "/roles");
            mv.setViewName("common/success");
            return mv;
        }
    }


//    根据某一角色id来到角色修改页面
    @GetMapping(value = "/role/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Map<String,Object> map){
        /*if(id==null){
            throw new RuntimeException("角色id为空，非法！");
        }*/
        Role role = roleService.findRoleById(id);
//        System.out.println("Role："+role.getDescription());
        map.put("role",role);
        return "system/role/add";
    }


//    修改角色信息
    @PutMapping(value = "/role")
    public ModelAndView update(Role role){
        Role r = roleService.update(role);
        if(r==null){
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：呜呜，id为 "+r.getId()+" 的系统角色修改失败！");
            mv.addObject("url", "/roles");
            mv.setViewName("common/error");
            return mv;
        }else{
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "小星提示：恭喜你，id为 "+r.getId()+" 的系统角色修改成功！");
            mv.addObject("url", "/roles");
            mv.setViewName("common/success");
            return mv;
        }
    }


//    删除一个角色信息
    @DeleteMapping(value = "/role/{id}")
    public ModelAndView delete(@PathVariable("id")Integer id){
        ModelAndView mv = new ModelAndView();
        roleService.delete(id);
        mv.addObject("msg","小星提示：删除id为 "+id+" 的系统角色成功！");
        mv.addObject("url","/roles");
        mv.setViewName("common/success");
        return mv;
    }


//    批量删除选中的id
    @PostMapping(value = "/delSelected")
    public ModelAndView delSelected(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

//        1，获取所有的id
        String[] ids = request.getParameterValues("rid");
//        2，调用service删除
        roleService.delSelectedRole(ids);
//        3，返回视图
        mv.addObject("msg","小星提示：批量删除系统角色成功了！");
        mv.addObject("url","/roles");
        mv.setViewName("common/success");

        return mv;
    }




}
