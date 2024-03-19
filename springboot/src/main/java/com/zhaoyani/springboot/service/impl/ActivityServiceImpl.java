package com.zhaoyani.springboot.service.impl;

import com.zhaoyani.springboot.entity.Activity;
import com.zhaoyani.springboot.mapper.ActivityMapper;
import com.zhaoyani.springboot.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZYN
 * @since 2023-08-04
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

    @Override
    public Activity findById(Integer activityId) {
        return null;
    }
}
