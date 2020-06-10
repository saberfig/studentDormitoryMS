<template>
  <div class="addpadding">
    <div>
      <div class="tableTop">
        <label class="pull-left" style="margin: 20px 0;">学号搜索：
          <input
            type="text"
            style="border: #ccc solid 1px;border-radius: 4px;"
            v-model="id"
          />
        </label>
        <el-form inline="true" class="pull-right">
          <el-form-item>
            <el-button type="primary"
                       class="pull-right"
                       @click="dialogFormVisible = true" >添加</el-button>
          </el-form-item>
          <span>&nbsp;</span>
          <el-form-item>
            <el-button type="success" class="pull-right" @click="modify">
              导入本地文件
            </el-button>
          </el-form-item>
        </el-form>

        <el-dialog title="学生入住"
                   :visible.sync="dialogFormVisible"
                   width="40%">
          <el-form class="dialog" label-position="right" label-width="30%">
            <el-form-item label="校区名称:">
              <el-col :span="14">
                <el-select v-model="campus" placeholder="请选择校区名称">
                  <div v-for="item in campus_names" :key="item">
                    <el-option :label="item" :value="item"></el-option>
                  </div>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="宿舍楼名称:">
              <el-col :span="14">
                <el-select v-model="dorm" placeholder="请选择宿舍楼名称">
                  <div v-for="item in dorm_names" :key="item">
                    <el-option :label="item" :value="item"></el-option>
                  </div>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="房间号:">
              <el-col :span="14">
                <el-select v-model="room" placeholder="请选择房间号">
                  <div v-for="item in room_ids" :key="item">
                    <el-option :label="item" :value="item"></el-option>
                  </div>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="床位号:">
              <el-col :span="14">
                <el-select v-model="bed" placeholder="请选择床位号">
                  <div v-for="item in bed_ids" :key="item">
                    <el-option :label="item" :value="item"></el-option>
                  </div>
                </el-select>
              </el-col>
            </el-form-item>
            <el-form-item label="学号:">
              <el-col :span="14">
                <el-input v-model="id"></el-input>
              </el-col>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="add">确 定</el-button>
          </span>
        </el-dialog>
      </div>

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

          list: [],
          campus_names: ["北京邮电大学西土城校区","北京邮电大学沙河校区"],
          dorm_names: ["学一楼","学二楼"],
          room_ids: ["301","302"],
          bed_ids: ["1","2","3","4","5","6","7","8","9","10","11"],
          dialogFormVisible: false,
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
