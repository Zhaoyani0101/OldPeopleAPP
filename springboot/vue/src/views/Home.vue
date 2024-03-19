<template>
<div>
  <el-row :gutter="10" style="margin-bottom:40px">
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">老人总数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          100
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">未体检老人数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          9
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">心理咨询老人数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          12
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">发布活动总数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          497
        </div>
      </el-card>
    </el-col>
  </el-row>
  <el-row :gutter="10" style="margin-bottom:40px">
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">服务订单总数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          559
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">退休党员老人总数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          100
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">志愿者总数</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          25
        </div>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card>
        <div style="color: #112f50;font-weight: bold;font-size: 20px">收益总额</div>
        <div style="padding: 10px 0; text-align: center;font-weight: bold;font-size: 30px">
          ￥354680
        </div>
      </el-card>
    </el-col>
  </el-row>
  <el-row>
    <el-col :span="8">
      <el-card id="main" style="width: 400px;height: 350px"></el-card>
    </el-col>
    <el-col :span="8">
      <el-card id="p" style="width: 400px;height: 350px"></el-card>
    </el-col>
    <el-col :span="8">
      <el-card id="q" style="width: 400px;height: 350px"></el-card>
    </el-col>
  </el-row>
</div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Home",
  data(){
    return{

    }
  },
  mounted() {//页面元素渲染之后再触发
    var option = {
      title: {
        text:'活动分类情况',
        left: 'center'
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar'
        }

      ]
    };
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    this.request.get("/echarts/example").then(res =>{
      //填空
      option.xAxis.data = res.data.x;
      option.series[0].data = res.data.y;
      option.series[1].data = res.data.y;
      //在数据准备完毕之后再set
      myChart.setOption(option);
    })


    //饼图
    var poption = {
      title: {
        text:'老人性别比例情况',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 980, name: '女' },
            { value: 1059, name: '男' },

          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var pDom = document.getElementById('p');
    var pChart = echarts.init(pDom);
    pChart.setOption(poption);


    //老人独居情况比例饼图
    var qoption = {
      title: {
        text:'老人独居情况比例情况',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 580, name: '非独居' },
            { value: 1059, name: '独居' },

          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var qDom = document.getElementById('q');
    var qChart = echarts.init(qDom);
    qChart.setOption(qoption);

  }
}
</script>

<style scoped>

</style>
