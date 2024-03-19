package com.zhaoyani.springboot.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.entity.Reserve;
import com.zhaoyani.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.zhaoyani.springboot.service.IArticleService;
import com.zhaoyani.springboot.entity.Article;


import org.springframework.web.bind.annotation.RestController;






/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZYN
 * @since 2023-09-09
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

       @Resource
       private IArticleService articleService;

       //新增或更新
       @PostMapping
       public Result save(@RequestBody Article article) {
           if(article.getId() == null){
               article.setTime(DateUtil.now());
//               article.setUser(TokenUtils.getCurrentUser().getNickname());
           }
        return Result.success(articleService.saveOrUpdate(article));
        }

        //删除
       @DeleteMapping("/{id}")
       public Result delete(@PathVariable Integer id) {
        return Result.success(articleService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(articleService.removeByIds(ids));
        }


       @GetMapping
       public Result findAll() {
        return Result.success(articleService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(articleService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            if(!"".equals(name)){
                queryWrapper.like("name",name);
            }
            queryWrapper.orderByDesc("id");
            return Result.success(articleService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }




}

