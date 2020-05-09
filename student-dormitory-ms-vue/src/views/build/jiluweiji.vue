<template>
  <div>
			<div>
				<table width="100%" class="templatemo-table-fj">
					<caption>
						<h2>
							违纪信息</h2>
					</caption>
					<thead>
					<tr>
						<th>学生姓名</th>
						<th>学号</th>
						<th>详细信息</th>
					</tr>
					</thead>
					<tr>
						<td>张三</td>
						<td>1111111111</td>
						<td>于2020.9.9 打破学九大门</td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button">登记违纪信息</button>
			</div>
			<div>
				<form class="templatemo-form-weiji">
					<h2>
						违纪信息登记</h2>
					学生姓名:<br><input type="text" class="templatemo-input-weiji"><br>
					学号：<br><input type="text" class="templatemo-input-weiji"><br>
					详细描述：<br>
					<textarea rows="10" cols="30" class="templatemo-textarea-weiji">请输入详细信息:</textarea><br>
					<button type="primary" @click="onSubmit">提交违纪信息</button><br>
				</form>
			</div>
		</div>
</template>

<script>
export default {
  name: "jiluweiji_build"
};
</script>

<style scoped>
</style>
