package com.zhaoyani.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.zhaoyani.springboot.service.IRoleService;
import com.zhaoyani.springboot.entity.Role;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZYN
 * @since 2023-08-04
 */
@RestController
@RequestMapping("/role")
public class RoleController {

       @Resource
       private IRoleService roleService;

       //新增或更新
       @PostMapping
       public Result save(@RequestBody Role role) {
        return Result.success(roleService.saveOrUpdate(role));
        }

        //删除
       @DeleteMapping("/{id}")
       public Result delete(@PathVariable Integer id) {
        return Result.success(roleService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(roleService.removeByIds(ids));
        }


       @GetMapping
       public Result findAll() {
        return Result.success(roleService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
        }


        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name) {
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            if(!"".equals(name)){
                queryWrapper.like("name",name);
            }
            queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize)));
        }

    /**
     * 绑定角色和菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id数组
     * @return
     */
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        return Result.success( roleService.getRoleMenu(roleId));
    }



}

