<template>
  <div class="addpadding">
    <div>
      <div class="tableTop">
        <label class="pull-left" style="margin: 20px 0;">学号搜索：
          <input
            type="text"
            style="border: #ccc solid 1px;border-radius: 4px;"
            v-model="keywords"
          />
        </label>
        <el-form :inline="true" class="pull-right">
          <el-form-item>
            <el-button type="primary"
                       @click="dialogFormVisible = true" >添加</el-button>
          </el-form-item>
          <el-form-item>
            <a href="javascript:;" class="file">本地导入
              <input type="file"  @change="importExcel($event.target)" id="">
            </a>
          </el-form-item>
          <el-form-item>
          <el-button type="success"
                     @click="dialogFormVisible_dh = true ">床位调换</el-button>
          </el-form-item>
        </el-form>
      </div>

      <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
          <th>校区</th>
          <th>宿舍楼</th>
          <th>房间号</th>
          <th>房间号</th>
          <th>姓名</th>
          <th>学号</th>
          <th>性别</th>
          <th>专业</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in search(keywords)" :key="item.id">
          <td>{{ getCampusName(item.roomDormCampusId)}}</td>
          <td>{{ getDormName(item.roomDormCampusId,item.roomDormId) }}</td>
          <td>{{ item.roomId }}</td>
          <td>{{ item.bed_id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.id }}</td>
          <td>{{ item.sex }}</td>
          <td>{{ item.major }}</td>
        </tr>
        </tbody>
      </table>

      <el-dialog title="学生入住"
                 :visible.sync="dialogFormVisible"
                 width="40%">
        <el-form class="dialog" label-position="right" label-width="30%">
          <el-form-item label="校区名称:">
            <el-col :span="14">
              <el-select v-model="campus" placeholder="请选择校区名称">
                <div v-for="(item,index) in campusList" :key="index">
                  <el-option :label="item.name" :value="item.id"></el-option>
                </div>
              </el-select>
            </el-col>
          </el-form-item>
          <el-form-item label="宿舍楼名称:">
            <el-col :span="14">
              <el-select v-model="dorm" placeholder="请选择宿舍楼名称">
                <div v-for="(item,index) in dormList" :key="index">
                  <el-option :label="item.dormName" :value="item.dormName+','+item.id"></el-option>
                </div>
              </el-select>
            </el-col>
          </el-form-item>
          <el-form-item label="房间号:">
            <el-col :span="14">
              <el-select v-model="room" placeholder="请选择房间号">
                <div v-for="(item,index) in rooms" :key="index">
                  <el-option :label="item" :value="item"></el-option>
                </div>
              </el-select>
            </el-col>
          </el-form-item>
          <el-form-item label="床位号:">
            <el-col :span="14">
              <el-select v-model="bed" placeholder="请选择床位号">
                <div v-for="(item,index) in Array.from({length:4}).map((item,index)=>{return index+1;})" :key="index">
                  <el-option :label="item" :value="item"></el-option>
                </div>
              </el-select>
            </el-col>
          </el-form-item>
          <el-form-item label="学号:">
            <el-col :span="14">
              <el-input v-model="id"></el-input>
            </el-col>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="add">确 定</el-button>
          </span>
      </el-dialog>

      <el-dialog title="床位调换"
                 :visible.sync="dialogFormVisible_dh"
                 width="40%">
        <el-form class="dialog" label-position="right" label-width="30%">
          <el-form-item label="学生一学号:">
            <el-col :span="14">
              <el-input v-model="id1"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="学生二学号:">
            <el-col :span="14">
              <el-input v-model="id2"></el-input>
            </el-col>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible_dh = false">取 消</el-button>
            <el-button type="primary" @click="exchange">确 定</el-button>
          </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import XLSX from 'xlsx';
  export default {
    name: "student_in",
    data() {
      return {
        keywords: "",
        id: "",
        bed: "",
        room: "",
        dorm: "",
        campus: "",
        id1:"",
        id2:"",

        campusList: [],
        dormList: [],
        dorms: [],
        roomList: [],
        rooms: [],
        list: [],
        studentList: [],
        students:[],
        dialogFormVisible: false,
        dialogFormVisible_dh: false,
        xlsxJson: [],
        submited:true,
      };
    },
    mounted() {
      this.get_student_bed();
    },
    methods: {
      get_student_bed(){
        this.$axios
          .get("/manager/get_student_bed")
          .then(successResponse => {
            this.get_campus_info();
            this.get_dorm_info();
            this.get_room_info();
            this.get_student();
            this.list=successResponse.data;
          })
          .catch(failResponse => {
            this.$message({
              message:'获取数据失败',
              type:'error',
            });
          });
      },
      get_campus_info(){
        this.$axios
          .get("/manager/get_campus_info")
          .then(successResponse => {
            this.campusList=successResponse.data;
          })
          .catch(failResponse => {});
      },
      get_dorm_info(){
        this.$axios
          .get("/manager/get_dorm_info")
          .then(successResponse => {
            this.dormList=successResponse.data;
          })
          .catch(failResponse => {});
      },
      get_room_info(){
        this.$axios
          .get("/manager/get_room_info")
          .then(successResponse => {
            this.roomList=successResponse.data;
            for(var a in this.roomList){
              if(this.rooms.indexOf(this.roomList[a].roomId) == -1){
                this.rooms.push(this.roomList[a].roomId);
              }
            }
          })
          .catch(failResponse => {});
      },
      get_student(){
        this.$axios
          .get("/manager/get_student")
          .then(successResponse => {
            this.studentList=successResponse.data;
            for(var a in this.studentList){
              if(this.students.indexOf(this.studentList[a].id) == -1){
                this.students.push(this.studentList[a].id);
              }
            }
          })
          .catch(failResponse => {});
      },
      getCampusName(campusId){
        var temp = this.campusList.filter(item=>{
          if(item.id===campusId)
            return item;
        })
        return temp[0].name;
      },
      getDormName(campusId,dormId){
        //console.log(this.dormList)
        //return "hh";
        var temp = this.dormList.filter(item=>{
          if(item.campusName===this.getCampusName(campusId)&&item.id===dormId)
            return item;
        });
        return temp[0].dormName;
      },
      search(keywords) {
        return this.list.filter(item => {
          if (item.id.includes(keywords)){
            console.log(item.id);
            return item;
          }
        });
      },
      add() {
        if(this.campus===""){
          this.$message({
            message:'请选择校区',
            type:'warning',
          });
          this.submited=false;
        }else if(this.dorm===""){
          this.$message({
            message:'请选择宿舍楼',
            type:'warning',
          });
          this.submited=false;
        }else if(this.room===""){
          this.$message({
            message:'请选择房间',
            type:'warning',
          });
          this.submited=false;
        }else if(this.bed===""){
          this.$message({
            message:'请选择床位',
            type:'warning',
          });
          this.submited=false;
        }else if(this.id===""){
          this.$message({
            message:'请添加学号',
            type:'warning',
          });
          this.submited=false;
        }

        if(this.students.includes(this.id)){
          this.$message({
            message:'学号已存在',
            type:'warning',
          });
          this.submited=false;
        }
        if(!this.submited){
          this.submited=true;
          return false;
        }else {
          this.$axios
            .post("/manager/add_bed", {
              id: this.bed,
              roomId: this.room,
              roomDormId: this.dorm.split(',')[1],
              roomDormCampusId: this.campus,
              studentId: this.id,
            })
            .then(successResponse => {
              this.get_student_bed();
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
        this.dialogFormVisible=false;
      },
      exchange(){
        console.log(this.students);
        if(!this.students.includes(this.id1)){
          this.$message({
            message:'学生一学号不存在',
            type:'warning',
          });
          this.submited=false;
        }else if(!this.students.includes(this.id2)){
          this.$message({
            message:'学生二学号不存在',
            type:'warning',
          });
          this.submited=false;
        }

        if(!this.submited){
          this.submited=true;
          return false;
        }else {
          this.$axios
            .post("/manager/exchange", [
              {studentId: this.id1},
              {studentId: this.id2}
            ])
            .then(successResponse => {
              this.get_student_bed();
              this.$message({
                message:'床位调换成功',
                type:'success',
              });
            })
            .catch(failResponse => {
              this.$message({
                message:'床位调换失败',
                type:'error',
              });
            });
        }
        this.dialogFormVisible_dh=false;
      },
      importExcel(file1) {
        let file = file1.files[0];
        const types = file.name.split('.')[1];
        const fileType = ['xlsx', 'xlc', 'xlm', 'xls', 'xlt', 'xlw', 'csv'].some(item => item === types);
        if (!fileType) {
          alert('格式错误！请重新选择');
          return;
        }
        this.file2Xce(file).then(tabJson => {
          if (tabJson && tabJson.length > 0) {
            this.xlsxJson = tabJson;
            var list=this.xlsxJson[0].sheet;
            for(var i=0;i<list.length;i++){
              this.$axios
                .post("/manager/add_bed",{
                  id:list[i].id,
                  roomId:list[i].roomId,
                  roomDormId:list[i].roomDormId,
                  roomDormCampusId:list[i].roomDormCampusId,
                  studentId:list[i].studentId,
                })
                .then(successResponse => {
                  this.get_student_bed();
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
          }
        });
      },
      file2Xce(file) {
        return new Promise(function(resolve, reject) {
          const reader = new FileReader();
          reader.onload = function(e) {
            const data = e.target.result;
            this.wb = XLSX.read(data, {
              type: 'binary'
            });
            const result = [];
            this.wb.SheetNames.forEach((sheetName) => {
              result.push({
                sheetName: sheetName,
                sheet: XLSX.utils.sheet_to_json(this.wb.Sheets[sheetName])
              });
            });
            resolve(result);
          }
          //reader.readAsBinaryString(file.raw);
          reader.readAsBinaryString(file);
        })
      }
    }
  }
</script>

<style scoped>
  .file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 30px;
  }
  .file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
  }
  .file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
  }
</style>
