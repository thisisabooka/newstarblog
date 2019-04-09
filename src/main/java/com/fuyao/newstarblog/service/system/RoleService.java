package com.fuyao.newstarblog.service.system;

import com.fuyao.newstarblog.beans.system.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

//    增
    public Role insert(Role role);

//    删
    public void delete(Integer id);

//    改
    public Role update(Role role);

//    查一个
    public Role findRoleById(Integer id);

//    查所有；分页查询
    public Page<Role> list(Pageable pageable);


//    根据 角色中文名称 模糊查询 这个角色的详细信息
    public Page<Role> findRolesByChineseNameLike(Pageable pageable, String chineseName);


//    批量删除
    public void delSelectedRole(String[] ids);


}
