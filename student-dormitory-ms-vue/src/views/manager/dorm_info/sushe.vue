<template>
  <div class="addpadding">
    <div>
      <label>请选择校区：</label>
      <select v-model="campusName">
        <option class="option1">西土城校区</option>
        <option class="option1">沙河校区</option>
        <option class="option1">宏福校区</option>
      </select>

      <label class="pull-right" style="margin: 20px 0;">
        输入搜索关键字：
        <input
          type="text"
          style="border: #ccc solid 1px;border-radius: 4px;"
          v-model="keywords"
        />
      </label>

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
                <button type="reset" class="btn btn-danger" @click="del(item.dormId)">删除</button>
              </li>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tableTop">
        <label style="display: inline-block;">
          <label>
            <label>宿舍名称:</label>
            <input type="text" v-model="dormName" />
          </label>
          <label>
            <label>所属校区:</label>
            <select v-model="campusName1" style="width: 120px;">
              <option class="option1">西土城校区</option>
              <option class="option1">沙河校区</option>
              <option class="option1">宏福校区</option>
            </select>
          </label>
        </label>
        <ul class="opreating">
          <li>
            <button type="reset" class="btn btn-info" @click="add">添加</button>
          </li>
          <li>
            <button type="reset" class="btn btn-success" @click="modify">修改</button>
          </li>
        </ul>
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
      roomNum: "",
      bedNum: "",
      keywords: "",
      list: [
      ]
    };
  },
  mounted(){
    this.get_dorm_info()
  },
  methods: {
    get_dorm_info(){
      this.$axios
        .get("/manager/get_dorm_info")
        .then(successResponse => {
          this.list=successResponse.data;
          console.log(this.list);
        })
        .catch(failResponse => {});
    },
    add() {
      var index = this.list.findIndex(item => item.id == this.id);
      if (index == -1) {
        var some = {
          dormId: this.dormId,
          dormName: this.dormName,
          roomNum: this.roomNum,
          bedNum: this.bedNum,
          campusName: this.campusName1
        };
        this.list.push(some);
      } else {
        alert("该ID已存在对应宿舍");
      }
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
</style>