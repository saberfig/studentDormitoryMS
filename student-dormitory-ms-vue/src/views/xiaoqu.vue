<template>
  <div>
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
          <td>{{ item.buildnum }}</td>
          <td>{{ item.roomnum }}</td>
          <td>{{ item.bednum }}</td>
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
        <label>
          <label>宿舍楼数:</label>
          <input type="text" v-model="buildnum" />
        </label>
        <label>
          <label>房间数:</label>
          <input type="text" v-model="roomnum" />
        </label>
        <label>
          <label>床位数:</label>
          <input type="text" v-model="bednum" />
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
      list: [
        { id: 1, name: "宏福校区", buildnum: 8, roomnum: 130, bednum: 520 },
        { id: 2, name: "沙河校区", buildnum: 12, roomnum: 195, bednum: 780 },
        { id: 3, name: "西土城校区", buildnum: 16, roomnum: 260, bednum: 1040 }
      ]
    };
  },
  methods: {
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