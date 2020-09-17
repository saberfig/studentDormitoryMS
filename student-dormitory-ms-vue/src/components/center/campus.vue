<template>
  <div class="addpadding">
    <el-table
      :data="search(keywords)"
      style="width: 80%; margin:auto;"
      border
      :row-class-name="tableRowClassName"
    >
      <el-table-column label="校区ID">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="校区名称">
        <template slot-scope="scope">
          <p>{{ scope.row.name }}</p>
        </template>
      </el-table-column>
      <el-table-column label="宿舍楼数">
        <template slot-scope="scope">{{ scope.row.dormNum }}</template>
      </el-table-column>
      <el-table-column label="房间数">
        <template slot-scope="scope">
          <p>{{ scope.row.roomNum }}</p>
        </template>
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
    <el-button type="primary" class="addBtn" @click="dialogFormVisible = true">添加校区</el-button>
    <el-dialog title="添加校区" :visible.sync="dialogFormVisible" width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="校区ID:">
          <el-col :span="14">
            <el-input v-model="addid" :disabled="true"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="校区名称:">
          <el-col :span="14">
            <el-input v-model="addname" placeholder="请填写校区名称"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改校区信息" :visible.sync="dialogFormVisible1" width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="校区ID:">
          <el-col :span="14">
            <el-input v-model="modifyid" :disabled="true"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="校区名称:">
          <el-col :span="14">
            <el-input v-model="modifyname"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="modifySub()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "xiaoqu",
  data() {
    return {
      id: "",
      index: "",

      name: "",
      buildnum: "",
      roomnum: "",
      bednum: "",

      keywords: "",
      addid: "",
      addname: "",
      modifyid: "",
      modifyname: "",

      dialogFormVisible1: false,
      dialogFormVisible: false,
      list: [],
      ids: [],
      names: [],
      submited: true
    };
  },
  mounted() {
    this.get_campus_info();
  },
  updated() {
    if (this.list) {
      this.addid = Number(this.list[this.list.length - 1].id) + 1;
      var length = this.list.length;
      for (var i = 0; i < length; i++) {
        this.ids.push(this.list[i].id);
        this.names.push(this.list[i].name);
      }
    }
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return "success-row";
      }
      return "";
    },
    get_campus_info() {
      this.$axios
        .get("/manager/get_campus_info")
        .then(successResponse => {
          this.list = successResponse.data;
        })
        .catch(failResponse => {
          this.$message({
            message: "数据获取失败，请检查网络是否稳定",
            type: "error"
          });
        });
    },
    add() {
      if (this.addname === "") {
        this.$message({
          message: "校区名称不能为空",
          type: "warning"
        });
        this.submited = false;
      } else if (this.names.includes(this.addname)) {
        this.$message({
          message: "校区名称已存在",
          type: "warning"
        });
        this.submited = false;
      }

      if (!this.submited) {
        this.submited = true;
        return false;
      } else {
        this.$axios
          .post("/manager/add_campus", {
            id: this.addid,
            name: this.addname
          })
          .then(successResponse => {
            this.get_campus_info();
            this.addname = "";
            this.$message({
              message: "添加成功",
              type: "success"
            });
          })
          .catch(failResponse => {
            this.get_campus_info();
            this.$message({
              message: "添加失败，请检查网络是否稳定",
              type: "error"
            });
          });
      }
      this.dialogFormVisible = false;
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
            .post("/manager/del_campus", { id: id })
            .then(successResponse => {
              this.get_campus_info();
              this.$message({
                message: "删除成功",
                type: "success"
              });
            })
            .catch(failResponse => {
              this.get_campus_info();
              this.$message({
                message: "删除失败，请保证该校区下不存在任何宿舍楼与房间",
                type: "error"
              });
            });
        })
        .catch(() => {});
    },
    modify(row) {
      this.dialogFormVisible1 = true;
      this.modifyid = row.id;
      this.modifyname = row.name;
    },
    modifySub() {
      if (this.names.includes(this.modifyname)) {
        this.$message({
          message: "校区名称已存在",
          type: "warning"
        });
        this.submited = false;
      }
      if (!this.submited) {
        this.submited = true;
        return false;
      } else {
        this.$confirm("此操作将永久修改该数据，是否继续？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            this.$axios
              .post("/manager/modify_campus", {
                id: this.modifyid,
                name: this.modifyname
              })
              .then(successResponse => {
                this.get_campus_info();
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
          })
          .catch(() => {});
      }
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          item.name.includes(keywords) ||
          item.id.toString().includes(keywords)
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
