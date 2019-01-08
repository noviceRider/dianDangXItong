/**
 * <p>title:广沣共享典当管理系统-group.js</p>
 * 
 * <p>Description:商品属性组的js</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */

//创建一个全局变量来判断是新增还是修改
var flag = false ;

$(function(){
	
	//初始化加载页面数据
	loading() ;
	
	//初始化load页面
	$("#add_modify_group_Container").load("add_modify_group_Container.html",null,function(){
		//初始化新增窗口
		$("#add_modify_group_Panel").dialog({
			buttons:'#saveAndCancel',
			onClose:cancelgroup ,
			width:440 ,
			modal:true ,
			closed:true
		});
		
		$("#add_modify_group_groupCode").textbox({
			required:true,
			missingMessage:"属性组编号不能为空",
			width:200
		})
		$("#add_modify_group_groupName").textbox({
			required:true,
			missingMessage:"属性组名称不能为空",
			width:200
		})
		
		$("#selectCommodityByNo").textbox({
			width:180,
			icons:[{
			     iconCls:'icon-search',
			     //调用搜索待选商品大类方法
			     handler:searchSelectCommodityByNo
			}]
		})
		$("#selectCommodityByYes").textbox({
			width:180,
			icons:[{
			     iconCls:'icon-search',
			     //调用搜索以待选商品大类方法
			     handler:searchSelectCommodityByYes
			}]
		})
		//设置按钮格式为easyUI格式
		$("#moveRight").linkbutton({
			text:">",
			width:50 ,
			onClick:function(){
				//调用右移方法
				moveRight();
			}
		})
		$("#moveAllRight").linkbutton({
			text:">>>",
			width:50 ,
			onClick:function(){
				//调用全部右移方法
				moveAllRight();
			}
		})
		$("#moveLeft").linkbutton({
			text:"<",
			width:50 ,
			onClick:function(){
				//调用左移方法
				moveLeft();
			}
		})
		$("#moveAllLeft").linkbutton({
			text:"<<<",
			width:50 ,
			onClick:function(){
				//调用全部左移的方法
				moveAllLeft();
			}
		})
		
		$("#savegroup").linkbutton({
			text:"确定",
			iconCls:'icon-save',
			onClick:function(){
				//调用确认方法
				savegroup();
			}
		})
		
		$("#cancelgroup").linkbutton({
			text:"取消",
			iconCls:'icon-clear',
			onClick:function(){
				//调用取消方法
				cancelgroup();
			}
		})
	})
})

/**
 * 初始化加载数据的方法
 * @returns
 */
function loading(){
	$("#groupTable").datagrid(Common.createDatagridOptionsParams(basePath, "/groupTable.json", 135,null));
}

/**
 * 设置显示状态的方法
 */
function showState(value){
	if (value == "01") {
		return "有效";
	}else if(value == "00"){
		return "无效";
	}
}

/**
 * 操作的方法
 */
function caozuo(value,row,index){
	if(row.groupState=='01'){
		return '<a href=# onClick=conversion(event) value='+row.groupState+' id='+row.groupCode+'>禁用</a> | <a href=# onClick=jump(event) id='+row.groupCode+'>参数列表</a>';
	}else if(row.groupState=='00'){
		return '<a href=# onClick=conversion(event) style=color:red value='+row.groupState+' id='+row.groupCode+'>启用</a>';
	}
}

/**
 * 	禁用、启用状态方法
 */
function conversion(event){
	//获取所选中数据的ID和Value值
	var groupState = $(event.target).attr("value");
	var groupCode = $(event.target).attr("id");
	var rows = $("#groupTable").datagrid("getData").rows ;
	var index  ;
	for(var i = 0 ; i < rows.length ; i++){
		if(rows[i].groupCode == groupCode){
			index = i ;
		}
	}
	//设置状态码
	if(groupState == "01"){
		groupState = "00" ;
	}else{
		groupState = "01" ;
	}
	$.ajax({
		url:basePath+"/changeGroupState.json",
		type:"post",
		dataType:"json",
		data:{'groupCode':groupCode,'groupState':groupState},
		success:function(data){
			$('#groupTable').datagrid('updateRow',{
				index: index,
				row: {
					groupState:groupState
				}
			});
		}
	});
}

/**
 * 点击查询按钮的方法
 */
function queryGroup(){
	var keyWord = $("#keyWord").textbox("getValue");
	//重新加载表格数据
	$("#groupTable").datagrid("reload",{"keyWord":keyWord});
}

