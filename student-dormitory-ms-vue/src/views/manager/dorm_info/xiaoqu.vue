<template>
  <div class="addpadding">
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
              <button type="reset" class="btn btn-danger" @click="del(item.id)">删除</button>
            </li>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="tableTop">
      <label style="display: inline-block;">
        <label>
          <label>校区ID:</label>
          <input type="text" v-model="id" />
        </label>
        <label>
          <label>校区名称:</label>
          <input type="text" v-model="name" />
        </label>
        <br>
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
</template>

<script>
export default {
  name: "xiaoqu",
  data() {
    return {
      id: "",
      name: "",
      buildnum: "",
      roomnum: "",
      bednum: "",
      keywords: "",
      list: []
    };
  },
  mounted() {
    this.get_campus_info();
  },
  methods: {
    get_campus_info(){
      this.$axios
        .get("/manager/get_campus_info")
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
          id: this.id,
          name: this.name,
          buildnum: this.buildnum,
          roomnum: this.roomnum,
          bednum: this.bednum
        };
        this.list.push(some);
      } else {
        alert("该ID已存在对应校区");
      }
    },
    del(id) {
      var index = this.list.findIndex(item => {
        if (item.id == id) {
          return true;
        }
      });
      this.list.splice(index, 1);
    },
    modify() {
      var index = this.list.findIndex(item => item.id == this.id);
      if (index == -1) {
        alert("无对应的校区");
      } else {
        this.list[index].name = this.name;
        this.list[index].buildnum = this.buildnum;
        this.list[index].roomnum = this.roomnum;
        this.list[index].bednum = this.bednum;
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
    }
  }
};
</script>

<style scoped>
</style>
