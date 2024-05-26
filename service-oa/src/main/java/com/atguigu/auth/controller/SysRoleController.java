package com.atguigu.auth.controller;


import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有的角色")
    @GetMapping("findAll")
    public Result findAll(){
        List<SysRole> list = sysRoleService.list();
        try {
            int i = 1/0;
        }catch (Exception e){
            throw new GuiguException(20001,"执行了自定义异常...");
        }

        return Result.ok(list);
    }

    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page, @PathVariable Long limit, SysRoleQueryVo sysRoleQueryVo){
        Page<SysRole> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(SysRole::getRoleName,roleName);
        }
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);

    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole){
        boolean is_success = sysRoleService.save(sysRole);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("根据ID查询")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody SysRole sysRole){
        boolean is_success = sysRoleService.updateById(sysRole);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean is_success = sysRoleService.removeById(id);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        boolean is_success = sysRoleService.removeByIds(idList);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
