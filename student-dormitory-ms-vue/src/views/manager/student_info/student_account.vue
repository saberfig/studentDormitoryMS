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
          <el-button type="primary"  @click="dialogFormVisible = true" >添加学生账号</el-button>
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
        <th>学号</th>
        <th>性别</th>
        <th>专业</th>
        <th>入学年份</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in search(keywords)" :key="item.id">
        <td>{{ item.name }}</td>
        <td>{{ item.id }}</td>
        <td>{{ item.sex }}</td>
        <td>{{ item.major }}</td>
        <td>{{ item.yearIn }}</td>
        <td>
          <li style="list-style: none;">
            <button type="reset" class="btn btn-success" @click="modify(item.id,item.name,item.sex,item.major,item.yearIn)">修改</button>
            <button type="reset" class="btn btn-danger" @click="del(item.id)">删除</button>
          </li>
        </td>
      </tr>
      </tbody>
    </table>

    <el-dialog title="添加学生账号"
               :visible.sync="dialogFormVisible"
               width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="学号:">
          <el-col :span="14">
            <el-input v-model="id" placeholder="请填写学号"></el-input>
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
        <el-form-item label="专业:">
          <el-col :span="14">
            <el-input v-model="major" placeholder="请填写专业"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="入学年份:">
          <el-col :span="14">
            <el-input v-model="year" placeholder="请填写入学年份"></el-input>
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
        <el-form-item label="学生学号:">
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

    <el-dialog title="修改学生账号"
               :visible.sync="dialogFormVisible2"
               width="40%">
      <el-form class="dialog" label-position="right" label-width="30%">
        <el-form-item label="学号:">
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
        <el-form-item label="专业:">
          <el-col :span="14">
            <el-input v-model="mmajor"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="入学年份:">
          <el-col :span="14">
            <el-input v-model="myear"></el-input>
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
        major:"",
        year:"",

        mid:"",
        mname:"",
        msex:"",
        mmajor:"",
        myear:"",

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
      this.get_student();
    },
    methods: {
      get_student() {
        this.$axios
          .get("/manager/get_student")
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
            message:'学号不能为空',
            type:'warning',
          });
          this.submited=false;
        } else if(!this.ids.includes(this.id)){
          this.$message({
            message:'该学号不存在',
            type:'warning',
          });
          this.submited=false;
        }

        if(!this.submited){
          this.submited=true;
          return false;
        }else{
          this.$axios
            .post("/manager/reset_psw", {
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
            message:'学号不能为空',
            type:'warning',
          });
          this.submited=false;
        } else if(this.ids.includes(this.id)){
          this.$message({
            message:'该学生账号已存在',
            type:'warning',
          });
          this.submited=false;
        } else if(this.name === ""){
          this.$message({
            message:'请填写学生姓名',
            type:'warning',
          });
          this.submited=false;
        }else if(this.sex===""){
          this.$message({
            message:'请填写学生性别',
            type:'warning',
          });
          this.submited=false;
        }else if(this.major === ""){
          this.$message({
            message:'请填写学生专业',
            type:'warning',
          });
          this.submited=false;

        }else if(this.year === ""){
          this.$message({
            message:'请填写学生入学年份',
            type:'warning',
          });
          this.submited=false;

        }

        if(!this.submited){
          this.submited=true;
          return false;
        }else{
          this.$axios
            .post("/manager/add_student", {
              id: this.id,
              name: this.name,
              sex:this.sex,
              major:this.major,
              yearIn:this.year,
              psw:this.id,
            })
            .then(successResponse => {
              this.get_student();
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
            .post("/manager/del_student",{id:id})
            .then(successResponse => {
              this.get_student();
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
      modify(id,name,sex,major,year) {
        this.dialogFormVisible2=true;
        this.mid=id;
        this.mname=name;
        this.msex=sex;
        this.mmajor=major;
        this.myear=year;
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
              .post("/manager/modify_student",{
                id:this.mid,
                name:this.mname,
                sex:this.msex,
                major:this.mmajor,
                yearIn:this.myear,
              })
              .then(successResponse => {
                this.get_student();
                this.$message({
                  message:'修改成功',
                  type:'success',
                });
                this.dialogFormVisible2=false;
              })
              .catch(failResponse => {
                this.get_student();
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
