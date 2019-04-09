package com.fuyao.newstarblog.service.impl.system;

import com.fuyao.newstarblog.beans.system.Admin;
import com.fuyao.newstarblog.repository.system.AdminRepository;
import com.fuyao.newstarblog.service.system.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

//    注册
    @Override
    public Admin register(Admin admin) {
        if(admin==null){
//            throw new RuntimeException("小星提示：用户输入非法！");
            return null;
        }else{
            admin.setLastLoginTime(new Date());  //新建管理员最近登录时间默认为管理员创建时间
            return adminRepository.save(admin);
        }
    }

    
//    根据用户昵称查询整个用户信息
    @Override
    public Admin findAdminByNickname(String nickname) {
        if(nickname==null){
//            throw new RuntimeException("小星提示：用户昵称为空，非法！");
            return null;
        }else{
            return adminRepository.findByNickname(nickname);
        }
    }


}
