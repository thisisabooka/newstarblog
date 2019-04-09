package com.fuyao.newstarblog.repository.system;

import com.fuyao.newstarblog.beans.system.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 后台管理系统角色资源库
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {

//    SpringData-Jpa分页查询：分页查询角色列表信息
    public Page<Role> findAllBy(Pageable pageable);


//    根据角色中文名模糊查询角色信息（分页）
    public Page<Role> findRolesByChineseNameLike(Pageable pageable, String chineseName);


//    根据某一个角色id查询出此角色的详细信息
    public Role findRoleById(Integer id);


}
