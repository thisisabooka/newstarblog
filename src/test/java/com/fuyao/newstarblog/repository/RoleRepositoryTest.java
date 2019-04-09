package com.fuyao.newstarblog.repository;

import com.fuyao.newstarblog.beans.system.Role;
import com.fuyao.newstarblog.repository.system.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

//    通过某一个角色id查询出此角色的详细信息
    @Test
    public void testFindRoleById(){
        Integer id = 1;
        Role role = roleRepository.findRoleById(id);
        System.out.println(role.toString());
    }

}
