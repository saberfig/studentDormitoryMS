<template>
  <div class="resetpsw">
			<div>
				<label>重置密码目标的学号：</label>
				<input type="text">
			</div>
			<div>
				<label>重置后的密码</label>
				<input type="text">
			</div>
			
			<button type="reset" class="btn btn-success" data-toggle="modal" data-target="#reset">修改</button>
		</div>
</template>

<script>
  export default {
    name: 'resetpsw'
  }
</script>

<style scoped>
</style>