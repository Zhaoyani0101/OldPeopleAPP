package com.zhaoyani.springboot.service;

import com.zhaoyani.springboot.entity.Reserve;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZYN
 * @since 2023-08-20
 */
public interface IReserveService extends IService<Reserve> {

    void add(Reserve reserve);
}
