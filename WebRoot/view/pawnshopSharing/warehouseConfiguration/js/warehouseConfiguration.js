/**
 * 定义一个全局变量来确定是新增还是修改
 * @returns
 */
var url = null;

/**
 * 页面初始化事件
 * @returns
 */
$(function(){
	//页面加载就调用加载数据表格的方法
	loaddataToTab();
	
	/**
	 * 发送一个Ajax请求加载下拉框的数据
	 * @returns
	 */
	$.ajax({
		url:basePath+"/loadStatueCombo.json",
		type : 'post',
		dataType : "json",
		success : function(data) {
			$("#WareHouseStateNameSear").combobox({
				textField:"whStat",
				valueField:"whStateId",
				width:120,
				data:data.result,
				panelHeight:true,
				 loadFilter:function(data){
				  	   data.unshift({whStateId:'',whStat:'--请选择--'});
				  	   return data;
				  	}
				});
			}
		});
	
	/**
	 * load一个页面可以进行修改或增加
	 * @returns
	 */
	$("#AOFWareHouseContainer").load("addOrUpdWarehouse.html",null,function(){
		$("#AOFPanel").dialog({
			iconCls: 'icon-save',
			width:300,
			height:250,
			closed:true,
			modal:true,
			buttons:"#saveBtn",
			onBeforeClose:function () { 
			//调用easyUI方法清空增加面板的数据
				$("#AOFForm").form("reset");
		        }
		});
		//初始化新增修改页面的组件
		$("#AOFwhCodeDis").textbox({width:120,required:true});
		$("#AOFwhName").textbox({width:120,required:true});
		$("#AOFphoneNo").textbox({width:120,required:true});
		$("#AOFrelShopRel").textbox({width:120,multiline:true,height:50});
		$("#AOFaddress").textbox({width:120,required:true});
		validateMetamanage();
		/**
		 * 加载可以负责仓库的人选
		 * @returns
		 */
		$.ajax({
			url:basePath+"/loadContentCombo.json",
			type : 'post',
			dataType : "json",
			success : function(data) {
				$("#AOFcontactCombo").combobox({
					textField:"contactName",
					valueField:"contact",
					width:120,
					panelHeight:true,
					required:true,
					data:data.result,
					loadFilter:function(data){
					  	   data.unshift({contact:'',contactName:'--请选择--'});
					  	   return data;
					  	}
					});
				 }
			});
		
		/**
		 * 初始化保存按钮
		 * @returns
		 */
		$("#saveAndLoad").linkbutton({
			 iconCls: 'icon-save',
			    onClick:function(){
			    	var bo = $("#AOFForm").serializeObject();
			    	if($("#AOFForm").form("validate")){
			    		if(($("#AOFcontactCombo").combobox("getValue")!="" && $("#AOFcontactCombo").combobox("getValue")!=null)){
			    			$.ajax({
								url:url,
								data:bo,
								novalidate:false,
								success:function(data){
									if(data.result=="1"){
										$.messager.alert("提示","保存成功");
										$("#AOFPanel").dialog("close");
										$("#AOFForm").form("reset");
										loaddataToTab();
										$("#AOFPanel").dialog({closed:true});
									}
									else{
										$.messager.alert("警告","保存失败");
										$("#AOFForm").form("reset");
										}
									}
				    			})	
			    		}else{
			    			$.messager.alert("警告","您未选择负责人");
			    			}
			    		}
			    	}
				})
				
				/**
		 * 初始化保存按钮
		 * @returns
		 */
		$("#cancelBtn").linkbutton({
			 iconCls: 'icon-cancel',
			    onClick:function(){
			    	$("#AOFPanel").dialog({closed:true});
			    	$("#AOFForm").form("reset");
			    }
			});
		})
})

/**
 * 加载数据表格以及筛选查询的方法
 * @returns
 */
function loaddataToTab(){
	var bo = $("#selectWareHouseForm").serializeObject();
    $("#showWareHouseTable").datagrid( Common.createDatagridOptionsParams(basePath,"/loadWareHouseDataToTab.json",135,bo));

}