/**
 * 点击增加按钮的方法
 */
function addGroup(){
	flag = true ;
	searchSelectCommodityByNo();
	$("#add_modify_group_groupCode").textbox("enable");
	$("#add_modify_group_groupCode").textbox({onChange:yanzheng()});
	$("#add_modify_group_Panel").dialog({title:"新增",closed:false});
}

/**
 * 	新增加载待选商品大类的方法
 */
function searchSelectCommodityByNo(){
	//获取关键字输入框的value值
	var keyWordByNo = $("#selectCommodityByNo").textbox("getValue") ;
	//删除待选仓库里面的option
	$("#left option").remove();
	//设置一个变量来保存已选仓库的option的value值
	var keyByYes ;
	//判断已选仓库的option的个数
	if($("#right option").length == 0){
		keyWordByYes = "''";
	}else{
		//获取已选仓库的option的value值
		keyWordByYes = $("#right option").map(function(){return $(this).val();}).get().join(",");
	}
	$.ajax({
		url:basePath+"/loadProductCategories.json",
		type:"post",
		data:{"keyWordByNo":keyWordByNo,"keyWordByYes":keyWordByYes},
		dataType:"json",
		success:function(data){
			for (var i = 0; i < data.rows.length; i++) {
				//动态的给待选仓库的select中添加option节点
				$("#left").append('<option value='+data.rows[i].catCode+'>'+data.rows[i].catName+'</option>');
			}
		}
	});
}

/**
 * 新增加载已选商品大类的方法
 */
function searchSelectCommodityByYes(){
	$("#right option").prop("selected",false);
	var keyword = $("#selectCommodityByYes").textbox("getValue") ;
	var reg = new RegExp(keyWord);
	for(var i = 0 ; i <$("#right option").length ; i++){
		if($("#right option")[i].text.indexOf(keyword) != -1){
			$("#right option").eq(i).prop("selected",true);
		}
	}
}

/**
 * 确认的方法
 */
function savegroup(){
	var groupCode = $("#add_modify_group_groupCode").textbox("getValue") ;
	var groupName = $("#add_modify_group_groupName").textbox("getValue") ;
	var obj = {"groupCode":groupCode,"groupName":groupName};
	var catCode = $("#right option").map(function(){return $(this).val();}).get().join(",");
	if(groupCode == '' || groupCode == null){
		$.messager.alert("信息","编号不能为空") ;
		return ;
	}else if(groupName == '' || groupName == null){
		$.messager.alert("信息","名称不能为空") ;
		return ;
	}else{
		if(flag){
			$.ajax({
				url:basePath+'/addGroup.json',
				type:'post',
				data:obj ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
						$.messager.alert("提示","新增成功!");
					}
				}
			})
			//增加属性组所关联的商品大类
			$.ajax({
				url:basePath+"/addAttrAndProd.json",
				type:"post",
				data:{"catCode":catCode,"groupCode":groupCode},
				dataType:"json",
				success:function(data){
					//调用加载数据的方法
					loading();
					cancelgroup();
				}
			});
		}else{
			//修改店铺
			$.ajax({
				url:basePath+"/updGroup.json",
				type:"post",
				data:obj,
				dataType:"json",
				success:function(data){
					if(data.rows > 0){
						$.messager.alert("提示","修改成功!");
					}
				}
			});
			//删除与修改属性组有关的关联商品大类数据
			$.ajax({
				url:basePath+"/removeGroupAndProd.json",
				type:"post",
				data:{"groupCode":groupCode},
				dataType:"json",
				success:function(data){
					//增加属性组所关联的商品大类
					$.ajax({
						url:basePath+"/addAttrAndProd.json",
						type:"post",
						data:{"catCode":catCode,"groupCode":groupCode},
						dataType:"json",
						success:function(data){
							//调用加载数据的方法
							loading();
							cancelgroup();
						}
					});
					
				}
			});
		}
	}
}

/**
 * 取消的方法
 */
function cancelgroup(){
	$("#add_modify_group_groupCode").textbox("setValue","") ;
	$("#add_modify_group_groupName").textbox("setValue","") ;
	$("#selectCommodityByYes").textbox("setValue","") ;
	$("#selectCommodityByNo").textbox("setValue","") ;
	//删除待选仓库的所有option节点
	$("#left option").remove();
	//删除已选仓库的所有option节点
	$("#right option").remove();
	$("#add_modify_group_Panel").dialog({closed:true});
}

