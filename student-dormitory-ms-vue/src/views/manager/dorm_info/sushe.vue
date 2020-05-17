<template>
  <div>
    <div>
      <label>请选择校区：</label>
      <select v-model="campus_name">
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
            <td>{{ item.dorm_name }}</td>
            <td>{{ item.campus_name }}</td>
            <td>{{ item.room_num }}</td>
            <td>{{ item.bed_num }}</td>
            <td>
              <li style="list-style: none;">
                <button type="reset" class="btn btn-danger" @click="del(item.dorm_id)">删除</button>
              </li>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="tableTop">
        <label style="display: inline-block;">
          <label>
            <label>宿舍楼ID:</label>
            <input type="text" v-model="dorm_id" />
          </label>
          <label>
            <label>宿舍名称:</label>
            <input type="text" v-model="dorm_name" />
          </label>
          <label>
            <label>所属校区:</label>
            <select v-model="campus_name1" style="width: 120px;">
              <option class="option1">西土城校区</option>
              <option class="option1">沙河校区</option>
              <option class="option1">宏福校区</option>
            </select>
          </label>
          <br />
          <label>
            <label>房间数:</label>
            <input type="text" v-model="room_num" />
          </label>
          <label>
            <label>床位数:</label>
            <input type="text" v-model="bed_num" />
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
      dorm_id: "",
      dorm_name: "",
      campus_name: "",
      campus_name1: "",
      room_num: "",
      bed_num: "",
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
          dorm_id: this.dorm_id,
          dorm_name: this.dorm_name,
          room_num: this.room_num,
          bed_num: this.bed_num,
          campus_name: this.campus_name1
        };
        this.list.push(some);
      } else {
        alert("该ID已存在对应宿舍");
      }
    },
    del(dorm_id) {
      var index = this.list.findIndex(item => {
        if (item.dorm_id == dorm_id) {
          return true;
        }
      });
      this.list.splice(index, 1);
    },
    modify() {
      var index = this.list.findIndex(item => item.dorm_id == this.dorm_id);
      if (index == -1) {
        alert("无对应的宿舍");
      } else {
        this.list[index].dorm_name = this.dorm_name;
        this.list[index].room_num = this.room_num;
        this.list[index].bed_num = this.bed_num;
        this.list[index].campus_name = this.campus_name1;
      }
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          (item.dorm_name.includes(keywords) ||
            item.dorm_id.toString().includes(keywords) ||
            item.campus_name.includes(keywords)) &&
          item.campus_name.includes(this.campus_name)
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