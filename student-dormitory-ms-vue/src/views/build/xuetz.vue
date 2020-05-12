<template>
  <div>
    <div>
    <section v-for="item in notice" :key="item.id">
      <!--公告区域1-->
      <div class="timeline-block">
        <!--标题-->
        <div class="timeline-title-info">
          <div class="timeline-title">公告</div>
          <div class="timeline-time">{{$moment(item.time).format('YYYY-MM-DD')}}</div>
        </div>
        <!--内容-->
        <div class="timeline-info">
          <div class="timeline-content">{{item.description}}</div>
        </div>
      </div>
      <!--end 公告区域-->
    </section>
    </div>

    <div>
      <button type="button">发布公告</button>
    </div>
    <div>
    	<h2>发布公告</h2>
    	<el-form  class="templatemo-form-weiji" ref="dataForm">
    	<el-form-item label="时间"  prop="time">
    	<br><el-input v-model="form.time" type="date"></el-input></el-form-item><br>
    	<el-form-item label="描述"  prop="description"><br>
    	<br><el-input v-model="form.description" class="templatemo-input-weiji"></el-input></el-form-item><br>
    	<el-button type="primary" @click="onSubmit">发布公告</el-button><br>
    	</el-form>
    	</div>
    </div>
</template>

<script>
export default {
  name: "xuetz",
  data() {
    return{
      form: {
        dormManagerId:'',
        description:'',
        time:'',
      },
      notice:[
      {
      id:'',
      time:'',
      description:'',
      dormManagerId:'',
      }
    ]
  }
},
   methods:{
     onSubmit(){
       this.$axios
         .post("/nav_build/xuetz",{
           dormManagerId: this.COMMON.id,
           time: this.form.time,
           description: this.form.description
         }).then(resp=> {
         if (resp && resp.status === 200) {
           this.$emit('onSubmit')
         }
     })
   },
  },
mounted:function(){
        this.$axios
          .post("/nav_build/xsxuetz", {
            id: this.COMMON.id,
                 })
                 .then(successResponse => {
                   this.notice = successResponse.data
                 })
                 .catch(failResponse => {
                 });
             }

};
</script>

<style scoped>
</style>
