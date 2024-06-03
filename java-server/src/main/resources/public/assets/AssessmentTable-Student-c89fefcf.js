import{m as i,a as _}from"./messageBox-f4d8c0d3.js";import{d as I,H as p,_ as f,e as d,f as t,L as n,j as c,k as m,F as l,M as r,v as F,o}from"./index-3f7c6146.js";import{f as B,a as g,h as v,i as C}from"./affairServ-848d5dc3.js";const E=I({data:()=>({student1Id:null,student2Id:null,assessmentList:[],studentList:[],editedItem:{},info:{}}),computed:{filteredStudentList(){return this.studentList.filter(s=>s.id!==this.info.studentId)},filteredEditStudentList(){return this.studentList.filter(s=>s.id!==this.info.studentId)}},created(){this.initialize()},methods:{async initialize(){let s=await p(this.student1Id);this.info=s.data.info,this.student1Id=this.info.studentId,this.assessmentList=await B(this.student1Id,this.student2Id),this.studentList=await g()},async query(){this.assessmentList=await B(this.student1Id,this.student2Id)},addItem(){this.editedItem={},document.getElementById("favDialog").show()},editItem(s){this.editedItem=s,document.getElementById("favDialog").show()},close(){document.getElementById("favDialog").close()},async confirm(){this.close();const s=await v(this.editedItem.assessmentId,this.info.studentId,this.editedItem.student2Id,this.editedItem.content);s.code==0?(i(this,"保存成功"),this.query()):i(this,s.msg)},async deleteItem(s){if(!await _("确认删除吗?"))return;const a=await C(s);a.code==0?(i(this,"成功删除了一条学生互评记录"),this.query()):i(this,a.msg)}}}),y={class:"base_form"},b=t("div",{class:"base_header"},[t("div",{class:"blue_column"}),t("div",{class:"base_title"},"学生互评管理")],-1),D={class:"base_query_oneLine"},L={class:"query_left"},k={class:"query_right"},w=t("span",null,"评价学生:",-1),A=t("span",null,"被评价学生:",-1),S=t("option",{value:"0"},"请选择...",-1),$=["value"],q={class:"table_center"},N={class:"content"},V=t("tr",{class:"<table_th"},[t("td",null,"评价学生学号"),t("td",null,"评价学生姓名"),t("td",null,"被评价学生学号"),t("td",null,"被评价学生姓名"),t("td",null,"评价内容"),t("td",null,"操作")],-1),M=["onClick"],U=["onClick"],z={id:"favDialog",onclose:"close()",style:{position:"absolute",top:"300px",left:"300px",width:"300px",height:"310px"}},T=t("div",{class:"base_title"},"--学生互评记录添加对话框显示--",-1),j={class:"dialog-div"},H={class:"dialog_content"},O=t("td",{colspan:"1",style:{"text-align":"right"}},"评价学生:",-1),G={colspan:"1"},J=t("td",{colspan:"1",style:{"text-align":"right"}},"被评价学生:",-1),K={colspan:"1"},P=t("option",{value:"0"},"请选择...",-1),Q=["value"],R=t("td",{colspan:"1",style:{"text-align":"right"}},"评价内容",-1),W={colspan:"1"},X={colspan:"2"};function Y(s,u,a,Z,x,tt){return o(),d(l,null,[t("div",y,[b,t("div",D,[t("div",L,[t("button",{class:"commButton",onClick:u[0]||(u[0]=e=>s.addItem())},"添加学生互评记录")]),t("div",k,[w,t("td",null,n(s.info.name),1),A,c(t("select",{class:"commInput","onUpdate:modelValue":u[1]||(u[1]=e=>s.student2Id=e)},[S,(o(!0),d(l,null,r(s.filteredStudentList,e=>(o(),d("option",{key:e.id,value:e.id},n(e.title),9,$))),128))],512),[[m,s.student2Id]]),t("button",{class:"commButton",onClick:u[2]||(u[2]=e=>s.query())},"查询学生互评记录列表")])]),t("div",q,[t("table",N,[V,(o(!0),d(l,null,r(s.assessmentList,e=>(o(),d("tr",{key:e.assessmentId},[t("td",null,n(e.stu1Num),1),t("td",null,n(e.stu1Name),1),t("td",null,n(e.stu2Num),1),t("td",null,n(e.stu2Name),1),t("td",null,n(e.content),1),t("td",null,[t("button",{class:"table_edit_button",onClick:h=>s.editItem(e)},"编辑",8,M),t("button",{class:"table_delete_button",onClick:h=>s.deleteItem(e.assessmentId)},"删除",8,U)])]))),128))])])]),t("dialog",z,[T,t("div",j,[t("table",H,[t("tr",null,[O,t("td",G,[t("td",null,n(s.info.name),1)])]),t("tr",null,[J,t("td",K,[c(t("select",{class:"commInput","onUpdate:modelValue":u[3]||(u[3]=e=>s.editedItem.student2Id=e)},[P,(o(!0),d(l,null,r(s.filteredEditStudentList,e=>(o(),d("option",{key:e.id,value:e.id},n(e.title),9,Q))),128))],512),[[m,s.editedItem.student2Id]])])]),t("tr",null,[R,t("td",W,[c(t("input",{"onUpdate:modelValue":u[4]||(u[4]=e=>s.editedItem.content=e),class:"commInput"},null,512),[[F,s.editedItem.content]])])]),t("tr",null,[t("td",X,[t("button",{class:"commButton",onClick:u[5]||(u[5]=e=>s.close()),style:{"margin-right":"30px"}}," 取消 "),t("button",{class:"commButton",onClick:u[6]||(u[6]=e=>s.confirm())},"确认")])])])])])],64)}const nt=f(E,[["render",Y]]);export{nt as default};
