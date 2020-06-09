<template>
  <div>
    <div>
      <table width="100%" class="templatemo-table-fj">
        <caption>
          <h2>访客信息</h2>
        </caption>
        <thead>
          <tr>
            <th>访客姓名</th>
            <th>访客身份</th>
            <th>相关学号</th>
            <th>来访时间</th>
            <th>理由</th>
          </tr>
        </thead>
        <tr v-for="item in vinfo" :key="item.id">
          <td>{{item.vname}}</td>
          <td>{{item.videntity}}</td>
          <td>{{item.studentId}}</td>
          <td>{{$moment(item.timeIn).format('YYYY-MM-DD HH:mm')}}</td>
          <td>{{item.reason}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "fangkexinxi",
  data() {
    return{
      vinfo:[
        {
         id:'',
         timeIn:'',
         vname:'',
         videntity:'',
         reason:'',
         studentId:'',
        }
      ]
    }
  },
  methods:{
  },
  mounted:function(){
          this.$axios
            .post("/nav_build/xsfangkexinxi", {
              id: this.COMMON.id,
                   })
                   .then(successResponse => {
                     this.vinfo = successResponse.data
                   })
                   .catch(failResponse => {
                   });
               }
};
</script>

<style scoped>
</style>
