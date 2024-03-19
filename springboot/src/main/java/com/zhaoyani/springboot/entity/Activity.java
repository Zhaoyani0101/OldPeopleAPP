package com.zhaoyani.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-08-04
 */
@Getter
@Setter
@TableName("sys_activity")
@ApiModel(value = "Activity对象", description = "")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("活动名称")
      private String actname;

      @ApiModelProperty("活动描述")
      private String descr;

      @ApiModelProperty("开始时间")
      private String startTime;

      @ApiModelProperty("结束时间")
      private String endTime;

      @ApiModelProperty("发起人id")
      private Integer creatorId;
      @TableField(exist = false)
      private String creator;

      @ApiModelProperty("活动地点")
      private String location;

      @ApiModelProperty("活动经费")
      private Integer fee;

      @ApiModelProperty("逻辑删除")
      private Integer deleted;

      @ApiModelProperty("活动照片")
      private String img;

      @ApiModelProperty("活动规则")
      private String rule;

      @ApiModelProperty("活动人数")
      private Integer nums;

      @ApiModelProperty("剩余人数")
      private Integer leftNums;

      @ApiModelProperty("活动状态")
      private String status;


}
