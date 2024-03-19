package com.zhaoyani.springboot.service;

import com.zhaoyani.springboot.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZYN
 * @since 2023-08-04
 */
public interface IActivityService extends IService<Activity> {

    Activity findById(Integer activityId);
}
