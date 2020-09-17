<template>
  <div class="addpadding">
    <div class="tableTop">
      <el-input
        placeholder="输入学号或姓名筛选"
        v-model="keywords"
        clearable
        size="medium"
        style="width:20rem;"
      ></el-input>
      <el-form :inline="true" class="pull-right">
        <el-form-item>
          <el-button type="primary" @click="dialogFormVisible = true">添加</el-button>
        </el-form-item>
        <el-form-item>
          <a href="javascript:;" class="file">
            本地导入
            <input type="file" @change="importExcel($event.target)" id />
          </a>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="dialogFormVisible_dh = true ">床位调换</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      :data="search(keywords)"
      style="width: 90%; margin:auto;"
      border
      :row-class-name="tableRowClassName"
    >
      <el-table-column label="校区">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ getCampusName(scope.row.roomDormCampusId) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="宿舍楼">
        <template slot-scope="scope">
          <p>{{ getDormName(scope.row.roomDormCampusId,scope.row.roomDormId) }}</p>
        </template>
      </el-table-column>
      <el-table-column label="房间号">
        <template slot-scope="scope">{{ scope.row.roomId }}</template>
      </el-table-column>
      <el-table-column label="姓名">
        <template slot-scope="scope">
          <p>{{ scope.row.name }}</p>
        </template>
      </el-table-column>
      <el-table-column label="学号">
        <template slot-scope="scope">
          <p>{{ scope.row.id }}</p>
        </template>
      </el-table-column>
      <el-table-column label="性别">
        <template slot-scope="scope">
          <p>{{ scope.row.sex }}</p>
        </template>
      </el-table-column>
      <el-table-column label="专业">
        <template slot-scope="scope">
          <p>{{ scope.row.major }}</p>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="学生入住" :visible.sync="dialogFormVisible" width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="校区名称:">
          <el-col :span="14">
            <el-select v-model="campus" placeholder="请选择校区名称">
              <div v-for="(item,index) in campusList" :key="index">
                <el-option :label="item.name" :value="item.id"></el-option>
              </div>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="宿舍楼名称:">
          <el-col :span="14">
            <el-select v-model="dorm" placeholder="请选择宿舍楼名称">
              <div v-for="(item,index) in dormList" :key="index">
                <el-option :label="item.dormName" :value="item.dormName+','+item.id"></el-option>
              </div>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="房间号:">
          <el-col :span="14">
            <el-select v-model="room" placeholder="请选择房间号">
              <div v-for="(item,index) in rooms" :key="index">
                <el-option :label="item" :value="item"></el-option>
              </div>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="床位号:">
          <el-col :span="14">
            <el-select v-model="bed" placeholder="请选择床位号">
              <div
                v-for="(item,index) in Array.from({length:4}).map((item,index)=>{return index+1;})"
                :key="index"
              >
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
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="床位调换" :visible.sync="dialogFormVisible_dh" width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="学生一学号:">
          <el-col :span="14">
            <el-input v-model="id1"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="学生二学号:">
          <el-col :span="14">
            <el-input v-model="id2"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible_dh = false">取 消</el-button>
        <el-button type="primary" @click="exchange">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import XLSX from "xlsx";
