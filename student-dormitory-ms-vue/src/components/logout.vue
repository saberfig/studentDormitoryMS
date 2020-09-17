<template>
  <div>
   <el-dialog title="" :visible.sync="dialogFormVisible" width="20%" :before-close="cancel">
        <el-form class="dialog">
          <span style="font-size:20px;font-weight:500;">退出登录？</span>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="clearUser">确 定</el-button>
        </div>
      </el-dialog>
  </div>
</template>
<script>
    export default {
      name: "info",
      data() {
        return {
            dialogFormVisible: true
        };
      },
      methods:{
        cancel(){
          this.$emit('cancel', '1');
        },
        clearUser() {
          this.COMMON.clearUser();
          this.$router.replace("/NewLogin");
    },
      }
    }
</script>

<style scoped>

</style>
