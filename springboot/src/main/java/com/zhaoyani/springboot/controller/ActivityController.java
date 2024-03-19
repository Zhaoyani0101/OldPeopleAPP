package com.zhaoyani.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.zhaoyani.springboot.service.IActivityService;
import com.zhaoyani.springboot.entity.Activity;


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
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private IActivityService activityService;

    @Resource
    private IUserService userService;


    //新增或更新
    @PostMapping
    public Result save(@RequestBody Activity activity) {
//        User user = SessionUtils.getUser();
//        activity.setCreatorId(user.getId());
//        activity.setLeftNums(activity.getNums());
        return Result.success(activityService.saveOrUpdate(activity));
    }


    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return  Result.success(activityService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return  Result.success(activityService.removeByIds(ids));
    }


    @GetMapping
    public Result findAll() {
        return  Result.success(activityService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(activityService.getById(id));
    }

    @GetMapping("/actname/{actname}")
    public Result findOne(@PathVariable String actname) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("actname",actname);
        return Result.success(activityService.getOne(queryWrapper));
    }


    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String actname,
                           @RequestParam(defaultValue = "") String location) {
//        List<User> userList = userService.list();
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        if(!"".equals(actname)){
            queryWrapper.like("actname",actname);
        }
        if(!"".equals(location)){
            queryWrapper.like("location",location);
        }
        queryWrapper.orderByDesc("id");
//        Page<Activity> page = activityService.page(new Page<>(pageNum, pageSize),queryWrapper);
//        for(Activity record : page.getRecords()){
//            userList.stream().filter(user -> user.getId().equals(record.getCreatorId())).findFirst().ifPresent(user -> {
//                record.setCreator(user.getNickname());
//            });
        return Result.success(activityService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<Activity> list = activityService.list();
        //通过工具类创建writer写出到磁盘路劲
//         Exception writer = ExcelUtil.getWriter(filesUploadPath +"/用户信息.xlsx");
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标签别名
        writer.addHeaderAlias("actname","活动名称");
        writer.addHeaderAlias("descr","活动描述");
        writer.addHeaderAlias("startTime","开始时间");
        writer.addHeaderAlias("endTime","结束时间");
        writer.addHeaderAlias("creatorId","发起人id");
        writer.addHeaderAlias("location","活动地点");
        writer.addHeaderAlias("deleted","逻辑删除");
        writer.addHeaderAlias("img","活动照片");
        writer.addHeaderAlias("rule","活动规则");
        writer.addHeaderAlias("nums","活动人数");
        writer.addHeaderAlias("leftNums","剩余人数");
        writer.addHeaderAlias("status","活动状态");


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
        List<Activity> activities = CollUtil.newArrayList();
        for(List<Object> row :list){
            Activity activity = new Activity();
            activity.setActname(row.get(0).toString());
            activity.setDescr(row.get(1).toString());
            activity.setStartTime(row.get(2).toString());
            activity.setEndTime(row.get(3).toString());
            activity.setLocation(row.get(4).toString());
            activity.setFee(row.get(5).hashCode());
            activity.setDeleted(row.get(6).hashCode());
            activity.setImg(row.get(7).toString());
            activity.setRule(row.get(8).toString());
            activity.setNums(row.get(9).hashCode());
            activity.setLeftNums(row.get(10).hashCode());
            activity.setStatus(row.get(11).toString());
            activity.setCreatorId(row.get(12).hashCode());

            activities.add(activity);
        }
        activityService.saveBatch(activities);
        return Result.success(true);
    }



}