/**
 * 通过传入的参数来判断是否增加或修改
 * @param num
 * @returns
 */
function AOFController(num){
	$("#AOFrelShopRel").textbox("disable");
	
	if(num == 1){
		//设置url并且修改whcode的name属性
		url=basePath+"/addWareHouse.json";
		
		$("#AOFwhCodeDis").textbox("enable");
		$("#AOFwhCodeDis").textbox({width:120,required:true});
		$("#AOFPanel").dialog("open");
		$("#AOFPanel").dialog("setTitle","新增仓库");
		$("#AOFwhCodeDis").textbox("enable");
		
		$("#AOFrelShopRel").textbox("setValue","暂无");
		$("#AOFwhCodeDis").attr("name","whCode");
		$("#whCode").attr("name","noname");
		$("#trHide").hide();
		return;
		
	}else if(num == 0){
		//根据返回的行数判断是否多选、少选、不选
		var rows = $("#showWareHouseTable").datagrid("getSelections");
		if(rows.length>1){
			$.messager.alert('警告','阁下一次只能修改一条数据');
			return;
		}else if(rows.length<1){
			$.messager.alert('警告','请阁下选择一条要修改的数据');
			return;
		}
		else{
			url=basePath+"/updWareHouse.json";
			
			$("#AOFwhCodeDis").textbox("disable");
			$("#AOFPanel").dialog("open");
			$("#AOFPanel").dialog("setTitle","修改仓库信息");
			
			$("#AOFwhCodeDis").textbox("setValue",rows[0].whCode);
			$("#whCode").val(rows[0].whCode);
			$("#AOFwhName").textbox("setValue",rows[0].whName);
			$("#AOFphoneNo").textbox("setValue",rows[0].phoneNo);
			$("#AOFcontactCombo").combobox("select",rows[0].contact);			
			$("#AOFaddress").textbox("setValue",rows[0].address);
			$("#trHide").show();
		if(rows[0].relShop == null){
			$("#AOFrelShopRel").textbox("setValue","暂无");
		}else{
			$("#AOFrelShopRel").textbox("setValue",rows[0].relShop);
		}
		
		}
	}
}

//表单非重验证
function validateMetamanage() {
	$("#AOFwhCodeDis").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/warehouseValidateState.json','whCode']"
	});
}


/**
 * 删除选中的仓库信息
 */
function delWarehouse(){
	var rows = $("#showWareHouseTable").datagrid("getSelections");
	if(rows.length == 0 ){
		$.messager.alert("请选择需要删除的缺陷信息");
		return ;
	}else{
		$.messager.confirm('提示', '您是否要删除选中缺陷？',
				function(r){
					if(r){
						var delIds = [];
						rows.forEach(e=>{
							delIds.push(e.whCode);
						});
						$.ajax({
							url:basePath+"/delWarehouse.json",
							data:{"delIds":delIds},
							novalidate:false,
							success:function(data){
								if(data.result=="1"){
									$.messager.alert("提示","删除成功");
									loaddataToTab();
								}
								else{
									$.messager.alert("警告","删除失败");
									}
								}
							})
						}
					})
				}
			}


/**
 * 改变禁用启用状态
 */
function updStat(value,row,index){
	if(row.whStateId == "00"){
			return '<a href="#" style="color:red" onclick="modifyStatue(\''+row.whCode+'\',this)">&nbsp;启用&nbsp;</a>'
		}else{
			return'<a href="#" style="color:blue" onclick="modifyStatue(\''+row.whCode+'\',this)">&nbsp;禁用&nbsp;</a>'
		}
			
}

/**
 * 发送请求改变状态
 */
function modifyStatue(whCode,dom){
	
	var bo = {};
	bo["whCode"] = whCode;
		$.ajax({
			url:basePath+"/updWarehouseStatue.json",
			novalidate:false,
			data:bo,
			success:function(data){
				var $a = $(dom);
				if(data.result== 01){
					$a.css("color","blue").text("禁用");
				}
				else{
					$a.css("color","red").text("启用");
					}
				}
			})

}
