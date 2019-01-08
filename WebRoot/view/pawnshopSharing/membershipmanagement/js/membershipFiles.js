$(function(){
	//加载数据网格
	$("#viplistTable").datagrid(Common.createDatagridOptionsParams(basePath, "/vipSearch.json",135,null));
})

/**
 * 打开新增会员面板
 * @returns
 */
function addVip(){
	//调用渠道下拉列表
	pullDownList();
	//调用会员序号非重复验证方法
	serialNumberValidation();
	//打开新增会员面板
	$("#vipContainer").dialog('open');
}

/**
 * 搜索
 * @returns
 */
function queryVip(){
	//序列化表单
	var params = $("#vipSearch").serializeObject();
	$("#viplistTable").datagrid(Common.createDatagridOptionsParams(basePath, "/vipSearch.json",135,params));
}

/**
* 新增会员面板的保存按钮
*/
function vipSave(){
	//判断返回的是true还是false
	var verify = $("#vipForm").form('validate');
	if(verify){
		$("#vipForm").form({
			url:basePath+"/vipSave.json",
			success:function(data){
				if(data.rows == 0){
					$.messager.alert("警告","新增失败！");
					return;
				}else{
					$.messager.alert("信息","新增成功！");
				}
				//刷新页面
				$("#viplistTable").datagrid(Common.createDatagridOptionsParams(basePath, "/vipSearch.json",135,null));	
				//清空新增界面
				$("#vipForm").form("reset");
			}
		})
		//提交新增会员表单
		$("#vipForm").submit();
		//关闭新增会员界面
		$("#vipContainer").dialog('close');
	}
}

/**
 * 会员新增面板的关闭按钮
 */
function vipClose(){
	$("#vipContainer").dialog('close');
	//清空会员新增表单
	$("#vipForm").form('reset');
}

/**
 * 删除
 * @returns
 */
function delVip(){
	//获取选中的行的数据
	var rows = $("#viplistTable").datagrid('getSelections');
	if(rows.length == 0){
		$.messager.alert('警告','请先进行选择!');
		return;
	}
	$.messager.confirm('警告','确认删除吗?',
		function(r){
			if(r){
				/*
				 * 发送异步请求到服务器完成删除功能
				 */
				//获取要删除会员的序号数组
				var dels = [];
				rows.forEach(e=>{
					dels.push(e.memberId);
				});
				$.post(
					basePath+"/delVip.json",
					{"dels":dels},
					function(data){
						if(data.rows == 0){
							$.messager.alert('警告','删除失败');
						}else{
							$.messager.alert('信息','删除成功');
							//刷新页面
							$("#viplistTable").datagrid(Common.createDatagridOptionsParams(basePath, "/vipSearch.json",135,null));
						}
					},
					"json"
				)
			}else{
				$("#viplistTable").datagrid("uncheckAll");
			}
		}
	);
}

/**
 * 打开修改会员信息
 * @returns
 */
function modifyVip(){
	//获取选中的行的信息
	var modifyVipInfo = $("#viplistTable").datagrid('getSelections');
	if(modifyVipInfo.length == 0){
		$.messager.alert('警告','请选择一条数据进行修改!');
	}else if(modifyVipInfo.length > 1){
		$.messager.alert('警告','一次只能选择一条数据进行修改!');
	}else{
		$.post(
				basePath+"/modifyVip.json",
				{"memberId":modifyVipInfo[0].memberId},
				function(data){
					//console.log(data.rows[0]);
					var result = data.rows[0];
					//for循环自动回填
					for(index in result){
						if(index !== "channelCode") {
							$("#modify" + index).textbox("setValue",result[index]);	
						}	
						//隐藏域填值
						$("#modifymemberIdHidden").val(modifyVipInfo[0].memberId)
					}
					//调用所属渠道下拉列表方法
					modifyPullDownList(result.channelCode);
					
		})
		//打开修改面板
		$("#modifyVipContainer").dialog('open');
	}
}

/**
* 关闭修改面板
*/
function modifyVipClose(){
	$("#modifyVipContainer").dialog('close');
}

/**
* 修改界面的保存按钮
*/
function modifyVipSave(){
	//判断返回的是true还是false
	var verify = $("#modifyVipForm").form('validate');
	if(verify){
		$("#modifyVipForm").form({
			url:basePath+"/modifyVipSave.json",
			success:function(data){
				if(data.rows == 0){
					$.messager.alert('警告','修改失败！');
				}else{
					$.messager.alert('信息','修改成功');
					//关闭修改界面
					$("#modifyVipContainer").dialog('close');
					//刷新页面
					$("#viplistTable").datagrid(Common.createDatagridOptionsParams(basePath, "/vipSearch.json",135,null));
				}
			}
		})
		//提交修改表单
		$("#modifyVipForm").submit();
	}	
}

/**
* 打开充值界面
*/
function rechargeVip(){
	//获取选中行的信息
	var row = $("#viplistTable").datagrid('getSelections');
	console.log(row[0].memberId);
	
	if(row.length == 0){
		$.messager.alert('警告','请选择一个会员充值!');
	}else if(row.length > 1){
		$.messager.alert('警告','一次只能选择一个会员充值!');
	}else{
		$.post(
			basePath+"/modifyVip.json",
			{"memberId":row[0].memberId},
			function(data){
				var result = data.rows[0];
				//for循环填坑
				for(index in result){
					$("#recharge" + index).textbox('setValue',result[index]);
				}
				//隐藏域填值
				$("#rechargememberIdHidden").val(row[0].memberId);
			}
		)
		//打开充值界面
		$("#rechargeVipContainer").dialog('open');
	}	
}

