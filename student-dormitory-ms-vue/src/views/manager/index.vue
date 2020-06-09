<template>
<div class="bgc">
  <el-carousel :interval="2000" height="830px" arrow="never" indicator-position="none">
    <el-carousel-item >
      <img src="../../../static/img/timg.jpg">
    </el-carousel-item>
    <el-carousel-item >
      <img src="../../../static/img/timg1.jpg">
    </el-carousel-item>
    <el-carousel-item >
      <img src="../../../static/img/timg2.jpg">
    </el-carousel-item>
  </el-carousel>
</div>
</template>

<script>
  export default {
    name: 'index',
  }
</script>