export default {
  name: "student_in",
  data() {
    return {
      keywords: "",
      id: "",
      bed: "",
      room: "",
      dorm: "",
      campus: "",
      id1: "",
      id2: "",
      campusList: [],
      dormList: [],
      dorms: [],
      roomList: [],
      rooms: [],
      list: [],
      studentList: [],
      students: [],
      dialogFormVisible: false,
      dialogFormVisible_dh: false,
      xlsxJson: [],
      submited: true
    };
  },
  mounted() {
    this.get_student_bed();
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return "success-row";
      }
      return "";
    },
    get_student_bed() {
      this.$axios
        .get("/manager/get_student_bed")
        .then(successResponse => {
          this.get_campus_info();
          this.get_dorm_info();
          this.get_room_info();
          this.get_student();
          this.list = successResponse.data;
        })
        .catch(failResponse => {
          this.$message({
            message: "获取数据失败",
            type: "error"
          });
        });
    },
    get_campus_info() {
      this.$axios
        .get("/manager/get_campus_info")
        .then(successResponse => {
          this.campusList = successResponse.data;
        })
        .catch(failResponse => {});
    },
    get_dorm_info() {
      this.$axios
        .get("/manager/get_dorm_info")
        .then(successResponse => {
          this.dormList = successResponse.data;
        })
        .catch(failResponse => {});
    },
    get_room_info() {
      this.$axios
        .get("/manager/get_room_info")
        .then(successResponse => {
          this.roomList = successResponse.data;
          for (var a in this.roomList) {
            if (this.rooms.indexOf(this.roomList[a].roomId) == -1) {
              this.rooms.push(this.roomList[a].roomId);
            }
          }
        })
        .catch(failResponse => {});
    },
    get_student() {
      this.$axios
        .get("/manager/get_student")
        .then(successResponse => {
          this.studentList = successResponse.data;
          for (var a in this.studentList) {
            if (this.students.indexOf(this.studentList[a].id) == -1) {
              this.students.push(this.studentList[a].id);
            }
          }
        })
        .catch(failResponse => {});
    },
    getCampusName(campusId) {
      var temp = this.campusList.filter(item => {
        if (item.id === campusId) return item;
      });
      return temp[0].name;
    },
    getDormName(campusId, dormId) {
      var temp = this.dormList.filter(item => {
        if (
          item.campusName === this.getCampusName(campusId) &&
          item.id === dormId
        )
          return item;
      });
      return temp[0].dormName;
    },
    search(keywords) {
      return this.list.filter(item => {
        if (item.id.includes(keywords) || item.name.includes(keywords)) {
          return item;
        }
      });
    },
    add() {
      if (this.campus === "") {
        this.$message({
          message: "请选择校区",
          type: "warning"
        });
        this.submited = false;
      } else if (this.dorm === "") {
        this.$message({
          message: "请选择宿舍楼",
          type: "warning"
        });
        this.submited = false;
      } else if (this.room === "") {
        this.$message({
          message: "请选择房间",
          type: "warning"
        });
        this.submited = false;
      } else if (this.bed === "") {
        this.$message({
          message: "请选择床位",
          type: "warning"
        });
        this.submited = false;
      } else if (this.id === "") {
        this.$message({
          message: "请添加学号",
          type: "warning"
        });
        this.submited = false;
      }

      if (!this.submited) {
        this.submited = true;
        return false;
      } else {
        this.$axios
          .post("/manager/add_bed", {
            id: this.bed,
            roomId: this.room,
            roomDormId: this.dorm.split(",")[1],
            roomDormCampusId: this.campus,
            studentId: this.id
          })
          .then(successResponse => {
            this.get_student_bed();
            this.$message({
              message: "添加成功",
              type: "success"
            });
          })
          .catch(failResponse => {
            this.$message({
              message: "添加失败",
              type: "error"
            });
          });
      }
      this.dialogFormVisible = false;
    },
    exchange() {
      if (!this.students.includes(this.id1)) {
        this.$message({
          message: "学生一学号不存在",
          type: "warning"
        });
        this.submited = false;
      } else if (!this.students.includes(this.id2)) {
        this.$message({
          message: "学生二学号不存在",
          type: "warning"
        });
        this.submited = false;
      }

      if (!this.submited) {
        this.submited = true;
        return false;
      } else {
        this.$axios
          .post("/manager/exchange", [
            { studentId: this.id1 },
            { studentId: this.id2 }
          ])
          .then(successResponse => {
            this.get_student_bed();
            this.$message({
              message: "床位调换成功",
              type: "success"
            });
          })
          .catch(failResponse => {
            this.$message({
              message: "床位调换失败",
              type: "error"
            });
          });
      }
      this.dialogFormVisible_dh = false;
    },
    importExcel(file1) {
      let file = file1.files[0];
      const types = file.name.split(".")[1];
      const fileType = ["xlsx", "xlc", "xlm", "xls", "xlt", "xlw", "csv"].some(
        item => item === types
      );
      if (!fileType) {
        this.$message({
          message: "格式错误！请重新选择",
          type: "error"
        });
        return;
      }
      this.file2Xce(file).then(tabJson => {
        if (tabJson && tabJson.length > 0) {
          this.xlsxJson = tabJson;
          var list = this.xlsxJson[0].sheet;
          for (var i = 0; i < list.length; i++) {
            this.$axios
              .post("/manager/add_bed", {
                id: list[i].id,
                roomId: list[i].roomId,
                roomDormId: list[i].roomDormId,
                roomDormCampusId: list[i].roomDormCampusId,
                studentId: list[i].studentId
              })
              .then(successResponse => {
                this.get_student_bed();
                this.$message({
                  message: "添加成功",
                  type: "success"
                });
              })
              .catch(failResponse => {
                this.$message({
                  message: "添加失败",
                  type: "error"
                });
              });
          }
        }
      });
    },
    file2Xce(file) {
      return new Promise(function(resolve, reject) {
        const reader = new FileReader();
        reader.onload = function(e) {
          const data = e.target.result;
          this.wb = XLSX.read(data, {
            type: "binary"
          });
          const result = [];
          this.wb.SheetNames.forEach(sheetName => {
            result.push({
              sheetName: sheetName,
              sheet: XLSX.utils.sheet_to_json(this.wb.Sheets[sheetName])
            });
          });
          resolve(result);
        };
        //reader.readAsBinaryString(file.raw);
        reader.readAsBinaryString(file);
      });
    }
  }
};
</script>

<style>
.file {
  position: relative;
  display: inline-block;
  background: #d0eeff;
  border: 1px solid #99d3f5;
  border-radius: 4px;
  padding: 4px 12px;
  overflow: hidden;
  color: #1e88c7;
  text-decoration: none;
  text-indent: 0;
  line-height: 30px;
}
.file input {
  position: absolute;
  font-size: 100px;
  right: 0;
  top: 0;
  opacity: 0;
}
.file:hover {
  background: #aadffd;
  border-color: #78c3f3;
  color: #004974;
  text-decoration: none;
}
.pull-right {
  float: right;
}
</style>
