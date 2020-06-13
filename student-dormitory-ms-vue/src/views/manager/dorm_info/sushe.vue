<template>
  <div class="addpadding">
    <div>
      <div class="block" style="margin-bottom:20px">
        <span class="demonstration">请选择校区：</span>
        <el-cascader v-model="campusName" :options="options" clearable></el-cascader>
        <el-button type="primary" class="pull-right" @click="dialogFormVisible = true" >添加宿舍楼</el-button>
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
          <tr v-for="item in search(keywords)" :key="item.campusName+item.dormName">
            <td>{{ item.dormName }}</td>
            <td>{{ item.campusName }}</td>
            <td>{{ item.roomNum }}</td>
            <td>{{ item.bedNum }}</td>
            <td>
              <li style="list-style: none;">
                <button type="reset" class="btn btn-success" @click="modify(item.id,item.campusName)">修改</button>
                <button type="reset" class="btn btn-danger" @click="del(item.id,item.campusName)">删除</button>
              </li>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tableTop">
      <el-dialog title="添加宿舍楼" :visible.sync="dialogFormVisible">
        <el-form class="dialog">
          <span>宿舍楼名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <input type="text" autocomplete="off" v-model="adddormname" class="el-input__inner width">
          <div class="block">
        <span class="demonstration">请选择所属校区：</span>
        <el-cascader v-model="addcampusname" :options="options" size="medium" clearable></el-cascader>
      </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改宿舍楼信息" :visible.sync="dialogFormVisible1">
         <span>宿舍楼id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <input type="text" autocomplete="off" class="el-input__inner width" :placeholder="dormIdNow" :disabled="true">
        <el-form class="dialog">
          <span >宿舍楼名称:&nbsp;</span>
           <input type="text" autocomplete="off" v-model="modifyname" class="el-input__inner width" :disabled="true">
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

      addid: "",
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
      dormIdNow: "",
      campusNameNow: "",
      options: [],
      //options1:[],
    };
  },
  mounted() {
    this.get_dorm_info();
    this.get_campus_info();
  },
  methods: {
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
              name.push(this.campusList[b].name)
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
      this.addid = this.list.length
      var campusname=this.addcampusname;
      var get_campus =this.campusList.filter(item => {
        if(item.name===campusname[0]){
          return item;
        }
      });
      this.$axios
        .post("/manager/add_dorm", {
          id: this.addid,
          campusId:get_campus[0].id,
          name: this.adddormname
        })
        .then(successResponse => {
          this.get_dorm_info();
          this.addname="";
          this.$message({
            message:'添加成功',
            type:'success',
          });
        })
        .catch(failResponse => {
          this.get_dorm_info();
          this.$message({
            message:'添加失败，请检查网络是否稳定',
            type:'error',
          });
        });
      this.dialogFormVisible = false;
    },
    del(dormId,campusName) {
      var get_campus =this.campusList.filter(item => {
        if(item.name===campusName){
          return item;
        }
      });
      console.log(dormId);
      this.$axios
        .post("/manager/del_dorm",{
          id:dormId,
          campusId:get_campus[0].id
        })
        .then(successResponse => {
          this.get_dorm_info();
          this.$message({
            message:'删除成功',
            type:'success',
          });
        })
        .catch(failResponse => {
          this.get_dorm_info();
          this.$message({
            message:'删除失败，请检查网络是否稳定',
            type:'error',
          });
        });
    },
    modify(dormId,campusName) {
      this.dialogFormVisible1=true;
      console.log("ss"+dormId);
      this.dormIdNow = dormId;
      this.campusNameNow = campusName;
    },
    modifySub(){
      var campusName=this.campusNameNow;
      var get_campus =this.campusList.filter(item => {
        if(item.name===campusName){
          return item;
        }
      });
      this.$axios
        .post("/manager/modify_dorm",{
          id:this.dormIdNow,
          campusId:get_campus[0].id,
          name:this.modifyname
        })
        .then(successResponse => {
          this.get_dorm_info();
          this.$message({
            message:'修改成功',
            type:'success',
          });
          this.dialogFormVisible1=false;
        })
        .catch(failResponse => {
          this.get_campus_info();
          this.$message({
            message:'修改失败，请检查网络是否稳定',
            type:'error',
          });
        });
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
