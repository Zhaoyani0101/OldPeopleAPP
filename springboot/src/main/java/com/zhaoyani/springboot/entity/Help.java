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
 * @since 2023-09-13
 */
@Getter
@Setter
  @TableName("help")
@ApiModel(value = "Help对象", description = "")
public class Help implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("老人姓名")
      private String name;

      @ApiModelProperty("电话录音")
      private String video;

      @ApiModelProperty("老人电话")
      private String phone;

      @ApiModelProperty("服务地址")
      private String address;

      @ApiModelProperty("工单状态")
      private String status;

      @ApiModelProperty("服务项目")
      private String project;

      @ApiModelProperty("付款方式")
      private String pay;

      @ApiModelProperty("服务金额")
      private Integer fee;

      @ApiModelProperty("服务人员")
      private String service;

      @ApiModelProperty("接单人员")
      private String admin;

      @ApiModelProperty("下单时间")
      private String time;


}
