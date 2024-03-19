package com.zhaoyani.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName: File
 * Package:com.zhaoyani.springboot.entity
 * Description:
 *
 * @Author ZYN
 * @Create 2023/10/4 14:53
 */

@Data
@TableName("file")
public class Files {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private Boolean isDelete;
    private Boolean enable;
    private String md5;

}
