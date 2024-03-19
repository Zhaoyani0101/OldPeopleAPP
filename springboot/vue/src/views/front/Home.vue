<template>
<div>
  <div style="margin: 10px 0">
    <el-carousel height="450px" interval="5000">
      <el-carousel-item v-for="item in imgs" :key="item">
        <img :src="item" alt="" style="width: 100%">
      </el-carousel-item>
    </el-carousel>
  </div>
</div>
</template>

<script>
export default {
  name: "FrontHome",
  data(){
    return{
      imgs:[
       'https://img.zcool.cn/community/01e3045c1cc6bfa8012029ac0598a7.jpg@1280w_1l_2o_100sh.jpg',
        'https://img.zcool.cn/community/017cd65c1cc6bea8012029acd602e6.jpg@1280w_1l_2o_100sh.jpg',
        'https://img.zcool.cn/community/01a9735c1cc6bfa8012029acb20d0b.jpg@2o.jpg',
          'https://img.zcool.cn/community/015dd15c1cc6bfa8012029ac49b686.jpg@1280w_1l_2o_100sh.jpg',
          'https://bpic.588ku.com/back_pic/05/93/23/885cf7ce508d001.jpg',
          'https://img95.699pic.com/photo/40176/9480.jpg_wh300.jpg',


      ]
    }
  },
  created() {
  },
  methods:{}
}
</script>

<style scoped>

</style>
