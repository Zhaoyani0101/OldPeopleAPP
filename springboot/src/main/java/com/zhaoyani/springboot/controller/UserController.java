package com.zhaoyani.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhaoyani.springboot.common.Constants;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.controller.dto.UserDTO;
import com.zhaoyani.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import com.zhaoyani.springboot.service.IUserService;
import com.zhaoyani.springboot.entity.User;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZYN
 * @since 2023-05-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

       @Resource
       private IUserService userService;

       //登录
     @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

      //注册
      @PostMapping("/register")
       public Result register(@RequestBody UserDTO userDTO) {
          String username = userDTO.getUsername();
          String password = userDTO.getPassword();
          if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
              return Result.error(Constants.CODE_400,"参数错误");
          }
         return Result.success(userService.register(userDTO));
    }

    //新增或更新
       @PostMapping
       public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
        }

        //删除
       @DeleteMapping("/{id}")
       public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
        }

        //批量删除
        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
        }


       @GetMapping
       public Result findAll() {
        return Result.success(userService.list());
        }

        //查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
        }

        @GetMapping("/username/{username}")
        public Result findOne(@PathVariable String username) {
         QueryWrapper<User> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("username",username);
         return Result.success(userService.getOne(queryWrapper));
    }



        //分页查询
        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam(defaultValue = "") String email,
                                   @RequestParam(defaultValue = "") String address) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if(!"".equals(username)){
                queryWrapper.like("username",username);
            }
            if(!"".equals(email)){
                queryWrapper.like("email",email);
            }
            if(!"".equals(address)){
                queryWrapper.like("address",address);
            }
            queryWrapper.orderByDesc("id");
        return Result.success(userService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<User> list = userService.list();
        //通过工具类创建writer写出到磁盘路劲
//         Exception writer = ExcelUtil.getWriter(filesUploadPath +"/用户信息.xlsx");
        //在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标签别名
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("create_time","创建时间");
        writer.addHeaderAlias("avatar_url","头像");

        //一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list,true);
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
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
        List<User> users = CollUtil.newArrayList();
        for(List<Object> row :list){
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }
        userService.saveBatch(users);
        return Result.success(true);
    }




}

