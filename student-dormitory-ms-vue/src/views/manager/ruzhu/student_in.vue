<template>
  <div class="addpadding">
    <div>
      <div class="tableTop">
        <label style="display: inline-block;">
          <label>
            <label>校区号:</label>
            <input type="text" v-model="campus" />
          </label>
          <label>
            <label>宿舍楼号:</label>
            <input type="text" v-model="dorm" />
          </label>
          <label>
            <label>房间号:</label>
            <input type="text" v-model="room" />
          </label>
          <br>
          <label>
            <label>床位号:</label>
            <input type="text" v-model="bed" />
          </label>
          <label>
            <label>学号:</label>
            <input type="text" v-model="student" />
          </label>
        </label>
        <div>
          <ul class="opreating">
            <li>
              <button type="reset" class="btn btn-info" @click="add">添加</button>
            </li>
            <li>
                <input type="file" style="display: inline-block" @change="importExcel($event.target)"></input>
            </li>
            <li>
                <button type="reset" class="btn btn-success" @click="upload">上传</button>
            </li>
          </ul>
        </div>
      </div>
      <label class="pull-left" style="margin: 20px 0;">
        学号搜索：
        <input
          type="text"
          style="border: #ccc solid 1px;border-radius: 4px;"
          v-model="id"
        />
      </label>

      <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
          <th>校区号</th>
          <th>宿舍楼号</th>
          <th>房间号</th>
          <th>床位号</th>
          <th>姓名</th>
          <th>学号</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in search(id)" :key="item.id">
          <td>{{ item.roomDormCampusId}}</td>
          <td>{{ item.roomDormId }}</td>
          <td>{{ item.roomId }}</td>
          <td>{{ item.bed_id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.id }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import XLSX from 'xlsx';
    export default {
        name: "student_in",
      data() {
        return {
          id: "",
          student: "",
          bed: "",
          room: "",
          dorm: "",
          campus: "",
          list: [],
          xlsxJson: []
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
              this.list=successResponse.data;
            })
            .catch(failResponse => {});
        },
        search(keywords) {
          return this.list.filter(item => {
            if (item.id.includes(keywords)){
              return item;
            }
          });
        },
        add() {
          this.$axios
            .post("/manager/add_bed",{id:this.bed,roomId:this.room,roomDormId:this.dorm,roomDormCampusId:this.campus,studentId:this.student})
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                alert("添加成功");
              }
              if (successResponse.data.code === 400) {
                alert("添加失败");
              }
              if (successResponse.data.code === 401) {
                alert("学生已存在");
              }
              this.get_student_bed();
            })
            .catch(failResponse => {});
        },
        modify() {
          //
        },
        importExcel(files) {
          let file = files.files[0] // 使用传统的input方法需要加上这一步
          const types = file.name.split('.')[1]
          const fileType = ['xlsx', 'xlc', 'xlm', 'xls', 'xlt', 'xlw', 'csv'].some(item => item === types)
          if (!fileType) {
            alert('格式错误！请重新选择')
            return
          }
          this.file2Xce(file).then(tabJson => {
            if (tabJson && tabJson.length > 0) {
              this.xlsxJson = tabJson
              console.log(this.xlsxJson[0].sheet);
            }
          })
        },
        file2Xce(file) {
          return new Promise(function(resolve, reject) {
            const reader = new FileReader()
            reader.onload = function(e) {
              const data = e.target.result
              this.wb = XLSX.read(data, {
                type: 'binary'
              })
              const result = []
              this.wb.SheetNames.forEach((sheetName) => {
                result.push({
                  sheetName: sheetName,
                  sheet: XLSX.utils.sheet_to_json(this.wb.Sheets[sheetName])
                })
              })
              resolve(result)
            }
            //reader.readAsBinaryString(file.raw)
            reader.readAsBinaryString(file) // 传统input方法
          })
        },
        upload(){
          this.$axios
            .post("/manager/add_beds",this.xlsxJson[0].sheet)
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                alert("添加成功");
              }
              if (successResponse.data.code === 400) {
                alert("添加失败");
              }
              if (successResponse.data.code === 401) {
                alert("学生已存在");
              }
              this.get_student_bed();
            })
            .catch(failResponse => {});
        },
      }
    }
</script>

<style scoped>

</style>
