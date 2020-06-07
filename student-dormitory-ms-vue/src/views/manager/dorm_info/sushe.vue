<template>
  <div class="addpadding">
    <div>
      <div class="block" style="margin-bottom:20px">
        <span class="demonstration">请选择校区：</span>
        <el-cascader v-model="campusName" :options="options"></el-cascader>
      </div>

      <table class="table table-bordered table-hover table-striped">
        <thead>
          <tr>
            <th>宿舍名称</th>
            <th>所属校区</th>
            <th>房间数</th>
            <th>床位数</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in search(keywords)" :key="item.id">
            <td>{{ item.dormName }}</td>
            <td>{{ item.campusName }}</td>
            <td>{{ item.roomNum }}</td>
            <td>{{ item.bedNum }}</td>
            <td>
              <li style="list-style: none;">
                <button type="reset" class="btn btn-success" @click="modify">修改</button>
                <button type="reset" class="btn btn-danger" @click="del(item.dormId)">删除</button>
              </li>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tableTop">
      <el-button type="primary" class="pull-right" @click="dialogFormVisible = true" >添加宿舍楼</el-button>
      <el-dialog title="添加宿舍楼" :visible.sync="dialogFormVisible">
        <el-form class="dialog">
          <span>宿舍楼名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <input type="text" autocomplete="off" v-model="adddormname" class="el-input__inner width">
          <div class="block">
        <span class="demonstration">请选择所属校区：</span>
        <el-cascader v-model="addcampusname" :options="options" size="medium"></el-cascader>
      </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>
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
      addcampusname:"",
      adddormname:"",
      roomNum: "",
      bedNum: "",
      keywords: "",
      list: [],
      dialogFormVisible:false,
      options: [
        
      ]
    };
  },
  mounted() {
    this.get_dorm_info(); 
  },
  methods: {
    get_dorm_info() {
      this.$axios
        .get("/manager/get_dorm_info")
        .then(successResponse => {
          this.list = successResponse.data;
          var a;
          for (a in this.list) {
            this.list[a].id = this.list[a].campusName + this.list[a].id;
          }
          var name = [];
          var b;
          for (b in this.list) {
            if (name.indexOf(this.list[b].campusName) == -1) {
              name.push(this.list[b].campusName)
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
      if(this.adddormname==""){
        alert("宿舍楼名不可为空")
        return
      }
      if(this.addcampusname==""){
        alert("所属校区不可为空")
        return
      }
      this.dialogFormVisible = false;
    },
    del(dormId) {
      var index = this.list.findIndex(item => {
        if (item.dormId == dormId) {
          return true;
        }
      });
      this.list.splice(index, 1);
    },
    modify() {
      var index = this.list.findIndex(item => item.dormId == this.dormId);
      if (index == -1) {
        alert("无对应的宿舍");
      } else {
        this.list[index].dormName = this.dormName;
        this.list[index].roomNum = this.roomNum;
        this.list[index].bedNum = this.bedNum;
        this.list[index].campusName = this.campusName1;
      }
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          (item.dormName.includes(keywords) ||
            item.dormId.toString().includes(keywords) ||
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

<style scoped>
.width{
  width: 221.4px;
  margin-bottom:10px;
  height: 36px;
}
</style>