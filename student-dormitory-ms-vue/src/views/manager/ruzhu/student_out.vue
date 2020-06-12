<template>
  <div class="addpadding">
    <div>
      <label class="pull-left" style="margin: 20px 0;">
        学号搜索：
        <input
          type="text"
          style="border: #ccc solid 1px;border-radius: 4px;"
          v-model="searchid"
        />
      </label>

      <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
          <th>校区号</th>
          <th>宿舍楼号</th>
          <th>房间号</th>
          <th>床位号</th>
          <th>姓名</th>
          <th>学号</th>
          <th>性别</th>
          <th>专业</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in search(searchid)" :key="item.id">
          <td>{{ getCampusName(item.roomDormCampusId)}}</td>
          <td>{{ getDormName(item.roomDormCampusId,item.roomDormId)}}</td>
          <td>{{ item.roomId }}</td>
          <td>{{ item.bed_id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.id }}</td>
          <td>{{ item.sex }}</td>
          <td>{{ item.major }}</td>
          <td>
            <li style="list-style: none;">
              <button type="reset" class="btn btn-danger" @click="del(item.id)">删除</button>
            </li>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  export default {
    name: "student_out",
    data() {
      return {
        searchid: "",
        list: [],
        campusList: [],
        dormList: [],
      };
    },
    mounted() {
      this.get_student_bed();
    },
    methods: {
      get_student_bed(){
        this.$axios
          .get("/manager/get_student_bed")
          .then(successResponse => {
            this.get_campus_info();
            this.get_dorm_info();
            this.list=successResponse.data;
          })
          .catch(failResponse => {});
      },
      get_campus_info(){
        this.$axios
          .get("/manager/get_campus_info")
          .then(successResponse => {
            this.campusList=successResponse.data;
          })
          .catch(failResponse => {});
      },
      get_dorm_info(){
        this.$axios
          .get("/manager/get_dorm_info")
          .then(successResponse => {
            this.dormList=successResponse.data;
            console.log(this.dormList);
          })
          .catch(failResponse => {});
      },
      getCampusName(campusId){
        var temp = this.campusList.filter(item=>{
          if(item.id===campusId)
            return item;
        })
        return temp[0].name;
      },
      getDormName(campusId,dormId){
        //console.log(this.dormList)
        //return "hh";
        var temp = this.dormList.filter(item=>{
          if(item.campusName===this.getCampusName(campusId)&&item.id===dormId)
            return item;
        });
        return temp[0].dormName;
      },
      del(id) {
        this.$confirm('此操作将永久删除该数据，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning',
        }).then(()=>{
          this.$axios
            .post("/manager/del_bed",{studentId:id})
            .then(successResponse => {
              this.get_student_bed();
              this.$message({
                message:'删除成功',
                type:'success',
              });
            })
            .catch(failResponse => {
              this.$message({
                message:'删除失败',
                type:'error',
              });
            });
        })
          .catch(()=>{
          })


      },
      search(keywords) {
        return this.list.filter(item => {
          if (item.id.includes(keywords)){
            return item;
          }
        });
      }
    }
  }
</script>

<style scoped>

</style>
