package com.zhaoyani.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.entity.Role;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.zhaoyani.springboot.service.IHelpService;
import com.zhaoyani.springboot.entity.Help;


import org.springframework.web.bind.annotation.RestController;






/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZYN
 * @since 2023-09-13
 */
@RestController
@RequestMapping("/help")
public class HelpController {

       @Resource
       private IHelpService helpService;

       //新增或更新
       @PostMapping
       public Result save(@RequestBody Help help) {
        return Result.success(helpService.saveOrUpdate(help));
        }

        //删除
       @DeleteMapping("/{id}")
       public Result delete(@PathVariable Integer id) {
        return Result.success(helpService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(helpService.removeByIds(ids));
        }


       @GetMapping
       public Result findAll() {
        return Result.success(helpService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(helpService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String address,
                           @RequestParam(defaultValue = "") String project) {
        QueryWrapper<Help> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }
        if(!"".equals(project)){
            queryWrapper.like("name",project);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(helpService.page(new Page<>(pageNum, pageSize)));
    }




}

