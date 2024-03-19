package com.zhaoyani.springboot.service;

import com.zhaoyani.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZYN
 * @since 2023-08-04
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}

