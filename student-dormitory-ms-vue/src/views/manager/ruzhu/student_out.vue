<template>
  <div class="addpadding">
    <div>
      <label class="pull-left" style="margin: 20px 0;">
        学号搜索：
        <input
          type="text"
          style="border: #ccc solid 1px;border-radius: 4px;"
          v-model="id"
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
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in search(id)" :key="item.id">
          <td>{{ item.roomDormCampusId}}</td>
          <td>{{ item.roomDormId }}</td>
          <td>{{ item.roomId }}</td>
          <td>{{ item.bed_id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.id }}</td>
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
        id: "",
        list: []
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
            this.list=successResponse.data;
          })
          .catch(failResponse => {});
      },
      del(id) {
        this.$axios
          .post("/manager/del_bed",{studentId:id})
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              //删除成功
            }
            if (successResponse.data.code === 400) {
              //删除失败
            }
            this.get_student_bed();
          })
          .catch(failResponse => {});

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
