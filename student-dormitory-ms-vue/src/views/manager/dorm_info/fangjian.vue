<template>
  <div>
    <div>
      <label>请选择校区：</label>
      <select v-model="campusName">
        <option class="option1">西土城校区</option>
        <option class="option1">沙河校区</option>
        <option class="option1">宏福校区</option>
      </select>

      <label>请选择宿舍楼：</label>
      <select v-model="dormName">
        <option class="option1">学一</option>
        <option class="option1">学二</option>
        <option class="option1">学三</option>
        <option class="option1">学四</option>
        <option class="option1">学五</option>
        <option class="option1">学六</option>
        <option class="option1">学七</option>
        <option class="option1">学八</option>
        <option class="option1">学九</option>
        <option class="option1">学十</option>
        <option class="option1">学十一</option>
        <option class="option1">学十二</option>
        <option class="option1">学二十九</option>
        <option class="option1">雁北楼</option>
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
            <th>房间号</th>
            <th>所属校区</th>
            <th>所属宿舍楼</th>
            <th>床位数</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in search(keywords)" :key="item.roomId">
            <td>{{ item.name }}</td>
            <td>{{ item.campusName }}</td>
            <td>{{ item.dormName }}</td>
            <td>{{ item.bedNum }}</td>
            <td>
              <li style="list-style: none;">
                <button type="reset" class="btn btn-danger" @click="del(item.roomId)">删除</button>
              </li>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tableTop">
        <label style="display: inline-block;">
          <label>
            <label>房间ID:</label>
            <input type="text" v-model="roomId" />
          </label>
          <label>
            <label>所属校区:</label>
            <select v-model="campusName1" style="width: 120px;">
              <option class="option1">西土城校区</option>
              <option class="option1">沙河校区</option>
              <option class="option1">宏福校区</option>
            </select>
          </label>
          <br />
          <label>
            <label>所属宿舍楼:</label>
            <select v-model="dormName1" style="width: 120px;">
              <option class="option1">学一</option>
              <option class="option1">学二</option>
              <option class="option1">学三</option>
              <option class="option1">学四</option>
              <option class="option1">学五</option>
              <option class="option1">学六</option>
              <option class="option1">学七</option>
              <option class="option1">学八</option>
              <option class="option1">学九</option>
              <option class="option1">学十</option>
              <option class="option1">学十一</option>
              <option class="option1">学十二</option>
              <option class="option1">学二十九</option>
              <option class="option1">雁北楼</option>
              <option class="option1">雁南楼</option>
            </select>
          </label>
          <label>
            <label>房间号:</label>
            <input type="text" v-model="name" />
          </label>
          <label>
            <label>床位数:</label>
            <input type="text" v-model="bedNum" />
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
  name: "fangjian",
  data() {
    return {
      roomId: "",
      dormId: "",
      campusID: "",
      name: "",
      campusName: "",
      campusName1: "",
      dormName: "",
      dormName1: "",
      bedNum: "",
      keywords: "",
      list: []
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
          this.list = successResponse.data;
          var a;
          for (a in this.list) {
            this.list[a].name =
              this.list[a].campusId+
              "-" +
              this.list[a].dormId +
              "-" +
              this.list[a].roomId;
          }
        })
        .catch(failResponse => {});
    },
    add() {
      var index = this.list.findIndex(item => item.id == this.id);
      if (index == -1) {
        var some = {
          roomId: this.roomId,
          name: this.campusID + "-" + this.dormId + "-" + this.roomId,
          bedNum: this.bedNum,
          campusName: this.campusName1,
          dormName: this.dormName1
        };
        this.list.push(some);
      } else {
        alert("该ID已存在对应房间");
      }
    },
    del(id) {
      var index = this.list.findIndex(item => {
        if (item.roomId == id) {
          return true;
        }
      });
      this.list.splice(index, 1);
    },
    modify() {
      var index = this.list.findIndex(item => item.roomId == this.roomId);
      if (index == -1) {
        alert("无对应的房间");
      } else {
        this.list[index].name = this.name;
        this.list[index].bedNum = this.bedNum;
        this.list[index].dormName = this.dormName1;
        this.list[index].campusName = this.campusName1;
      }
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          (item.dormName.includes(keywords) ||
            item.campusName.includes(keywords) ||
            item.roomId.toString().includes(keywords) ||
            item.name.includes(keywords)) &&
          item.campusName.includes(this.campusName) &&
          item.dormName.includes(this.dormName)
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