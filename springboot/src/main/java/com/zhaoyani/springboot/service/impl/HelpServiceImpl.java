package com.zhaoyani.springboot.service.impl;

import com.zhaoyani.springboot.entity.Help;
import com.zhaoyani.springboot.mapper.HelpMapper;
import com.zhaoyani.springboot.service.IHelpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZYN
 * @since 2023-09-13
 */
@Service
public class HelpServiceImpl extends ServiceImpl<HelpMapper, Help> implements IHelpService {

}
