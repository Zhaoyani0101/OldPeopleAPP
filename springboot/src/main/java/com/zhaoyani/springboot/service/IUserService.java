package com.zhaoyani.springboot.service;

import com.zhaoyani.springboot.controller.dto.UserDTO;
import com.zhaoyani.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZYN
 * @since 2023-05-07
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
