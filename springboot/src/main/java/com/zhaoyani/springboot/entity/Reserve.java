package com.zhaoyani.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.beans.Transient;
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
 * @since 2023-08-20
 */
@Getter
@Setter
  @TableName("reserve")
@ApiModel(value = "Reserve对象", description = "")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

      @ApiModelProperty("活动id")
      private Integer activityId;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("预约时间")
      private String time;

      @ApiModelProperty("审核状态")
      private String status;

      @ApiModelProperty("逻辑删除")
      private Integer deleted;




}
