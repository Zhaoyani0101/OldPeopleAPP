package com.zhaoyani.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.zhaoyani.springboot.common.Result;
import com.zhaoyani.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/example")
    public Result get() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("文娱类", "棋牌类", "体育类", "乐器类"));
        map.put("y", CollUtil.newArrayList(150, 230, 254, 165));
        return Result.success(map);
    }


}
