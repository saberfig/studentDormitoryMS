webpackJsonp([2],{"61y9":function(t,e){},"EH+b":function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"content"},[i("div",{staticClass:"title"},[t._v("公告详情")]),t._v(" "),i("div",{staticClass:"timeline"},[i("el-timeline",t._l(t.noticeList,function(e,n){return i("el-timeline-item",{key:n,attrs:{icon:e.icon,type:e.type,color:e.color,size:e.size,timestamp:e.time}},[t._v(t._s(e.description))])}),1)],1)])},staticRenderFns:[]};var o=i("VU/8")({name:"xiaotz",data:function(){return{noticeList:""}},mounted:function(){this.getNotice()},methods:{getDate:function(t){return t.replace(/T/g," ").replace(/:00\.000\+0000/,"")},getNotice:function(){var t=this;this.$axios.get("/nav_build/xiaotz").then(function(e){if(e&&200===e.status){t.noticeList=e.data;var i=void 0;for(i in t.noticeList)t.noticeList[i].time=t.getDate(t.noticeList[i].time),t.noticeList[i].type="primary",t.noticeList[i].color="##409EFF"}})}}},n,!1,function(t){i("j9Ck")},"data-v-87364e1a",null).exports,s={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",{staticClass:"title"},[t._v("来访记录")]),t._v(" "),i("el-table",{staticStyle:{width:"80%",margin:"auto"},attrs:{data:t.vinfo,border:""}},[i("el-table-column",{attrs:{label:"访客姓名"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.vname))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"访客身份证号"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.videntity))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"相关学号"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.studentId))]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"来访时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.timeIn))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"理由"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.reason))])]}}])})],1),t._v(" "),i("div",{staticClass:"title"},[t._v("登记访客信息")]),t._v(" "),i("el-date-picker",{attrs:{type:"datetime",placeholder:"来访时间"},model:{value:t.form.timeIn,callback:function(e){t.$set(t.form,"timeIn",e)},expression:"form.timeIn"}}),t._v(" "),i("el-input",{attrs:{placeholder:"访客姓名"},model:{value:t.form.vname,callback:function(e){t.$set(t.form,"vname",e)},expression:"form.vname"}}),t._v(" "),i("el-input",{attrs:{placeholder:"身份证号"},model:{value:t.form.videntity,callback:function(e){t.$set(t.form,"videntity",e)},expression:"form.videntity"}}),t._v(" "),i("el-input",{attrs:{placeholder:"来访理由"},model:{value:t.form.reason,callback:function(e){t.$set(t.form,"reason",e)},expression:"form.reason"}}),t._v(" "),i("el-input",{attrs:{placeholder:"相关学号"},model:{value:t.form.studentId,callback:function(e){t.$set(t.form,"studentId",e)},expression:"form.studentId"}}),t._v(" "),i("el-button",{staticStyle:{"margin-left":"31%"},attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("提交访客信息")])],1)},staticRenderFns:[]};var a=i("VU/8")({name:"fangkexinxi",data:function(){return{vinfo:[{id:"",timeIn:"",vname:"",videntity:"",reason:"",studentId:""}],form:{timeIn:"",vname:"",videntity:"",reason:"",studentId:""}}},methods:{getGuest:function(){var t=this;this.$axios.post("/nav_build/xsfangkexinxi",{id:this.COMMON.id}).then(function(e){t.vinfo=e.data;var i=void 0;for(i in t.vinfo)t.vinfo[i].timeIn=t.getDate(t.vinfo[i].timeIn)}).catch(function(t){})},getDate:function(t){return t.replace(/T/g," ").replace(/\.000\+0000/,"")},onSubmit:function(){var t=this;""===this.form.timeIn||""===this.form.vname||""==this.form.videntity||""===this.form.reason||""===this.form.studentId?this.$message({message:"发布失败，请检查要发布的内容",type:"error"}):this.$axios.post("/nav_build/djfangkexinxi",{timeIn:this.form.timeIn,vname:this.form.vname,videntity:this.form.videntity,reason:this.form.reason,studentId:this.form.studentId}).then(function(e){e&&200===e.status&&(t.$emit("onSubmit"),t.$message({message:"发布成功",type:"success"}),t.getGuest())})}},mounted:function(){this.getGuest()}},s,!1,function(t){i("iKGM")},"data-v-2913ff8c",null).exports,r={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"content"},[i("div",{staticClass:"title"},[t._v("我发布的公告")]),t._v(" "),i("div",{staticClass:"timeline"},[i("el-timeline",t._l(t.notice,function(e,n){return i("el-timeline-item",{key:n,attrs:{icon:e.icon,type:e.type,color:e.color,size:e.size,timestamp:e.time}},[t._v(t._s(e.description))])}),1)],1),t._v(" "),i("div",{staticClass:"title"},[t._v("发布新公告")]),t._v(" "),i("div",{staticClass:"timeline"},[i("el-date-picker",{attrs:{type:"datetime",placeholder:"选择发布时间"},model:{value:t.form.time,callback:function(e){t.$set(t.form,"time",e)},expression:"form.time"}}),t._v(" "),i("el-input",{staticStyle:{width:"40%"},attrs:{placeholder:"请填写公告内容"},model:{value:t.form.description,callback:function(e){t.$set(t.form,"description",e)},expression:"form.description"}}),t._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("发布")])],1)])},staticRenderFns:[]};var l=i("VU/8")({name:"xuetz",data:function(){return{notice:[{id:"",time:"",description:"",dormManagerId:""}],form:{dormManagerId:"",description:"",time:""}}},methods:{getNotice:function(){var t=this;this.$axios.post("/nav_build/xsxuetz",{id:this.COMMON.id}).then(function(e){t.notice=e.data;var i=void 0;for(i in t.notice)t.notice[i].time=t.getDate(t.notice[i].time),t.notice[i].type="primary",t.notice[i].color="#409EFF"}).catch(function(t){})},getDate:function(t){return t.replace(/T/g," ").replace(/\.000\+0000/,"")},onSubmit:function(){var t=this;""===this.form.time||""===this.form.description?this.$message({message:"发布失败，请检查要发布的内容",type:"error"}):this.$axios.post("/nav_build/xuetz",{dormManagerId:this.COMMON.id,time:this.form.time,description:this.form.description}).then(function(e){e&&200===e.status&&(t.$emit("onSubmit"),t.$message({message:"发布成功",type:"success"}),t.getNotice())})}},mounted:function(){this.getNotice()}},r,!1,function(t){i("oj3b")},"data-v-3ded40b0",null).exports,c={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",[i("div",{staticClass:"title"},[t._v("本楼基本信息")]),t._v(" "),i("el-table",{staticStyle:{width:"80%",margin:"auto"},attrs:{data:t.info,border:""}},[i("el-table-column",{attrs:{label:"校区"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.campusName))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"寝室楼"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.dormName))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"学生人数"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(t.info.length))]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"管理员姓名"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.maDormName))])]}}])})],1),t._v(" "),i("div",{staticClass:"title"},[t._v("本楼学生")]),t._v(" "),i("el-table",{staticStyle:{width:"80%",margin:"auto"},attrs:{data:t.mastudentinfo,border:""}},[i("el-table-column",{attrs:{label:"寝室号"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.roomId))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"学生姓名"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.name))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"学生性别"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.sex))]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"学号"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.id))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"专业"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.major))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"联系方式"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.tel))])]}}])})],1)],1)])},staticRenderFns:[]};var u=i("VU/8")({name:"index_build",data:function(){return{info:[{dormName:"",campusName:"",maDormName:""}],mastudentinfo:[{id:"",name:"",roomId:"",sex:"",major:"",tel:""}]}},methods:{getDormInfo:function(){var t=this;this.$axios.post("/nav_build/index",{id:this.COMMON.id}).then(function(e){t.info=e.data}).catch(function(t){})},getStudentInfo:function(){var t=this;this.$axios.post("/nav_build/chuangwei_build",{id:this.COMMON.id}).then(function(e){t.mastudentinfo=e.data}).catch(function(t){})}},mounted:function(){this.getDormInfo(),this.getStudentInfo()}},c,!1,function(t){i("f/Gx")},"data-v-72c3f192",null).exports,d={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",{staticClass:"title"},[t._v("本楼违纪信息")]),t._v(" "),i("el-table",{staticStyle:{width:"80%",margin:"auto"},attrs:{data:t.offeninfo,border:""}},[i("el-table-column",{attrs:{label:"学生姓名"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("span",{staticStyle:{"margin-left":"10px"}},[t._v(t._s(e.row.name))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"学号"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("p",[t._v(t._s(e.row.studentId))])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"详细信息"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.description))]}}])})],1),t._v(" "),i("div",{staticClass:"title"},[t._v("添加违纪信息")]),t._v(" "),i("div",{staticStyle:{width:"30%","margin-left":"10%"}},[i("el-date-picker",{attrs:{type:"datetime",placeholder:"选择违纪时间"},model:{value:t.form.time,callback:function(e){t.$set(t.form,"time",e)},expression:"form.time"}}),t._v(" "),i("br"),t._v(" "),i("br"),t._v(" "),i("el-input",{staticStyle:{width:"47%"},attrs:{placeholder:"违纪学生学号"},model:{value:t.form.studentId,callback:function(e){t.$set(t.form,"studentId",e)},expression:"form.studentId"}}),t._v(" "),i("br"),t._v(" "),i("br"),t._v(" "),i("el-input",{staticStyle:{width:"47%"},attrs:{placeholder:"违纪内容"},model:{value:t.form.description,callback:function(e){t.$set(t.form,"description",e)},expression:"form.description"}}),t._v(" "),i("el-button",{staticStyle:{float:"right"},attrs:{type:"primary"},on:{click:t.onSubmit}},[t._v("提交违纪信息")])],1)],1)},staticRenderFns:[]};var m={name:"nav_build",data:function(){return{id:this.COMMON.id,dialogFormVisible:!1,activeIndex:"1"}},components:{guest:a,campus_notice:o,ext_notice:l,vio:i("VU/8")({name:"jiluweiji",data:function(){return{offeninfo:[{id:"",name:"",studentId:"",description:""}],form:{studentId:"",description:"",time:""}}},methods:{getVio:function(){var t=this;this.$axios.post("/nav_build/xsjiluweiji",{id:this.COMMON.id}).then(function(e){t.offeninfo=e.data}).catch(function(t){})},onSubmit:function(){var t=this;""===this.form.studentId||""===this.form.description||""===this.form.time?this.$message({message:"发布失败，请检查要发布的内容",type:"error"}):this.$axios.post("/nav_build/jiluweiji",{studentId:this.form.studentId,time:this.form.time,description:this.form.description}).then(function(e){e&&200===e.status&&(t.$emit("onSubmit"),t.$message({message:"发布成功",type:"success"}),t.getVio())})}},mounted:function(){this.getVio()}},d,!1,function(t){i("61y9")},"data-v-98099d22",null).exports,dorm_info:u,logout:i("0US+").a},methods:{changeIndex:function(t){this.activeIndex=t},logout:function(){this.dialogFormVisible=!0},handleCancel:function(t){this.dialogFormVisible=!1,this.activeIndex=t}}},f={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("el-menu",{staticClass:"el-menu-demo",staticStyle:{border:"none"},attrs:{"default-active":t.activeIndex,mode:"horizontal","background-color":"#95d1ff","text-color":"#fff","active-text-color":"#0099FF"},on:{select:t.changeIndex}},[n("el-menu-item",{staticClass:"bgc",attrs:{index:"6"}},[n("img",{attrs:{src:i("ZBJ4")}})]),t._v(" "),n("el-menu-item",{staticClass:"bgc item",attrs:{index:"5"},on:{click:t.logout}},[n("span",[t._v("退出登录")])]),t._v(" "),n("el-submenu",{staticClass:"bgc item",attrs:{index:"4"}},[n("template",{slot:"title"},[t._v("学生信息管理")]),t._v(" "),n("el-menu-item",{attrs:{index:"4-1"}},[t._v("违纪信息管理")]),t._v(" "),n("el-menu-item",{attrs:{index:"4-2"}},[t._v("访客信息管理")])],2),t._v(" "),n("el-submenu",{staticClass:"bgc item",attrs:{index:"3"}},[n("template",{slot:"title"},[t._v("通知")]),t._v(" "),n("el-menu-item",{attrs:{index:"3-1"}},[t._v("校方通知")]),t._v(" "),n("el-menu-item",{attrs:{index:"3-2"}},[t._v("通知学生")])],2),t._v(" "),n("el-menu-item",{staticClass:"bgc item",attrs:{index:"1"}},[n("li",[t._v("宿舍信息")])])],1),t._v(" "),"1"===t.activeIndex?n("dorm_info"):t._e(),t._v(" "),"3-1"===t.activeIndex?n("campus_notice"):t._e(),t._v(" "),"3-2"===t.activeIndex?n("ext_notice"):t._e(),t._v(" "),"4-1"===t.activeIndex?n("vio"):t._e(),t._v(" "),"4-2"===t.activeIndex?n("guest"):t._e(),t._v(" "),1==t.dialogFormVisible?n("logout",{on:{cancel:t.handleCancel}}):t._e()],1)},staticRenderFns:[]};var v=i("VU/8")(m,f,!1,function(t){i("ErL7")},"data-v-26786a34",null);e.default=v.exports},ErL7:function(t,e){},"f/Gx":function(t,e){},iKGM:function(t,e){},j9Ck:function(t,e){},oj3b:function(t,e){}});