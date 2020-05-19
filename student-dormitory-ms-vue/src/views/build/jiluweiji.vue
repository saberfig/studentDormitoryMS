<template>
  <div>
			<div>
				<table width="100%" class="templatemo-table-fj">
					<caption>
						<h2>
							违纪信息</h2>
					</caption>
					<thead>
					<tr>
						<th>学生姓名</th>
						<th>学号</th>
						<th>详细信息</th>
					</tr>
					</thead>
					<tr v-for="item in offeninfo" :key="item.id">
						<td>{{item.name}}</td>
						<td>{{item.studentId}}</td>
						<td>{{item.description}}</td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button">登记违纪信息</button>
			</div>
			<div>
					<h2>
      		违纪信息登记</h2>
				  <el-form  class="templatemo-form-weiji" ref="dataForm">
					<el-form-item label="记录日期"  prop="time">
					<br><el-input v-model="form.time" type="date"></el-input></el-form-item><br>
					<el-form-item label="学号"  prop="studentId">
					<br><el-input v-model="form.studentId" class="templatemo-input-weiji"></el-input></el-form-item><br>
					<el-form-item label="描述"  prop="description"><br>
					<br><el-input v-model="form.description" class="templatemo-input-weiji"></el-input></el-form-item><br>
					<el-button type="primary" @click="onSubmit">提交违纪信息</el-button><br>
				</el-form>
			</div>
		</div>
</template>

<script>
export default {
  name: "jiluweiji",
  data() {
    return{
      form: {
        studentId:'',
        description:'',
        time:'',
      },
      offeninfo:[
      {
      id:'',
      name:'',
      studentId:'',
      description:'',
      }
     ]
    }
  },

  methods:{
    onSubmit(){
      this.$axios
        .post("/nav_build/jiluweiji",{
          studentId: this.form.studentId,
          time: this.form.time,
          description: this.form.description
        }).then(resp=> {
        if (resp && resp.status === 200) {
          this.$emit('onSubmit')
        }
    })
  }
 },

  mounted: function() {
    this.$axios
      .post("/nav_build/xsjiluweiji", {
        id: this.COMMON.id,
              })
              .then(successResponse => {
                this.offeninfo = successResponse.data
              })
              .catch(failResponse  => {
              });
  }

};
</script>

<style scoped>
</style>
