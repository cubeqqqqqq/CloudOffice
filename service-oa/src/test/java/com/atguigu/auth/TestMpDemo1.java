package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo1 {
    @Autowired
    private SysRoleMapper mapper;
    @Test
    public  void getAll(){
        List<SysRole> list = mapper.selectList(null);
        System.out.println(list);
    }
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        int rows = mapper.insert(sysRole);
        System.out.println(rows);
    }
}
