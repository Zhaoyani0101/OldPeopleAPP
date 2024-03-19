package com.zhaoyani.springboot.controller.dto;

import com.zhaoyani.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接收前端登录请求参数
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;
}
