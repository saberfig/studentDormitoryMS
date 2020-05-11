<template>
  <div>
    <dvi>info</dvi>
    <div>
      <div>
        <table width="100%" class="templatemo-table-fj">
          <caption>
            <h2>个人住宿信息</h2>
          </caption>
          <thead>
          <tr>
            <th>校区</th>
            <th>寝室楼号</th>
            <th>房间号</th>
            <th>床号</th>
          </tr>
          </thead>
          <tr>
            <td>{{this.responsestudentinfo.campusname}}</td>
            <td>{{this.responsestudentinfo.dormnum}}号公寓</td>
            <td>{{this.responsestudentinfo.roomnum}}</td>
            <td>{{this.responsestudentinfo.bednum}}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>


</template>

<script>
    export default {
      name: "info",
      data() {
        return {
          request: {
            userid: this.COMMON.id,
          },
          responsestudentinfo: {
            campusname: "test西土城校区",
            dormnum: "test9",
            roomnum: "test117",
            bednum: "test1",
          },
          responseResult: []
        };
      },
      mounted:function(){
        this.$axios
          .post("/studentinfo", {
            id: this.COMMON.id,
          })
          .then(successResponse => {
            this.responsestudentinfo.campusname = successResponse.data.campusname,
              this.responsestudentinfo.dormnum = successResponse.data.dormnum,
              this.responsestudentinfo.roomnum = successResponse.data.roomnum,
              this.responsestudentinfo.bednum = successResponse.data.bednum

          })
          .catch(failResponse => {
          });
      }

      /*
            this.$axios
        .post("/studentinfo", {
          id: this.COMMON.id,
        })
        .then(successResponse => {
          this.responsestudentinfo.campusname = successResponse.data.campusname,
            this.responsestudentinfo.dormnum = successResponse.data.dormnum,
            this.responsestudentinfo.roomnum = successResponse.data.roomnum,
            this.responsestudentinfo.bednum = successResponse.data.bednum

        })
        .catch(failResponse => {
        });
       */



    }
</script>

<style scoped>

</style>
