<template>
  <div>
    <div id="container" style="width: 100%;height: calc(100vh - 100px)">

    </div>
  </div>

</template>


<script>
export default {
  name: "Map",
  data(){
    return {}
  },
  created() {
  },
  mounted() {
    const map = new AMap.Map('container', {
      zoom:13,  //初始化地图层级
    })

    const marker = new AMap.Marker({
      position: new AMap.LngLat(116.39,39.9),
      title:'银川'
    })
    map.add(marker);
  }

}
</script>

<style scoped>

</style>
