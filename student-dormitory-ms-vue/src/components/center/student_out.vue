<template>
  <div class="addpadding">
    <div>
      <div class="addBtn">
        <a href="javascript:;" class="file">
          通过文件批量处理
          <input type="file" @change="importExcel($event.target)" id />
        </a>
      </div>

      <el-table
        :data="search(searchid)"
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
        <el-table-column label="床位号">
          <template slot-scope="scope">{{ scope.row.bed_id }}</template>
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
        <el-table-column width="180">
          <template slot="header" slot-scope="scope">
            <el-input v-model="searchid" size="small" placeholder="输入关键字搜索" />
          </template>
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="del(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import XLSX from "xlsx";

export default {
  name: "student_out",
  data() {
    return {
      searchid: "",
      list: [],
      campusList: [],
      dormList: []
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
          this.list = successResponse.data;
        })
        .catch(failResponse => {});
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
    getCampusName(campusId) {
      var temp = this.campusList.filter(item => {
        if (item.id === campusId) return item;
      });
      return temp[0].name;
    },
    getDormName(campusId, dormId) {
      //return "hh";
      var temp = this.dormList.filter(item => {
        if (
          item.campusName === this.getCampusName(campusId) &&
          item.id === dormId
        )
          return item;
      });
      return temp[0].dormName;
    },
    del(row) {
      const id = row.id;
      this.$confirm("此操作将永久删除该数据，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
            .post("/manager/del_bed", { studentId: id })
            .then(successResponse => {
              this.get_student_bed();
              this.$message({
                message: "删除成功",
                type: "success"
              });
            })
            .catch(failResponse => {
              this.$message({
                message: "删除失败",
                type: "error"
              });
            });
        })
        .catch(() => {});
    },
    search(keywords) {
      return this.list.filter(item => {
        if (item.id.includes(keywords)) {
          return item;
        }
      });
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
          this.$confirm("此操作将永久删除一些数据，是否继续？", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              for (var i = 0; i < list.length; i++) {
                this.$axios
                  .post("/manager/del_bed", {
                    studentId: list[i].studentId
                  })
                  .then(successResponse => {
                    this.get_student_bed();
                    this.$message({
                      message: "删除成功",
                      type: "success"
                    });
                  })
                  .catch(failResponse => {
                    this.$message({
                      message: "删除失败",
                      type: "error"
                    });
                  });
              }
            })
            .catch(() => {});
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
        reader.readAsBinaryString(file);
      });
    }
  }
};
</script>

<style scoped>
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
  cursor: pointer;
}
</style>
