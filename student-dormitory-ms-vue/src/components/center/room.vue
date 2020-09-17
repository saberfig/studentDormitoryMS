<template>
  <div class="addpadding">
    <div>
      <div class="block" style="margin-bottom:20px">
        <span class="demonstration">请选择所属位置：</span>
        <el-cascader v-model="filter" :options="options" clearable></el-cascader>
      </div>
      <el-table
        :data="search(keywords)"
        style="width: 80%; margin:auto;"
        border
        :row-class-name="tableRowClassName"
      >
        <el-table-column label="房间号">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.roomId }}</span>
          </template>
        </el-table-column>
        <el-table-column label="所属校区">
          <template slot-scope="scope">
            <p>{{ scope.row.campusName }}</p>
          </template>
        </el-table-column>
        <el-table-column label="所属宿舍楼">
          <template slot-scope="scope">{{ scope.row.dormName }}</template>
        </el-table-column>
        <el-table-column label="床位数">
          <template slot-scope="scope">
            <p>{{ scope.row.bedNum }}</p>
          </template>
        </el-table-column>
        <el-table-column width="180">
          <template slot="header" slot-scope="scope">
            <el-input v-model="keywords" size="small" placeholder="输入关键字搜索" />
          </template>
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="del(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="primary" class="addBtn" @click="dialogFormVisible = true">添加房间</el-button>
      <el-dialog title="添加房间" :visible.sync="dialogFormVisible" width="35%">
        <el-form class="dialog" label-position="right" label-width="35%">
          <el-form-item label="房间号:">
            <el-col :span="14">
              <el-input v-model="addroomId" autosize></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="请选择所属位置:">
            <el-col :span="14">
              <el-cascader v-model="addroomplace" :options="options1" size="medium"></el-cascader>
            </el-col>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "fangjian",
  data() {
    return {
      roomId: "",
      dormId: "",
      campusId: "",
      name: "",
      campusName: "",
      adddormname: "",
      addcampusname: "",
      dormName: "",
      bedNum: "",
      keywords: "",
      dialogFormVisible: false,
      list: [],
      campusList: [],
      dormList: [],
      options: [],
      options1: [],
      filter: ["", ""],
      addroomId: "",
      addroomplace: ["", ""],
      addcampusId: "",
      adddormId: ""
    };
  },
  mounted() {
    this.get_room_info();
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return "success-row";
      }
      return "";
    },
    get_room_info() {
      this.$axios
        .get("/manager/get_room_info")
        .then(successResponse => {
          this.get_campus_info();
          this.get_dorm_info();
          this.list = successResponse.data;
          var a;
          for (a in this.list) {
            this.list[a].name = this.list[a].roomId;
          }
          var name = [];
          var b;
          for (b in this.list) {
            if (name.indexOf(this.list[b].campusName) == -1) {
              name.push(this.list[b].campusName);
            }
          }
          var c;
          for (c in name) {
            this.options.push({ value: name[c], label: name[c], children: [] });
          }
          var d;
          for (d in this.list) {
            var e;
            for (e in name) {
              if (name[e] == this.list[d].campusName) {
                this.options[e].children.push({
                  value: this.list[d].dormName,
                  label: this.list[d].dormName
                });
              }
            }
          }
          var x;
          for (x in this.options) {
            var y;
            var unique = [];
            for (y in this.options[x].children) {
              if (unique.indexOf(this.options[x].children[y].value) == -1) {
                unique.push(this.options[x].children[y].value);
              } else {
                delete this.options[x].children[y];
              }
            }
          }
        })
        .catch(failResponse => {});
    },
    get_campus_info() {
      this.$axios
        .get("/manager/get_campus_info")
        .then(successResponse => {
          this.campusList = successResponse.data;
          var name = [];
          var b;
          for (b in this.campusList) {
            if (name.indexOf(this.campusList[b].name) == -1) {
              name.push(this.campusList[b].name);
            }
          }
          var c;
          for (c in name) {
            this.options1.push({
              value: name[c],
              label: name[c],
              children: []
            });
          }
        })
        .catch(failResponse => {});
    },
    get_dorm_info() {
      this.$axios
        .get("/manager/get_dorm_info")
        .then(successResponse => {
          this.dormList = successResponse.data;
          var name = [];
          var b;
          for (b in this.campusList) {
            if (name.indexOf(this.campusList[b].name) == -1) {
              name.push(this.campusList[b].name);
            }
          }
          var d;
          for (d in this.dormList) {
            var e;
            for (e in name) {
              if (name[e] == this.dormList[d].campusName) {
                this.options1[e].children.push({
                  value: this.dormList[d].dormName,
                  label: this.dormList[d].dormName
                });
              }
            }
          }
          var x;
          for (x in this.options1) {
            var y;
            var unique = [];
            for (y in this.options1[x].children) {
              if (unique.indexOf(this.options1[x].children[y].value) == -1) {
                unique.push(this.options1[x].children[y].value);
              } else {
                delete this.options1[x].children[y];
              }
            }
          }
        })
        .catch(failResponse => {});
    },
    add() {
      if (this.addroomId == "") {
        this.$message({
          message: "房间号不可为空",
          type: "error"
        });
        return;
      }
      if (this.addroomplace == "") {
        this.$message({
          message: "房间所属位置不可为空",
          type: "error"
        });
        return;
      }
      var a;
      var b;
      for (a in this.campusList) {
        for (b in this.dormList)
          if (
            this.campusList[a].name === this.addroomplace[0] &&
            this.dormList[b].dormName === this.addroomplace[1]
          ) {
            this.addcampusId = this.campusList[a].id;
            this.adddormId = this.dormList[b].id;
          }
      }
      this.$axios
        .post("/manager/add_room", {
          id: this.addroomId,
          dormId: this.adddormId,
          dormCampusId: this.addcampusId
        })
        .then(successResponse => {
          this.get_room_info();
          this.addname = "";
          this.$message({
            message: "添加成功",
            type: "success"
          });
        })
        .catch(failResponse => {
          this.$message({
            message: "添加失败，请检查网络是否稳定",
            type: "error"
          });
        });

      this.dialogFormVisible = false;
    },
    del(row) {
      const id = row.roomId;
      const campusName = row.campusName;
      const dormName = row.dormName;
      var a;
      var b;
      var dormId;
      var campusId;
      for (a in this.campusList) {
        for (b in this.dormList)
          if (
            this.campusList[a].name === campusName &&
            this.dormList[b].dormName === dormName
          ) {
            campusId = this.campusList[a].id;
            dormId = this.dormList[b].id;
          }
      }
      this.$axios
        .post("/manager/del_room", {
          id: id, //房间id
          dormId: dormId, //宿舍楼id
          campusId: campusId //校区id
        })
        .then(successResponse => {
          this.get_room_info();
          this.$message({
            message: "删除成功",
            type: "success"
          });
        })
        .catch(failResponse => {
          this.$message({
            message: "删除失败，请检查网络是否稳定",
            type: "error"
          });
        });
    },
    search(keywords) {
      return this.list.filter(item => {
        var filter0;
        var filter1;
        if (
          typeof this.filter[0] === "undefined" &&
          typeof this.filter[0] === "undefined"
        ) {
          filter0 = "";
          filter1 = "";
        } else {
          filter0 = this.filter[0];
          filter1 = this.filter[1];
        }
        if (
          item.campusName.includes(filter0) &&
          item.dormName.includes(filter1)
        ) {
          return item;
        }
      });
    }
  }
};
</script>
<style scoped>
.width {
  width: 221.4px;
  margin-bottom: 10px;
  height: 36px;
}
</style>
