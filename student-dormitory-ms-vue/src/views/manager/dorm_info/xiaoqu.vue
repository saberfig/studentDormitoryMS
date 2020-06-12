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
              <button type="reset" class="btn btn-success" @click="modify(item.id,item.name)">修改</button>
              <button type="reset" class="btn btn-danger" @click="del(item.id)">删除</button>
            </li>
          </td>
        </tr>
      </tbody>
    </table>

    <el-dialog title="添加校区"
               :visible.sync="dialogFormVisible"
               width="40%">
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

    <el-dialog title="修改校区信息"
               :visible.sync="dialogFormVisible1"
               width="40%">
      <el-form class="dialog"
               label-position="right"
               label-width="30%">
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
      index:"",

      name: "",
      buildnum: "",
      roomnum: "",
      bednum: "",

      keywords: "",
      addid: "",
      addname: "",
      modifyid: "",
      modifyname:"",

      dialogFormVisible1: false,
      dialogFormVisible: false,
      list: [],
      ids:[],
      names: [],
      submited: true,
    };
  },
  mounted() {
    this.get_campus_info();
  },
  updated(){
    if(this.list){
      this.addid=Number(this.list[this.list.length-1].id)+1;
      var length=this.list.length;
      for(var i=0;i<length;i++){
        this.ids.push(this.list[i].id);
        this.names.push(this.list[i].name);
      }
    }
  },
  methods: {
    get_campus_info() {
      this.$axios
        .get("/manager/get_campus_info")
        .then(successResponse => {
          this.list = successResponse.data;
          console.log(this.list);
        })
        .catch(failResponse => {
          this.$message({
            message:'数据获取失败，请检查网络是否稳定',
            type:'error',
          });
        });
    },
    add() {
      if (this.addname === "") {
        this.$message({
          message:'校区名称不能为空',
          type:'warning',
        });
        this.submited=false;
      } else if(this.names.includes(this.addname)){
        this.$message({
          message:'校区名称已存在',
          type:'warning',
        });
        this.submited=false;
      }

      if(!this.submited){
        this.submited=true;
        return false;
      }else{
        this.$axios
          .post("/manager/add_campus", {
            id: this.addid,
            name: this.addname
          })
          .then(successResponse => {
            this.get_campus_info();
            this.addname="";
            this.$message({
              message:'添加成功',
              type:'success',
            });
          })
          .catch(failResponse => {
            this.get_campus_info();
            this.$message({
              message:'添加失败，请检查网络是否稳定',
              type:'error',
            });
          });
      }
      this.dialogFormVisible = false;
    },
    del(id) {
      this.$confirm('此操作将永久删除该数据，是否继续？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning',
      }).then(()=>{
        this.$axios
          .post("/manager/del_campus",{id:id})
          .then(successResponse => {
            this.get_campus_info();
            this.$message({
              message:'删除成功',
              type:'success',
            });
          })
          .catch(failResponse => {
            this.get_campus_info();
            this.$message({
              message:'删除失败，请保证该校区下不存在任何宿舍楼与房间',
              type:'error',
            });
          });
      })
      .catch(()=>{
      })
    },
    modify(id,name) {
      this.dialogFormVisible1=true
      this.modifyid=id;
      this.modifyname=name;
    },
    modifySub(){
      if(this.names.includes(this.modifyname)){
        this.$message({
          message:'校区名称已存在',
          type:'warning',
        });
        this.submited=false;
      }
      if(!this.submited) {
        this.submited = true;
        return false;
      }
      else{
        this.$confirm('此操作将永久修改该数据，是否继续？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning',
        }).then(()=>{
          this.$axios
            .post("/manager/modify_campus",{id:this.modifyid,name:this.modifyname})
            .then(successResponse => {
              this.get_campus_info();
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
        })
        .catch(()=>{
        });
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
    },
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
