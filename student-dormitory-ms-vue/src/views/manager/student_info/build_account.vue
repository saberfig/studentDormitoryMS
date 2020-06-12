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
      <el-form :inline="true" class="pull-right">
        <el-form-item>
          <el-button type="primary"  @click="dialogFormVisible = true" >添加宿舍管理员账号</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="success"  @click="dialogFormVisible1 = true" >重置密码</el-button>
        </el-form-item>
      </el-form>
    </div>
    <table class="table table-bordered table-hover table-striped">
      <thead>
      <tr>
        <th>姓名</th>
        <th>账号</th>
        <th>性别</th>
        <th>所管理宿舍楼名称</th>
        <th>所在校区</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in search(keywords)" :key="item.id">
        <td>{{ item.name }}</td>
        <td>{{ item.id }}</td>
        <td>{{ item.sex }}</td>
        <td>{{ item.dormCampusId }}</td>
        <td>{{ item.dormId }}</td>
        <td>
          <li style="list-style: none;">
            <button type="reset" class="btn btn-success" @click="modify(item.id,item.name,item.sex,item.dormId,item.dormCampusId)">修改</button>
            <button type="reset" class="btn btn-danger" @click="del(item.id)">删除</button>
          </li>
        </td>
      </tr>
      </tbody>
    </table>

    <el-dialog title="添加宿舍管理员账号"
               :visible.sync="dialogFormVisible"
               width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="账号:">
          <el-col :span="14">
            <el-input v-model="id" placeholder="请填写账号"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="姓名:">
          <el-col :span="14">
            <el-input v-model="name" placeholder="请填写姓名"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别:">
          <el-col :span="14">
            <el-input v-model="sex" placeholder="请填写性别"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="所在校区:">
          <el-col :span="14">
            <el-input v-model="dormCampusId" placeholder="请选择所在校区"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="所管理宿舍楼名称:">
          <el-col :span="14">
            <el-input v-model="dormId" placeholder="请选择宿舍楼名称"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="add">确 定</el-button>
          </span>
    </el-dialog>

    <el-dialog title="重置密码"
               :visible.sync="dialogFormVisible1"
               width="40%">
      <el-form class="dialog"
               label-position="right"
               label-width="30%">
        <el-form-item label="宿舍管理员账号:">
          <el-col :span="14">
            <el-input v-model="id2"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="reset">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改宿舍管理员账号"
               :visible.sync="dialogFormVisible2"
               width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="账号:">
          <el-col :span="14">
            <el-input v-model="mid" :disabled="true"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="姓名:">
          <el-col :span="14">
            <el-input v-model="mname"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别:">
          <el-col :span="14">
            <el-input v-model="msex"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="所在校区:">
          <el-col :span="14">
            <el-input v-model="mdormCampusId"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="所管理宿舍楼名称:">
          <el-col :span="14">
            <el-input v-model="mdormId"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="modifySub">确 定</el-button>
          </span>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: "xiaoqu",
    data() {
      return {
        keywords:"",
        id: "",
        name: "",
        sex:"",
        dormId:"",
        dormCampusId:"",

        mid:"",
        mname:"",
        msex:"",
        mdormId:"",
        mdormCampusId:"",

        id2:"",
        ids:[],
        dialogFormVisible1: false,
        dialogFormVisible: false,
        dialogFormVisible2: false,
        list:[],
        submited:true,
      };
    },
    mounted() {
      this.get_build();
    },
    methods: {
      get_build() {
        this.$axios
          .get("/manager/get_build")
          .then(successResponse => {
            this.list = successResponse.data;
            for(var i=0;i<this.list.length;i++){
              this.ids.push(this.list[i].id);
            }
          })
          .catch(failResponse => {
            this.$message({
              message:'获取数据失败',
              type:'error',
            });
          });
      },
      reset() {
        if (this.id2 === "") {
          this.$message({
            message:'账号不能为空',
            type:'warning',
          });
          this.submited=false;
        } else if(!this.ids.includes(this.id)){
          this.$message({
            message:'该账号不存在',
            type:'warning',
          });
          this.submited=false;
        }

        if(!this.submited){
          this.submited=true;
          return false;
        }else{
          this.$axios
            .post("/manager/reset_psw_b", {
              id: this.id2,
            })
            .then(successResponse => {
              this.id2="";
              this.$message({
                message:'密码重置成功',
                type:'success',
              });
            })
            .catch(failResponse => {
              this.$message({
                message:'密码重置失败',
                type:'error',
              });
            });
        }
        this.dialogFormVisible1 = false;
      },
      add(){
        if (this.id === "") {
          this.$message({
            message:'账号不能为空',
            type:'warning',
          });
          this.submited=false;
        } else if(this.ids.includes(this.id)){
          this.$message({
            message:'该宿舍管理员账号已存在',
            type:'warning',
          });
          this.submited=false;
        }

        if(!this.submited){
          this.submited=true;
          return false;
        }else{
          this.$axios
            .post("/manager/add_build", {
              id: this.id,
              name: this.name,
              sex:this.sex,
              dormId:this.dormId,
              dormCampusId:this.dormCampusId,
              psw:this.id,
            })
            .then(successResponse => {
              this.get_build();
              this.$message({
                message:'添加成功',
                type:'success',
              });
            })
            .catch(failResponse => {
              this.$message({
                message:'添加失败',
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
            .post("/manager/del_build",{id:id})
            .then(successResponse => {
              this.get_build();
              this.$message({
                message:'删除成功',
                type:'success',
              });
            })
            .catch(failResponse => {
              this.$message({
                message:'删除失败',
                type:'error',
              });
            });
        })
          .catch(()=>{
          })
      },
      modify(id,name,sex,dormId,dormCampusId) {
        this.dialogFormVisible2=true;
        this.mid=id;
        this.mname=name;
        this.msex=sex;
        this.mdormId=dormId;
        this.mdormCampusId=dormCampusId;
      },
      modifySub(){
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
              .post("/manager/modify_build",{
                id:this.mid,
                name:this.mname,
                sex:this.msex,
                dormId:this.mdormId,
                dormCampusId:this.mdormCampusId,
              })
              .then(successResponse => {
                this.get_build();
                this.$message({
                  message:'修改成功',
                  type:'success',
                });
                this.dialogFormVisible2=false;
              })
              .catch(failResponse => {
                this.get_build();
                this.$message({
                  message:'修改失败',
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
