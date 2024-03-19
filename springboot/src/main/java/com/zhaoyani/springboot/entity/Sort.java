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
 * @since 2023-09-17
 */
@Getter
@Setter
  @TableName("sort")
@ApiModel(value = "Sort对象", description = "")
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("活动类型")
      private String name;

      @ApiModelProperty("类型描述")
      private String typedesc;


}