/**
 * 	全部右移的方法
 * @returns
 */
function moveAllRight(){
	$(".left>option").appendTo(".right");
}
/**
 * 	全部左移的方法
 * @returns
 */
function moveAllLeft(){
	$(".right>option").appendTo(".left");
}	

/**
 * 	右移的方法
 * @returns
 */
function moveRight(){
	$(".left>option:selected").appendTo(".right");
	//让移动过去的option取消被选中状态
	$("option:selected").prop("selected",false);
}

/**
 * 	左移的方法
 * @returns
 */
function moveLeft(){
	$(".right>option:selected").appendTo(".left");
	//让移动过去的option取消被选中状态
	$("option:selected").prop("selected",false);
}

/**
 *  表单验证
 */
function yanzheng(){
	$("#add_modify_group_groupCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/yanzhengByGroupCode.json','groupCode']"
	});
}

/**
 * 点击修改的方法
 */
function updGroup(){
	//获取表格中选中的数据
	var rows = $("#groupTable").datagrid("getSelections");
	//如果选择了多条
	if(rows.length >1){
		$.messager.alert("信息","每次只能选择一条数据修改") ;
		//如果一条都没选
	}else if(rows.length == 0){
		$.messager.alert("信息","请选择一条数据进行修改") ;
		//如果只选择了一条
	}else{
		//设置公共变量为false
		flag = false ;
		$("#add_modify_group_groupCode").textbox("disable") ;
		var groupCode = $("#groupTable").datagrid("getSelected").groupCode ;
		$.ajax({
			url:basePath+'/getAttrByGroupCode.json',
			type:'post',
			data:{"groupCode":groupCode} ,
			dataType:'json',
			success:function(data){
				$("#add_modify_group_groupCode").textbox("setValue",data.rows.groupCode) ;
				$("#add_modify_group_groupName").textbox("setValue",data.rows.groupName) ;
			}
		})
		//根据编号获取已选仓库的数据
		$.ajax({
			url:basePath+"/getCommodityByGroupCode.json",
			type:"post",
			data:{"groupCode":groupCode},
			dataType:"json",
			success:function(data){
				//如果已选仓库有数据
				if(data.rows.length >0){
					//动态的给已选仓库添加option节点
					var catCode = "";
					for (var i = 0; i < data.rows.length; i++) {
						$("#right").append('<option value='+data.rows[i].catCode+'>'+data.rows[i].catName+'</option>');
						if(i == data.rows.length -1){
							catCode += data.rows[i].catCode
						}else{
							catCode += data.rows[i].catCode +","
						}
					}
					//根据编号查询待选商品大类的数据
					searchSelectCommodityByNo();
				}else{
					//根据编号查询待选商品大类的数据
					searchSelectCommodityByNo();
				}
			}
		})
		$("#add_modify_group_Panel").dialog({title:"修改",closed:false});
	}
}

/**
 *  点击删除按钮的方法
 */
function delGroup(){
	//获取需要删除的数据的数据
	var rows = $("#groupTable").datagrid("getSelections");
	//如果没有选中数据
	if(rows.length == 0 ){
		$.messager.alert('警告','没有选择要删除的数据，请先进行选择！！');   
		return;
		//如果有选中数据
	}else{
		$.messager.confirm('提示', '会删除旗下的说有属性，请谨慎选择。您是否要删除选中的数据？', 
			function(r){
				if (r){
					//获取选中的数据的编号
					var groupCode = "";
					for (var i = 0; i < rows.length; i++) {
						if(i == rows.length - 1){
							groupCode += rows[i].groupCode ;
						}else{
							groupCode += rows[i].groupCode + "," ;
						}
					}
					//根据编号删除属性组
					$.ajax({
						url:basePath+"/delGroup.json",
						type:"post",
						data:{"groupCode":groupCode},
						dataType:"json",
						success:function(data){
							
						}
					});
					$.ajax({
						url:basePath+"/removeGroupAndProd.json",
						type:"post",
						data:{"groupCode":groupCode},
						dataType:"json",
						success:function(data){
							loading();
							$.messager.alert('信息','删除的数据成功！！');
						}
					})
				}
		})
	}
}

/**
 * 点击参数列表的方法
 */
function jump(event){
	var groupCode = $(event.target).attr("id");
	window.location.href="parameterList.html?valus="+groupCode ;
}