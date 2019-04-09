package com.fuyao.newstarblog.service.impl.system;

import com.fuyao.newstarblog.beans.system.Role;
import com.fuyao.newstarblog.repository.system.RoleRepository;
import com.fuyao.newstarblog.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台管理系统角色业务逻辑层（Role-Service）
 */
@Service  //注入IOC容器中
@Transactional  //基于注解的事务控制
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


//    添加一个角色信息
    @Override
    public Role insert(Role role) {
        if(role==null){
//            throw new RuntimeException("角色信息为空，非法！");
            return null;
        }else{
            Role r = roleRepository.save(role);
            return r;
        }
    }


//    通过角色id删除某一个角色信息
    @Override
    public void delete(Integer id) {
        if(id==null){
            throw new RuntimeException("角色id为空，非法！");
        }else{
            roleRepository.delete(id);
        }
    }


//    修改某一个角色信息
    @Override
    public Role update(Role role) {
        if(role.getId()==null){
//            throw new RuntimeException("角色id为空，非法！");
            return null;
        }else{
            Role r = roleRepository.saveAndFlush(role);
            return r;
        }
    }


//    通过角色id查询某一个角色的详细信息
    @Override
    public Role findRoleById(Integer id) {
        if(id==null){
//            throw new RuntimeException("角色id为空，非法！");
            return null;
        }else{
            Role r = roleRepository.findOne(id);
            return r;
        }
    }


//    分页查询；查询所有的角色列表信息
    @Override
    public Page<Role> list(Pageable pageable) {
        Page<Role> rolePage = roleRepository.findAllBy(pageable);
        return rolePage;
    }


//    根据 角色中文名称 模糊查询 角色信息
    @Override
    public Page<Role> findRolesByChineseNameLike(Pageable pageable,String chineseName) {
//        chineseName = "%" + "%";  //这是错误写法
        Page<Role> rolePage = roleRepository.findRolesByChineseNameLike(pageable,chineseName);
        return rolePage;
    }


//    1，批量删除；2，删除选中
    @Override
    public void delSelectedRole(String[] ids) {
        if(ids!=null&&ids.length>0){
//            遍历数组
            for(String id : ids){
//                调用dao删除！
                Integer temp = Integer.parseInt(id);
                roleRepository.delete(temp);
            }
        }else{
            throw new RuntimeException("小星提示：发生未知错误，角色批量删除失败！");
        }
    }


}
