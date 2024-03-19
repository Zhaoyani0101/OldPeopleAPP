package com.zhaoyani.springboot.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.zhaoyani.springboot.entity.Activity;
import com.zhaoyani.springboot.service.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ActivityTask {

    @Resource
    IActivityService activityService;

    @Scheduled(fixedRate = 60 * 1000) //1分钟执行一次
    public void task(){
        log.info("我是定时任务，我更新活动状态的任务开始");

        List<Activity> updateList = new ArrayList<>();
        //扫描整个活动表
        List<Activity> list = activityService.list();
        for (Activity activity : list) {
            if(!"已结束".equals(activity.getStatus())){
                continue;
            }
            String startTime = activity.getStartTime();
            String endTime = activity.getEndTime();
            DateTime startDateTime = DateUtil.parse(startTime,"yyyy-MM-ddTHH:mm");//开始时间
            DateTime endDateTime = DateUtil.parse(endTime,"yyyy-MM-dd HH:mm");//结束时间
            DateTime now = new DateTime();//当前时间
            if(now.isAfterOrEquals(startDateTime) && now.isBeforeOrEquals(endDateTime)){
                activity.setStatus("进行中");
                updateList.add(activity);
            }else if(now.isAfter(endDateTime)){
                activity.setStatus("已结束");
                updateList.add(activity);
            }
        }
        if(updateList.size() > 0){
            log.info("定时任务正在更新活动状态......");
        }
        activityService.updateBatchById(updateList);//批量更新数据
        log.info("我是定时任务，我更新活动状态的任务结束");


    }
}
