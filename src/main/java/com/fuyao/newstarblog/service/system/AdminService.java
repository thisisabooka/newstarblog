package com.fuyao.newstarblog.service.system;

import com.fuyao.newstarblog.beans.system.Admin;

public interface AdminService {

//    注册
    public Admin register(Admin admin);

//    登录认证，根据用户昵称查询整个用户信息
    public Admin findAdminByNickname(String nickname);

}
