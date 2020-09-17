<template>
  <div id="app">
   <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>

<style>
*{
  font-size: 14px;
  margin: 0px;
  padding: 0px;
  font-family: "Helvetica Neue",HelveticaNeue,"Helvetica-Neue",Helvetica;
}
body {
	background-color: #e4efff;
}
.dialog {
  text-align: center;
  margin: auto;
}
.dialog label {
  font-weight: 400;
  font-size: 20px;
  margin-right: 10px;
}
.dialog input {
  width: 200px;
}
.el-table .success-row {
  background: #f0f9eb;
}
.addpadding {
  position: relative;
  padding: 25px 20px 0 20px;
}
.addBtn {
  position: absolute;
  right: 10.8%;
  bottom: -6rem;
}
.el-table th,
.el-table td {
  text-align: center;
}
.cell p {
  margin: 0;
}
</style>
