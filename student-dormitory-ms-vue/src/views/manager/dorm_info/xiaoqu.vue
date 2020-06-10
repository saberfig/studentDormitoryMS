<template>
  <div class="addpadding">
    <label class="pull-left" style="margin: 20px 0;">
      输入搜索关键字：
      <input
        type="text"
        style="border: #ccc solid 1px;border-radius: 4px;"
        v-model="keywords"
      />
    </label>
    <div class="tableTop">
      <el-button type="primary" class="pull-right" @click="dialogFormVisible = true" >添加校区</el-button>
      <el-dialog title="添加校区" :visible.sync="dialogFormVisible">
        <el-form class="dialog" label-position="right" label-width="80px">
          <el-form-item label="校区ID:">
            <el-col :span="12">
              <el-input v-model="addid"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="校区名称:">
            <el-col :span="12">
              <el-input v-model="addname"></el-input>
            </el-col>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <table class="table table-bordered table-hover table-striped">
      <thead>
        <tr>
          <th>校区ID</th>
          <th>校区名称</th>
          <th>宿舍楼数</th>
          <th>房间数</th>
          <th>床位数</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in search(keywords)" :key="item.id">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.dormNum }}</td>
          <td>{{ item.roomNum }}</td>
          <td>{{ item.bedNum }}</td>
          <td>
            <li style="list-style: none;">
              <button type="reset" class="btn btn-success" @click="modify(item.id)">修改</button>
              <button type="reset" class="btn btn-danger" @click="del(item.id)">删除</button>
            </li>
          </td>
        </tr>
      </tbody>
    </table>
<!--    <div class="tableTop">-->
<!--      <el-button type="primary" class="pull-right" @click="dialogFormVisible = true" >添加校区</el-button>-->
<!--      <el-dialog title="添加校区" :visible.sync="dialogFormVisible">-->
<!--        <el-form class="dialog">-->
<!--          <label >校区名称:</label>-->
<!--          <input type="text" v-model="addname">-->
<!--        </el-form>-->
<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button @click="dialogFormVisible = false">取 消</el-button>-->
<!--          <el-button type="primary" @click="add">确 定</el-button>-->
<!--        </div>-->
<!--      </el-dialog>-->
<!--    </div>-->
<!--      <el-dialog title="修改校区信息" :visible.sync="dialogFormVisible1">-->
<!--        <el-form class="dialog">-->
<!--          <label >校区名称:</label>-->
<!--          <input type="text" v-model="modifyname">-->
<!--        </el-form>-->
<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button @click="dialogFormVisible1 = false">取 消</el-button>-->
<!--          <el-button type="primary" @click="modifySub">确 定</el-button>-->
<!--        </div>-->
<!--      </el-dialog>-->

  </div>
</template>

<script>
export default {
  name: "xiaoqu",
  data() {
    return {
      id: "",
      index:"",
      name: "",
      buildnum: "",
      roomnum: "",
      bednum: "",
      keywords: "",
      addid: "",
      addname: "",
      modifyname:"",
      dialogFormVisible1: false,
      dialogFormVisible: false,
      formLabelWidth: "100px",
      list: []
    };
  },
  mounted() {
    this.get_campus_info();
  },
  methods: {
    get_campus_info() {
      this.$axios
        .get("/manager/get_campus_info")
        .then(successResponse => {
          this.list = successResponse.data;
        })
        .catch(failResponse => {});
    },
    add() {
      if (this.addname === "") {
        alert("校区名不能为空!");
      } else {
        this.$axios
          .post("/manager/add_campus", {
            id: this.list[this.list.length-1].id+1,
            name: this.addname
          })
          .then(successResponse => {
            this.get_campus_info();
          })
          .catch(failResponse => {});
      }
      this.dialogFormVisible = false;
    },
    del(id) {
      var index = this.list.findIndex(item => {
        if (item.id == id) {
          return true;
        }
      });
      this.list.splice(index, 1);
    },
    modify(id) {
      this.dialogFormVisible1=true
      this.index = this.list.findIndex(item => {
        if (item.id == id) {
          return true;
        }
      });
      this.modifyname=this.list[this.index].name
    },
    modifySub(){
      this.list[this.index].name=this.modifyname
      // console.log(this.modifyname)
      this.dialogFormVisible1=false
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
    },
    test() {
      console.log(this.list.length);
    }
  }
};
</script>

<style scoped>
.dialog{
  text-align: center;
  margin: auto;
}
.dialog label{
  font-weight: 400;
  font-size: 20px;
  margin-right: 10px;
}
.dialog input{
  width: 200px;
  height: 23px;
}
</style>
