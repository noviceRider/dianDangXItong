var isNew;//判断新增还是修改
var url;//增加或修改地址

$(function(){
	
	// 加载新增、修改页面
	$("#channel_add_modify_div").load("channelAddModify.html", null,
			function() {
				// 进行解析
				$.parser.parse(this);
			});
	
	// 初始化数据网格
	$("#channelTable").datagrid(
			Common.createDatagridOptionsParams(basePath,
					"/queryChannelTable.json", null, null));
})

/**
 * 按条件查询
 * @returns
 */
function queryChannel(){

	//获取搜索条件区域的各条件信息
	var obj ={
			"keyWord":$("#keyWord").val()
			};

	//获得搜索表单中的数据 查找缺陷表格
	$("#channelTable").datagrid(
			Common.createDatagridOptionsParams(
					basePath, "/queryChannelTable.json", 135, obj
			)
	);
	
	//重置搜索条件表单
	$("#queryForm").form("reset");
}

/**
 * 新增按钮
 */
function addChannel() {
	isNew = true;
	url = basePath + "/addChannel.json";

	//打开编号编辑框为可编辑状态
	$("#channelCode").textbox("enable");

	// 打开新增对话框
	$("#channel_am").dialog({
		modal : true,
		title : "新增渠道商",
		iconCls : "icon-add",
		onClose : function() {
			// 清空数据
			$("#channel_form").form("reset");
		},
	}).dialog("center");
	//调用验证方法
	validateMetamanage();
}

/**
 * 验证表名和实体名，不能为空也不能重复
 */
function validateMetamanage() {
	
	$.ajaxSettings.async=false;
	//渠道名
	$("#channelName").textbox({
		required:true,
		missingMessage:"渠道名不能为空"
	});
	//编号非空且不能重复
	$("#channelCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已经存在",
		validType:"remoteValid['"+basePath+"/validateChannel.json','channelCode']"
	});
	$.ajaxSettings.async=true;
}

/**
 *  删除
 * @returns
 */
function removeChannel(){
	// 得到要修改的节点
	var rows = $("#channelTable").datagrid("getSelections");
	if (rows == null || rows.length == 0) {
		$.messager.alert("提示信息", "请选择要删除的数据！", "info");
		return;
	}
	// 得到要删除的行节点
	var channelCodes = [];
	//拼接字符串
	for (var i = 0; i < rows.length; i++) {
		channelCodes.push(rows[i].channelCode);
	}
	//拼接一个要删除的ID串
	var a = "'"+channelCodes.join("','")+"'";
	//提示信息
	var delNames = "";
	//拼接提示信息
	for (var i = 0; i < rows.length; i++) {
	
		delNames += " 【"+rows[i].channelName+"】 ";
	}
	$.messager.confirm('提示','您确认要删除渠道商'+delNames+'的信息吗？',function(r){
		if (r){
			$.ajax({
				url : basePath + "/romoveChannelById.json",
				type : 'post',
				data : {"channelCodes":a},
				dataType : "json",
				async : false,
				success : function(data) {
					// 更新页面数据
					$('#channelTable').datagrid('reload');
				}
			});
		}else{
			$("#channelTable").datagrid('uncheckAll');
		}
	});
}

/**
 *  修改
 * @returns
 */
function modifyChannel(){
	
	isNew = false;
	url = basePath + "/modifyChannel.json";
	
	// 得到要修改的节点
	var rows = $("#channelTable").datagrid("getSelections");
	if (rows == null || rows.length == 0) {
		$.messager.alert("提示信息", "请选择要修改的数据！", "info");
		return;
	} else if (rows.length > 1) {
		$.messager.alert("提示信息", "只能选择一条渠道商修改！", "info");
		return;
	}
	//获取修改行的id
	var row = queryChannelById(rows[0].channelCode);
	//装填数据
	$("#channel_form").form("load", row);
	//打开编号框为不可编辑状态
	$("#channelCode").textbox("disable");
	// 打开新增修改对话框
	$("#channel_am").dialog({
		modal : true,
		title : "修改缺陷状态",
		iconCls : "icon-edit",
		onClose : function() {
			// 清空数据
			$("#channel_form").form("reset");
		},
	}).dialog("center");
	
	//调用验证方法
	validateMetamanage();
	
}

/**
 * 单条查询
 * 
 * @param stateCode
 * @returns
 */
function queryChannelById(channelCode) {
	var row = {};
	// 获取需要修改的这条数据
	$.ajax({
		url : basePath + "/queryChannelById.json",
		type : 'post',
		data : {
			"channelCode" : channelCode
		},
		dataType : "json",
		async : false,
		success : function(data) {
			row = data.row;
		}
	});
	return row;
}

/**
 * 保存按钮
 */
function saveChannel(){
	//序列化表单
	var formData = $("#channel_form").serializeObject();
	//获得ID
	formData.channelCode = $("#channelCode").textbox("getValue");
	//默认状态为有效
	formData.channelState = "01";
	//判断验证
	if($("#channel_form").form("validate")){
		$.ajax({
			url : url,
			type : 'post',
			data : formData,
			dataType : "json",
			success : function(data) {
				if (data.resultCode == "1") {
					// 更新页面数据
					$('#channelTable').datagrid('reload');
					$.messager.alert("提示信息", "保存成功", "success!!");
					//重置表单数据，为下次操作做准备
					cancelChannel();
				} else if (data.resultCode == "0") {
					$.messager.alert("提示信息", "操作失败", "error");
				}
			},
			error : function() {
				$.messager.alert('错误', '服务器内部错误!', 'error');
			}
		});
	}else{
		$.messager.alert('错误', '必填内容错误，保存失败', 'error');
	}
	//重置表单数据，为下次操作做准备
	cancelChannel();
}

/**
 * 取消按钮
 */
function cancelChannel() {
	//关闭窗口
	$("#channel_am").dialog("close");
	// 清空数据
	$("#channel_form").form("reset");
}

/**
 *  有效无效时 显示图片
 * @returns
 */
function isUsedFormatter(value,row,index){
	
	if (value == "01") {// 有效
		return "<img src='../../../css/themes/icons/ok.png'/>";
	} else if (value == "00") {// 无效
		return "—";
	}
}

/**
 *  禁用启用
 * @returns
 */
function formatterDo(value,row,index){
	if(row.channelState == "01"){
		return '<a style="color: blue;" href=# onClick="switchM(\''+row.channelCode+'\',\''+row.channelState+'\')">禁用</a>';
	}else{
		return '<a style="color: blue;" href=# onClick="switchM(\''+row.channelCode+'\',\''+row.channelState+'\')">启用</a>';
	}
}	

/**
 *  禁用、启用转换
 * @returns
 */
function switchM(channelCode,channelState){
	
	if(channelState == "1"){
		channelState = "00";
	}else{
		channelState = "01";
	}

	
	$.post(
			basePath+"/switchM.json",
			{"channelCode":channelCode,"channelState":channelState},
			function(data) {
			
				if(data.result == 1){
					if(channelState=="00"){
						$.messager.alert("警告","禁用成功！");
					}else{
						$.messager.alert("警告","启用成功！");
					}
				}else{
					if(moduleState=="00"){
						$.messager.alert("警告","禁用失败！");
					}else{
						$.messager.alert("警告","启用失败！");
					}
				}
				//刷新页面
				queryChannel();
			},
			"json"
		);
}