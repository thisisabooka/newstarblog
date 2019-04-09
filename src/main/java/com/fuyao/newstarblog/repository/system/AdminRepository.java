package com.fuyao.newstarblog.repository.system;


import com.fuyao.newstarblog.beans.system.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 后台系统管理员资源库
 */
public interface AdminRepository extends JpaRepository<Admin,Integer> {

//    根据用户昵称查询整个用户信息
    public Admin findByNickname(String nickname);

    
}
