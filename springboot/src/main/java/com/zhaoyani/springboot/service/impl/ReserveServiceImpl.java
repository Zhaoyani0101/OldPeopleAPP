package com.zhaoyani.springboot.service.impl;

import com.zhaoyani.springboot.entity.Reserve;
import com.zhaoyani.springboot.mapper.ReserveMapper;
import com.zhaoyani.springboot.service.IReserveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZYN
 * @since 2023-08-20
 */
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements IReserveService {

    @Override
    public void add(Reserve reserve) {

    }
}
