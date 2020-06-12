<template>
  <div class="addpadding">
    <div>
      <div class="block" style="margin-bottom:20px">
        <span class="demonstration">请选择所属位置：</span>
        <el-cascader v-model="filter" :options="options"></el-cascader>
      </div>
      <table class="table table-bordered table-hover table-striped">
        <thead>
          <tr>
            <th>房间号</th>
            <th>所属校区</th>
            <th>所属宿舍楼</th>
            <th>床位数</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in search(keywords)" :key="item.campusName+item.dormName+item.name">
            <td>{{ item.name }}</td>
            <td>{{ item.campusName }}</td>
            <td>{{ item.dormName }}</td>
            <td>{{ item.bedNum }}</td>
            <td>
              <li style="list-style: none;">
                <!-- <button type="reset" class="btn btn-success" @click="modify">修改</button> -->
                <button type="reset" class="btn btn-danger" @click="del(item.name)">删除</button>
              </li>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tableTop">
        <el-button type="primary" class="pull-right" @click="dialogFormVisible = true">添加房间</el-button>
        <el-dialog title="添加宿舍楼" :visible.sync="dialogFormVisible">
          <el-form class="dialog">
            <span>房间号: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <input
              type="text"
              autocomplete="off"
              v-model="addroomId"
              class="el-input__inner width"
            />
            <div class="block">
        <span class="demonstration">请选择所属位置：</span>
        <el-cascader v-model="addroomplace" :options="options1"></el-cascader>
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
  name: "fangjian",
  data() {
    return {
      roomId: "",
      dormId: "",
      campusId: "",
      name: "",
      campusName: "",
      adddormname: "",
      addcampusname: "",
      dormName: "",
      bedNum: "",
      keywords: "",
      dialogFormVisible: false,
      list: [],
      campusList:[],
      dormList:[],
      options: [],
      options1:[],
      filter: ["",""],
      addroomId:"",
      addroomplace:"",
    };
  },
  mounted() {
    this.get_room_info();
  },
  methods: {
    get_room_info() {
      this.$axios
        .get("/manager/get_room_info")
        .then(successResponse => {
          this.get_campus_info()
          this.get_dorm_info()
          this.list = successResponse.data;
          var a;
          for (a in this.list) {
            this.list[a].name =
              this.list[a].campusId +
              "-" +
              this.list[a].dormId +
              "-" +
              this.list[a].roomId;
          }
          var name = [];
          var b;
          for (b in this.list) {
            if (name.indexOf(this.list[b].campusName) == -1) {
              name.push(this.list[b].campusName);
            }
          }
          var c;
          for (c in name) {
            this.options.push({ value: name[c], label: name[c],children:[]});
          }
          var d;
          for (d in this.list) {
            var e;
            for (e in name) {
              if (name[e] == this.list[d].campusName) {
                this.options[e].children.push({
                  value: this.list[d].dormName,
                  label: this.list[d].dormName
                });
              }
            }
          }
          var x
          for (x in this.options) {
            var y
            var unique = []
            for(y in this.options[x].children){
              if(unique.indexOf(this.options[x].children[y].value)==-1){
                unique.push(this.options[x].children[y].value)
              }
              else{
                delete this.options[x].children[y]
              }
            }
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
            this.options1.push({ value: name[c], label: name[c],children:[] });
          }
        })
        .catch(failResponse => {});
    },
    get_dorm_info() {
      this.$axios
        .get("/manager/get_dorm_info")
        .then(successResponse => {
          this.dormList = successResponse.data;
          var name = [];
          var b;
          for (b in this.campusList) {
            if (name.indexOf(this.campusList[b].name) == -1) {
              name.push(this.campusList[b].name)
            }
          }
          var d;
          for (d in this.dormList) {
            var e;
            for (e in name) {
              if (name[e] == this.dormList[d].campusName) {
                this.options1[e].children.push({
                  value: this.dormList[d].dormName,
                  label: this.dormList[d].dormName
                });
              }
            }
          }
          var x
          for (x in this.options1) {
            var y
            var unique = []
            for(y in this.options1[x].children){
              if(unique.indexOf(this.options1[x].children[y].value)==-1){
                unique.push(this.options1[x].children[y].value)
              }
              else{
                delete this.options1[x].children[y]
              }
            }
          }
        })
        .catch(failResponse => {});
    },
    add() {
      if(this.addroomId==""){
        alert("房间号不可为空")
        return
      }
      if(this.addroomplace=="")
      {
        alert("房间所属位置不可为空")
        return
      }

      this.$axios
        .post("/manager/add_room", {
          id: this.addid,//房间id
          dormId: this.addname,//宿舍楼id
          dormCampusId:""//校区id
        })
        .then(successResponse => {
          this.get_room_info();
          this.addname="";
          this.$message({
            message:'添加成功',
            type:'success',
          });
        })
        .catch(failResponse => {
          this.$message({
            message:'添加失败，请检查网络是否稳定',
            type:'error',
          });
        });

      this.dialogFormVisible = false;
    },
    del(id) {
      this.$axios
        .post("/manager/del_room",{
          id:id,//房间id
          dormId:"",//宿舍楼id
          campusId: "",//校区id
        })
        .then(successResponse => {
          this.get_room_info();
          this.$message({
            message:'删除成功',
            type:'success',
          });
        })
        .catch(failResponse => {
          this.$message({
            message:'删除失败，请检查网络是否稳定',
            type:'error',
          });
        });
    },
    // modify() {
    //   var index = this.list.findIndex(item => item.roomId == this.roomId);
    //   if (index == -1) {
    //     alert("无对应的房间");
    //   } else {
    //     this.list[index].name = this.name;
    //     this.list[index].bedNum = this.bedNum;
    //     this.list[index].dormName = this.dormName1;
    //     this.list[index].campusName = this.campusName1;
    //   }
    // },
    search(keywords) {
      return this.list.filter(item => {
        var filter0
        var filter1
        if(typeof(this.filter[0])==='undefined'&&typeof(this.filter[0])==='undefined'){
          filter0=""
          filter1=""
        }
        else{
          filter0=this.filter[0]
          filter1=this.filter[1]
        }
        console.log(filter0,filter1)
        if (
          item.campusName.includes(filter0) &&
          item.dormName.includes(filter1)
        ) {
          return item;
        }
      });
    },
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
