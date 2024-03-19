<template>
  <div>
    <!--头部-->
    <div style="display: flex; height: 61px; line-height: 60px; border-bottom: 1px solid #eee;background-color: #112f50">
        <div style="width: 300px;display:flex;padding-left: 30px">
          <div style="width: 60px">
          <img src="../../assets/Logo.jpg" alt="" style="width: 50px;position: relative;top: 5px;right: 0">
          </div>
          <div style="flex:1"><font color="#eee">欢迎来到"空巢不空心"系统</font></div>
        </div>
      <div style="flex: 1">
        <el-menu
            class="el-menu-demo"
            mode="horizontal"
            background-color="#112f50"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-menu-item index="1">首页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">健康模块</template>
            <el-submenu index="2-1">
              <template slot="title">身体模块</template>
              <el-menu-item index="2-1-1">每日健康检测</el-menu-item>
              <el-menu-item index="2-1-2">实时推送健康食谱</el-menu-item>
            </el-submenu>
            <el-submenu index="2-2">
              <template slot="title">心理模块</template>
              <el-menu-item index="2-2-1">心理监测</el-menu-item>
              <el-menu-item index="2-2-2">心理疏导</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">生活模块</template>
            <el-submenu index="3-1">
              <template slot="title">服务模块</template>
              <el-menu-item index="3-1-1">就医服务</el-menu-item>
              <el-menu-item index="3-1-2">代购服务</el-menu-item>
              <el-menu-item index="3-1-3">互助服务</el-menu-item>
            </el-submenu>
            <el-submenu index="3-2">
              <template slot="title">娱乐模块</template>
              <el-menu-item index="3-2-1">一键召集</el-menu-item>
              <el-menu-item index="3-2-2">预约活动</el-menu-item>
              <el-menu-item index="3-2-3">活动详情</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">圈子</template>
            <el-menu-item index="4-1">智能聊天</el-menu-item>
            <el-menu-item index="4-2">党组活动</el-menu-item>
            <el-menu-item index="4-3">志同道合</el-menu-item>
          </el-submenu>
          <el-menu-item index="5" disabled>消息中心</el-menu-item>
        </el-menu>
      </div>
      <div style="width: 300px;background-color: #112f50">
        <div v-if="!user.username" style="text-align: right;padding-right: 30px">
        <el-button @click="$router.push('/login')">登录</el-button>
        <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style="width: 150px;cursor: pointer;text-align: right">
            <div style="display: inline-block">
              <img :src="user.avatarUrl" alt=""
                   style="width:30px;border-radius: 50%;position: relative;top: 10px;right: 5px">
              <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
              <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                <router-link to="/person">个人信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                <span  style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

      </div>
    </div>
    <div style="width: 1000px;margin: 0 auto">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Front",
    data() {
      return {
        user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")):{}
      };
    },
    methods: {
     logout() {
       this.$store.commit("logout")
       this.$message.success("退出成功")
     }
    }
}
</script>

<style scoped>

</style>
