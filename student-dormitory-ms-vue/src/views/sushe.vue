<template>
  <div>
    <div>
      <label>请选择校区：</label>
      <select v-model="campusbelong">
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
            <th>宿舍楼ID</th>
            <th>宿舍名称</th>
            <th>所属校区</th>
            <th>宿舍楼层数</th>
            <th>房间数</th>
            <th>床位数</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in search(keywords)" :key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.campusbelong }}</td>
            <td>{{ item.floornum }}</td>
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
            <label>宿舍楼ID:</label>
            <input type="text" v-model="id" />
          </label>
          <label>
            <label>宿舍名称:</label>
            <input type="text" v-model="name" />
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
            <label>宿舍楼层数:</label>
            <input type="text" v-model="floornum" />
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
  </div>
</template>

<script>
export default {
  name: "sushe",
  data() {
    return {
      id: "",
      name: "",
      campusbelong: "",
      campusbelong1: "",
      floornum: "",
      roomnum: "",
      bednum: "",
      keywords: "",
      list: [
        {
          id: 1,
          name: "学一",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "西土城校区"
        },
        {
          id: 2,
          name: "学二",
          floornum: 12,
          roomnum: 195,
          bednum: 780,
          campusbelong: "西土城校区"
        },
        {
          id: 3,
          name: "学三",
          floornum: 16,
          roomnum: 260,
          bednum: 1040,
          campusbelong: "西土城校区"
        },
        {
          id: 4,
          name: "学四",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "西土城校区"
        },
        {
          id: 5,
          name: "学五",
          floornum: 12,
          roomnum: 195,
          bednum: 780,
          campusbelong: "西土城校区"
        },
        {
          id: 6,
          name: "学六",
          floornum: 16,
          roomnum: 260,
          bednum: 1040,
          campusbelong: "西土城校区"
        },
        {
          id: 7,
          name: "学七",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "西土城校区"
        },
        {
          id: 8,
          name: "学八",
          floornum: 12,
          roomnum: 195,
          bednum: 780,
          campusbelong: "西土城校区"
        },
        {
          id: 9,
          name: "学九",
          floornum: 16,
          roomnum: 260,
          bednum: 1040,
          campusbelong: "西土城校区"
        },
        {
          id: 10,
          name: "学十",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "西土城校区"
        },
        {
          id: 11,
          name: "学十一",
          floornum: 12,
          roomnum: 195,
          bednum: 780,
          campusbelong: "西土城校区"
        },
        {
          id: 12,
          name: "学十二",
          floornum: 16,
          roomnum: 260,
          bednum: 1040,
          campusbelong: "西土城校区"
        },
        {
          id: 13,
          name: "学二十九",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "西土城校区"
        },
        {
          id: 14,
          name: "雁北楼",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "沙河校区"
        },
        {
          id: 15,
          name: "学一楼",
          floornum: 8,
          roomnum: 130,
          bednum: 520,
          campusbelong: "宏福校区"
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
          floornum: this.floornum,
          roomnum: this.roomnum,
          bednum: this.bednum,
          campusbelong: this.campusbelong1
        };
        this.list.push(some);
      } else {
        alert("该ID已存在对应宿舍");
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
        alert("无对应的宿舍");
      } else {
        this.list[index].name = this.name;
        this.list[index].floornum = this.floornum;
        this.list[index].roomnum = this.roomnum;
        this.list[index].bednum = this.bednum;
        this.list[index].campusbelong = this.campusbelong1;
      }
    },
    search(keywords) {
      return this.list.filter(item => {
        if (
          (item.name.includes(keywords) ||
            item.id.toString().includes(keywords) ||
            item.campusbelong.includes(keywords)) &&
          item.campusbelong.includes(this.campusbelong)
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