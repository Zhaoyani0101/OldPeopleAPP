package com.zhaoyani.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.entity.Activity;
import com.zhaoyani.springboot.service.IActivityService;
import com.zhaoyani.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.zhaoyani.springboot.service.IReserveService;
import com.zhaoyani.springboot.entity.Reserve;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZYN
 * @since 2023-08-20
 */
@RestController
@RequestMapping("/reserve")
public class ReserveController {

       @Resource
       private IReserveService reserveService;

       @Resource
       private IUserService userService;

       @Resource
       private IActivityService activityService;


       //新增或更新
       @PostMapping
       public Result save(@RequestBody Reserve reserve) {
           reserve.setTime(DateUtil.now());
           reserveService.add(reserve);

        return Result.success(reserveService.saveOrUpdate(reserve));
        }

        //删除
       @DeleteMapping("/{id}")
       public Result delete(@PathVariable Integer id) {
        return Result.success(reserveService.removeById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(reserveService.removeByIds(ids));
        }


       @GetMapping
       public Result findAll() {
        return Result.success(reserveService.list());
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(reserveService.getById(id));
        }

        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String status) {
            QueryWrapper<Reserve> queryWrapper = new QueryWrapper<>();
            if(!"".equals(status)){
                queryWrapper.like("status",status);
            }
            queryWrapper.orderByDesc("id");
            return Result.success(reserveService.page(new Page<>(pageNum, pageSize),queryWrapper));

        }



    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<Reserve> list = reserveService.list();
        //通过工具类创建writer写出到磁盘路劲
//         Exception writer = ExcelUtil.getWriter(filesUploadPath +"/用户信息.xlsx");
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标签别名
        writer.addHeaderAlias("activityId","活动id");
        writer.addHeaderAlias("userId","用户id");
        writer.addHeaderAlias("time","预约时间");
        writer.addHeaderAlias("status","审核状态");


        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("活动信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;fileName="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }

    /**
     * excel导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //方式一：通过javaBean的方式读取excel,表头必须是英文
//         List<User> list = reader.readAll(User.class);

        //方式二：忽略表头中文
        List<List<Object>> list = reader.read(1);
        List<Reserve> reserves = CollUtil.newArrayList();
        for(List<Object> row :list){
            Reserve reserve = new Reserve();
            reserve.setActivityId(row.get(0).hashCode());
            reserve.setUserId(row.get(1).hashCode());
            reserve.setTime(row.get(2).toString());
            reserve.setStatus(row.get(3).toString());

            reserves.add(reserve);
        }
        reserveService.saveBatch(reserves);
        return Result.success(true);
    }



}

