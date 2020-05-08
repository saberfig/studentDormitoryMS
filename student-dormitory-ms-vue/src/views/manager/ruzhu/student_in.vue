<template>
  <div>
    <div>
      <div class="tableTop">
        <label style="display: inline-block;">
          <label>
            <label>校区号:</label>
            <input type="text" v-model="campus" />
          </label>
          <label>
            <label>宿舍楼号:</label>
            <input type="text" v-model="dorm" />
          </label>
          <label>
            <label>房间号:</label>
            <input type="text" v-model="room" />
          </label>
          <br>
          <label>
            <label>床位号:</label>
            <input type="text" v-model="bed" />
          </label>
          <label>
            <label>学号:</label>
            <input type="text" v-model="student" />
          </label>
        </label>
        <div>
          <ul class="opreating">
            <li>
              <button type="reset" class="btn btn-info" @click="add">添加</button>
            </li>
            <li>
              <button type="reset" class="btn btn-success" @click="modify">导入本地文件</button>
            </li>
          </ul>
        </div>
      </div>
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
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
    export default {
        name: "student_in",
      data() {
        return {
          id: "",
          student: "",
          bed: "",
          room: "",
          dorm: "",
          campus: "",
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
        search(keywords) {
          return this.list.filter(item => {
            if (item.id.includes(keywords)){
              return item;
            }
          });
        },
        add() {
          this.$axios
            .post("/manager/add_bed",{id:this.bed,roomId:this.room,roomDormId:this.dorm,roomDormCampusId:this.campus,studentId:this.student})
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                alert("添加成功");
              }
              if (successResponse.data.code === 400) {
                alert("添加失败");
              }
              if (successResponse.data.code === 401) {
                alert("学生已存在");
              }
              this.get_student_bed();
            })
            .catch(failResponse => {});
        },
        modify() {
          //
        },
      }
    }
</script>

<style scoped>

</style>