/**
* 充值界面的保存按钮
*/
function rechargeVipSave(){
	//判断返回的是true还是false
	var verify = $("#rechargeVipForm").form('validate');
	console.log(verify);
	if(verify){
		$("#rechargeVipForm").form({
			url:basePath+"/rechargeVipSave.json",
			success:function(data){
				if(data.rows == 0){
					$.messager.alert('警告','充值失败!');
				}else{
					$.messager.alert('信息','充值成功!');
					//关闭充值界面
					$("#rechargeVipContainer").dialog('close');
					//刷新页面
					$("#viplistTable").datagrid(Common.createDatagridOptionsParams(basePath, "/vipSearch.json",135,null));
					//清空充值表单
					$("#rechargeVipForm").form('reset');
				}
			}
		})
		//充值表单提交
		$("#rechargeVipForm").submit();
	}	
}

/**
* 充值界面的关闭按钮
*/
function rechargeVipClose(){
	//关闭充值界面
	$("#rechargeVipContainer").dialog('close');
	//清空充值表单
	$("#rechargeVipForm").form('reset');
}

/**
* 会员序号验证
*/
function serialNumberValidation(){
	$("#addMemberId").textbox({
		required:true,
		missingMessage:"序号号不能为空",
		invalidMessage:"序号已存在",
		validType:"remoteValid['"+basePath+"/serialNumberValidation.json','memberId']"
	});
}

/**
 * 禁用，启用状态
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operation(value,row,index){
	if(row.memberStat =='01'){
		return"<a href='#' onClick='forbidden(event)' value="+row.memberStat+" id="+row.memberId+" style='color:blue'>禁用</a>";
	}else{
		return"<a href='#' onClick='forbidden(event)' value="+row.memberStat+" id="+row.memberId+" style='color:blue'>启用</a>";
	} 
}

/**
* 改变状态
*/
function forbidden(event){
	//获取选中数据的状态值
	var memberStat = $(event.target).attr("value");
	//获取选中数据的会员序号
	var memberId = $(event.target).attr("id");
	var rows = $("#viplistTable").datagrid("getData").rows ;
	var index  ;
	for(var i = 0 ; i < rows.length ; i++){
		if(rows[i].memberId == memberId){
			index = i ;
		}
	}
	if(memberStat == '01'){
		memberStat = '00';
	}else{
		memberStat = '01';
	}
	$.ajax({
		url:basePath+"/forbidden.json",
		type:"post",
		dataType:"json",
		data:{"memberStat":memberStat,"memberId":memberId},
		success:function(data){
			$('#viplistTable').datagrid('updateRow',{
				index: index,
				row: {
					memberStat:memberStat
				}
			});
		}
	})
}

/**
* 新增会员所属渠道下拉框
*/
function pullDownList(){
	$.ajax({
		url:basePath+'/getChannel.json',//查询全部渠道
		type:'post',
		dataType:'json',
		success:function(data){
			$("#addChannelCode").combobox({
				required:true,
				editable:false,
			    valueField:'channelCode', 
			    textField:'channelName',
			    panelHeight:'100px',
			    width:145 ,
			    data:data.rows ,
			    loadFilter:function(data){
				  	   return data;
				  	}
			});
		}
	})
}

/**
* 修改会员所属渠道下拉框
*/
function modifyPullDownList(value){
	$.ajax({
		url:basePath+'/getChannel.json',//查询全部渠道
		type:'post',
		dataType:'json',
		success:function(data){
			$("#modifychannelCode").combobox({
				required:true,
				editable:false,
			    valueField:'channelCode', 
			    textField:'channelName',
			    panelHeight:'100px',
			    width:145 ,
			    data:data.rows ,
			    loadFilter:function(data){
				  	   $("#modifychannelCode").combobox("setValue",value);
				  	   return data;
				  	}
			});
		}
	})
}

/**
* 打开详情
*/
function particularsVip(){
	var particulars = $("#viplistTable").datagrid('getSelections');
	$("#particularsVipForm").form('load',particulars[0]);
	/*$.post(
		basePath+"/modifyVip.json",
		{"memberId":particulars[0].memberId},
		function(data){
			var result = data.rows[0];
			//for循环填坑
			for(index in result){
				$("#particulars" + index).textbox('setValue',result[index]);
			}
		}
	)*/
	$("#particularsVipContainer").dialog('open');
}

/**
* 详情界面取消按钮
*/
function particularsVipClose(){
	$("#particularsVipContainer").dialog('close');
}

/**
* 格式化数据列的状态
*/
function yesOrNo(value,row,index){
	if(value == '01'){
		return "<img src='../../../css/themes/icons/ok.png'/>";
	}else{
		return "—";
	}
}

/**
* 数字验证
*/
$.extend($.fn.validatebox.defaults.rules, {
	myvalid: { 
		validator: function (value, param) {
			return /^\d+(\.\d{1,2})?$/.test(value)
		}, 
		message: '只能输入正整数和两位小数！' 
	} 
}); 