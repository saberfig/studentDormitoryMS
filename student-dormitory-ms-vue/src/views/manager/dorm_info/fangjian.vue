<template>
  <div>
    <div>
      <label>请选择校区：</label>
      <select v-model="campusbelong">
        <option class="option1">西土城校区</option>
        <option class="option1">沙河校区</option>
        <option class="option1">宏福校区</option>
      </select>

      <label>请选择宿舍楼：</label>
      <select v-model="buildbelong">
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
            <th>房间ID</th>
            <th>所属校区</th>
            <th>所属宿舍楼</th>
            <th>房间号</th>
            <th>床位数</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in search(keywords)" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.campusbelong }}</td>
            <td>{{ item.buildbelong }}</td>
            <td>{{ item.name }}</td>
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
            <label>房间楼ID:</label>
            <input type="text" v-model="id" />
          </label>
          <label>
            <label>所属校区:</label>
            <select v-model="campusbelong1" style="width: 120px;">
              <option class="option1">西土城校区</option>
              <option class="option1">沙河校区</option>
              <option class="option1">宏福校区</option>
            </select>
          </label>
          <br />
          <label>
            <label>所属宿舍楼:</label>
            <select v-model="buildbelong1" style="width: 120px;">
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
          </label>
          <label>
            <label>房间号:</label>
            <input type="text" v-model="name" />
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
  </div>
</template>

<script>
export default {
  name: "fangjian",
  data() {
    return {
      id: "",
      name: "",
      campusbelong: "",
      campusbelong1: "",
      buildbelong: "",
      buildbelong1: "",
      floornum: "",
      roomnum: "",
      bednum: "",
      keywords: "",
      list: [
        {
          id: 1,
          bednum: 4,
          name: "1-101",
          campusbelong: "西土城校区",
          buildbelong: "学一"
        },
        {
          id: 2,
          bednum: 4,
          name: "1-102",
          campusbelong: "西土城校区",
          buildbelong: "学一"
        },
        {
          id: 3,
          bednum: 4,
          name: "2-203",
          campusbelong: "西土城校区",
          buildbelong: "学二"
        },
        {
          id: 4,
          bednum: 4,
          name: "2-506",
          campusbelong: "西土城校区",
          buildbelong: "学二"
        },
        {
          id: 5,
          bednum: 4,
          name: "9-101",
          campusbelong: "西土城校区",
          buildbelong: "学九"
        },
        {
          id: 6,
          bednum: 4,
          name: "9-301",
          campusbelong: "西土城校区",
          buildbelong: "学九"
        },
        {
          id: 7,
          bednum: 4,
          name: "9-302",
          campusbelong: "西土城校区",
          buildbelong: "学九"
        },
        {
          id: 8,
          bednum: 4,
          name: "A503",
          campusbelong: "沙河校区",
          buildbelong: "雁北楼"
        },
        {
          id: 9,
          bednum: 4,
          name: "B412",
          campusbelong: "沙河校区",
          buildbelong: "雁北楼"
        },
        {
          id: 10,
          bednum: 4,
          name: "1-204",
          campusbelong: "宏福校区",
          buildbelong: "学一"
        },
        {
          id: 11,
          bednum: 4,
          name: "1-601",
          campusbelong: "宏福校区",
          buildbelong: "学一"
        },
        {
          id: 12,
          bednum: 4,
          name: "2-310",
          campusbelong: "宏福校区",
          buildbelong: "学二"
        }
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
          bednum: this.bednum,
          campusbelong: this.campusbelong1,
          buildbelong: this.buildbelong1
        };
        this.list.push(some);
      } else {
        alert("该ID已存在对应房间");
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
        alert("无对应的房间");
      } else {
        this.list[index].name = this.name;
        this.list[index].bednum = this.bednum;
        this.list[index].buildbelong = this.buildbelong1;
        this.list[index].campusbelong = this.campusbelong1;
      }
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          (item.buildbelong.includes(keywords) ||
            item.campusbelong.includes(keywords) ||
            item.id.toString().includes(keywords) ||
            item.name.includes(keywords)) &&
          item.campusbelong.includes(this.campusbelong) &&
          item.buildbelong.includes(this.buildbelong)
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