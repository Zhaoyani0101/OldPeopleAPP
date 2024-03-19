<template>
  <el-menu :default-openeds="opens" style="min-height:100vh;overflow:hidden"
           background-color="#112f50" text-color="#fff"
           :collapse-transition="false"
           active-text-color="#ffd04b"
           :collapse="isCollapse"
            router>
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/Logo.jpg" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">
      <b style="color: white" v-show="logoTextShow">"空巢不空心"</b>
    </div>
    <div v-for="item in menus" :key="item.id">
      <div v-if="item.path">
        <el-menu-item :index="item.path">
          <i :class="item.icon"></i>
          <span slot="title">{{ item.name }}</span>
        </el-menu-item>
      </div>
      <div v-else>
        <el-submenu :index="item.id + ''">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
          </template>
          <div  v-for="subItem in item.children" :key="subItem.id">
            <el-menu-item :index="subItem.path">
              <i :class="subItem.icon"></i>
              <span slot="title">{{ subItem.name }}</span>
            </el-menu-item>
          </div>
        </el-submenu>
      </div>
    </div>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean,
  },
  data(){
    return{
      menus:localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) :[],
      opens:localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")).map(v => v.id + '') :[]
    }
  },
}
</script>

<style scoped>

</style>
