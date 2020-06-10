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
                <button type="reset" class="btn btn-success" @click="modify(item.dormId)">修改</button>
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
        <el-cascader v-model="addcampusname" :options="options1" size="medium"></el-cascader>
      </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改宿舍楼信息" :visible.sync="dialogFormVisible1">
        <el-form class="dialog">
          <label >宿舍楼名称:</label>
          <input type="text" v-model="modifyname">
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="modifySub">确 定</el-button>
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
      campusList:[],
      dialogFormVisible:false,
      dialogFormVisible1:false,
      modifyname:"",
      options: [],
      options1:[],
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
          this.get_campus_info()
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
    get_campus_info() {
      this.$axios
        .get("/manager/get_campus_info")
        .then(successResponse => {
          this.campusList = successResponse.data;
          var name = [];
          var b;
          for (b in this.campusList) {
            if (name.indexOf(this.campusList[b].name) == -1) {
              name.push(this.campusList[b].name)
            }
          }
          var c;
          for (c in name) {          
            this.options1.push({ value: name[c], label: name[c] });
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
    modify(id) {
      this.dialogFormVisible1=true
      this.index = this.list.findIndex(item => {
        if (item.dormId == id) {
          return true;
        }
      });
      this.modifyname=this.list[this.index].dormName
    },
    modifySub(){
      this.list[this.index].dormName=this.modifyname
      // console.log(this.modifyname)
      this.dialogFormVisible1=false
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