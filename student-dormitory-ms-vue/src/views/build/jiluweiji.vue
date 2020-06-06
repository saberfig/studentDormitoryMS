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
		</div>
</template>

<script>
export default {
  name: "jiluweiji",
  data() {
    return{
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
