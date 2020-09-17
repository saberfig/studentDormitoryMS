<template>
  <div class="addpadding">
    <div>
      <div class="block" style="margin-bottom:20px">
        <span class="demonstration">请选择校区：</span>
        <el-cascader v-model="campusName" :options="options" clearable></el-cascader>
      </div>
      <el-table
        :data="search(keywords)"
        style="width: 80%; margin:auto;"
        border
        :row-class-name="tableRowClassName"
      >
        <el-table-column label="宿舍楼">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.dormName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="所属校区">
          <template slot-scope="scope">
            <p>{{ scope.row.campusName }}</p>
          </template>
        </el-table-column>
        <el-table-column label="房间数">
          <template slot-scope="scope">{{ scope.row.roomNum }}</template>
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
            <el-button size="mini" type="success" @click="modify(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="del(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="primary" class="addBtn" @click="dialogFormVisible = true">添加宿舍楼</el-button>

      <el-dialog title="添加宿舍楼" :visible.sync="dialogFormVisible" width="35%">
        <el-form class="dialog" label-position="right" label-width="35%">
          <el-form-item label="宿舍楼名称:">
            <el-col :span="14">
              <el-input v-model="adddormname" autosize></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="请选择所属校区:">
            <el-col :span="14">
              <el-cascader v-model="addcampusname" :options="options" size="medium" clearable></el-cascader>
            </el-col>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="修改宿舍楼信息" :visible.sync="dialogFormVisible1" width="30%">
        宿舍楼id:
        <el-input :placeholder="dormIdNow" :disabled="true"></el-input>宿舍楼名称:
        <el-input v-model="modifyname"></el-input>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="modifySub">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "sushe",
  data() {
    return {
      dormId: "",
      dormName: "",
      campusName: "",
      campusName1: "",

      addid: "",
      addcampusname: "",
      adddormname: "",

      roomNum: "",
      bedNum: "",

      keywords: "",
      list: [],
      campusList: [],
      dialogFormVisible: false,
      dialogFormVisible1: false,
      modifyname: "",
      dormIdNow: "",
      campusNameNow: "",
      options: []
    };
  },
  mounted() {
    this.get_dorm_info();
    this.get_campus_info();
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return "success-row";
      }
      return "";
    },
    get_dorm_info() {
      this.$axios
        .get("/manager/get_dorm_info")
        .then(successResponse => {
          this.list = successResponse.data;
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
            this.options.push({ value: name[c], label: name[c] });
          }
        })
        .catch(failResponse => {});
    },

    add() {
      if (this.adddormname == "") {
        this.$message({
          message: "宿舍楼名不可为空",
          type: "error"
        });
        return;
      }
      if (this.addcampusname == "") {
        this.$message({
          message: "所属校区不可为空",
          type: "error"
        });
        return;
      }
      this.addid = this.list.length;
      var campusname = this.addcampusname;
      var get_campus = this.campusList.filter(item => {
        if (item.name === campusname[0]) {
          return item;
        }
      });
      var a;
      for (a in this.list) {
        if (
          this.list[a].campusName == campusname &&
          this.list[a].dormName == this.adddormname
        ) {
          this.$message({
            message: "添加失败，该校区下已有同名宿舍楼",
            type: "error"
          });
          return "";
        }
      }
      this.$axios
        .post("/manager/add_dorm", {
          id: this.addid,
          campusId: get_campus[0].id,
          name: this.adddormname
        })
        .then(successResponse => {
          this.get_dorm_info();
          this.addname = "";
          this.$message({
            message: "添加成功",
            type: "success"
          });
        })
        .catch(failResponse => {
          this.get_dorm_info();
          this.$message({
            message: "添加失败，请检查网络是否稳定",
            type: "error"
          });
        });
      this.dialogFormVisible = false;
    },
    del(row) {
      const dormId = row.id;
      const campusName = row.campusName;
      var get_campus = this.campusList.filter(item => {
        if (item.name === campusName) {
          return item;
        }
      });
      this.$axios
        .post("/manager/del_dorm", {
          id: dormId,
          campusId: get_campus[0].id
        })
        .then(successResponse => {
          this.get_dorm_info();
          this.$message({
            message: "删除成功",
            type: "success"
          });
        })
        .catch(failResponse => {
          this.get_dorm_info();
          this.$message({
            message: "删除失败，请保证该宿舍楼下不存在房间与床位",
            type: "error"
          });
        });
    },
    modify(row) {
      const dormId = row.id;
      const campusName = row.campusName;
      this.dialogFormVisible1 = true;
      this.dormIdNow = dormId;
      this.campusNameNow = campusName;
    },
    modifySub() {
      var campusName = this.campusNameNow;
      var get_campus = this.campusList.filter(item => {
        if (item.name === campusName) {
          return item;
        }
      });
      var a;
      for (a in this.list) {
        if (
          this.list[a].campusName === campusName &&
          this.list[a].dormName === this.modifyname
        ) {
          this.$message({
            message: "修改失败，该校区下已有同名宿舍楼",
            type: "error"
          });
          return;
        }
      }
      this.$axios
        .post("/manager/modify_dorm", {
          id: this.dormIdNow,
          campusId: get_campus[0].id,
          name: this.modifyname
        })
        .then(successResponse => {
          this.get_dorm_info();
          this.$message({
            message: "修改成功",
            type: "success"
          });
          this.dialogFormVisible1 = false;
        })
        .catch(failResponse => {
          this.get_campus_info();
          this.$message({
            message: "修改失败，请检查网络是否稳定",
            type: "error"
          });
        });
      this.dialogFormVisible1 = false;
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          (item.dormName.includes(keywords) ||
            item.campusName.includes(keywords)) &&
          item.campusName.includes(this.campusName)
        ) {
          return item;
        }
      });
    }
  }
};
</script>

<style>

</style>
