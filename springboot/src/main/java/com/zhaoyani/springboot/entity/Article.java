package com.zhaoyani.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author ZYN
 * @since 2023-09-09
 */
@Getter
@Setter
  @TableName("article")
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("标题")
      private String name;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("发布人")
      private String user;

      @ApiModelProperty("发布时间")
      private String time;

      @ApiModelProperty("状态")
      private Integer status;


}
