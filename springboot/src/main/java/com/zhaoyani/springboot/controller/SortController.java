package com.zhaoyani.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.entity.User;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.zhaoyani.springboot.service.ISortService;
import com.zhaoyani.springboot.entity.Sort;


import org.springframework.web.bind.annotation.RestController;






/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZYN
 * @since 2023-09-17
 */
@RestController
@RequestMapping("/sort")
public class SortController {

       @Resource
       private ISortService sortService;

       //新增或更新
       @PostMapping
       public Result save(@RequestBody Sort sort) {
        return Result.success(sortService.saveOrUpdate(sort));
        }

        //删除
       @DeleteMapping("/{id}")
       public Result delete(@PathVariable Integer id) {
        return Result.success(sortService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(sortService.removeByIds(ids));
        }


       @GetMapping
       public Result findAll() {
        return Result.success(sortService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(sortService.getById(id));
        }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Sort> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(sortService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }




}

