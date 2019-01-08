/**
 * <p>title:广沣共享典当管理系统-storeInformation.js</p>
 * 
 * <p>Description:门店管理的js</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */

//定义一个全局变量来判断是新增还是修改
var falg = false ;

/**
 * 初始化加载函数
 * @returns
 */
$(function(){
	
	//加载状态下拉列表方法
	stateSpinner();
	
	//初始化加载数据方法
	loading();
	
	//将新增修改页面load进div里面
	$("#add_modify_store_Container").load("add_modify_store_Container.html",null,function(){
		//初始化新增窗口
		$.parser.parse(this);
		$("#add_modify_store_Panel").dialog({
			closed:true
		});
		
		//设置input框格式为easyUI格式
		$("#add_modify_store_shopCode").textbox({
			required:true,
			missingMessage:"门店编号编号不能为空",
			width:150,
			//调用验证方法
			onChange:yanzheng
		})
		$("#add_modify_store_shopName").textbox({
			required:true,
			missingMessage:"门店名称不能为空",
			width:150
		})
		$("#add_modify_store_contact").textbox({
			required:true,
			missingMessage:"联系人不能为空",
			width:150
		})
		$("#add_modify_store_phoneNo").textbox({
			required:true,
			missingMessage:"联系方式不能为空",
			width:150
		})
		$("#add_modify_store_address").textbox({
			required:true,
			missingMessage:"地址不能为空",
			width:150
		})
		$("#selectWarehouseByYes").textbox({
			width:150,
			icons:[{
			     iconCls:'icon-search',
			     //调用搜索已选仓库方法
			     handler:searchSelectWarehouseByYes
			}]
		})
		$("#selectWarehouseByNo").textbox({
			width:150,
			icons:[{
			     iconCls:'icon-search',
			     //调用搜索待选仓库方法
			     handler:searchSelectWarehouseByNo
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
		
		$("#YES").linkbutton({
			text:"确定",
			iconCls:'icon-save',
			onClick:function(){
				//调用确认方法
				confirm();
			}
		});
		
		$("#NO").linkbutton({
			text:"取消",
			iconCls:'icon-clear',
			onClick:function(){
				//调用取消方法
				cancel();
			}
		});
	})
})

/**
 * 	状态下拉的方法
 * @returns
 */
function stateSpinner(){
	$.ajax({
		url:basePath+'/getshopStateDropdown.json',
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			//设置input框格式为easyUI下拉列表的格式
			$("#state").combobox({
			    valueField:'shopState', 
			    textField:'shopDesc',
			    panelHeight:'auto',
			    editable:false ,
			    width:80 ,
			    data:data.rows ,
			    loadFilter:function(data){
			  	   data.unshift({shopState:'',shopDesc:'--请选择--'});
			  	   return data;
				}
			});
		}
	})
}

/**
 * 	页面初始化加载方法
 * @returns
 */
function loading(){
	$("#storeTable").datagrid(Common.createDatagridOptionsParams(basePath, "/getStore.json", 135,null));
}

/**
 * 	禁用、启用状态方法
 */
function operation(event){
	//获取所选中数据的ID和Value值
	var shopState = $(event.target).attr("value");
	var shopCode = $(event.target).attr("id");
	//设置状态码
	if(shopState == "01"){
		shopState = "00" ;
	}else{
		shopState = "01" ;
	}
	$.ajax({
		url:basePath+"/operation.json",
		type:"post",
		dataType:"json",
		data:{'shopCode':shopCode,'shopState':shopState},
		success:function(data){
			//调用初始化加载方法
			loading();
		}
	});
}

/**
 * 	设置禁用和启用超链接的方法
 */
function sun(value,row,index){
	//设置页面上显示的文字和格式
	if(row.shopState =='01'){
		return"<a href='#' onClick='operation(event)' value="+row.shopState+" id="+row.shopCode+" style='color:blue'>禁用</a>";
	}else{
		return"<a href='#' onClick='operation(event)' value="+row.shopState+" id="+row.shopCode+" style='color:red'>启用</a>";
	} 
}

/**
 * 	点击查询按钮的方法
 */
function queryStore(){
	//获取关键字和状态下拉列表的value值
	var keyWord = $("#keyWord").textbox("getValue");
	var state = $("#state").combobox("getValue");

	//重新加载数据
	$("#storeTable").datagrid("reload",{"keyWord":keyWord,"state":state});
}

/**
 * 新增打开面板的方法
 */
function addStore(){
	//设置公共变量为true
	falg = true ;
	//让input框可输入值
	$("#add_modify_store_shopCode").textbox("enable");
	//设置面板格式
	$("#add_modify_store_Panel").dialog({
		title:"新增",
		iconCls:'icon-man',
		width:400,
		height:460,
		modal:true ,
		buttons:'#buttons',
		onClose:cancel() ,
		required:true ,
		closed:false
	});
	//调用搜索待选仓库方法
	searchSelectWarehouseByNo();
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
 * 	新增加载待选仓库面板数据的方法
 * @returns
 */
function searchSelectWarehouseByNo(){
	//获取关键字输入框的value值
	var keyword = $("#selectWarehouseByNo").textbox("getValue") ;
	//删除待选仓库里面的option
	$("#left option").remove();
	//设置一个变量来保存已选仓库的option的value值
	var keyByYes ;
	//判断已选仓库的option的个数
	if($("#right option").length == 0){
		keyByYes = "''";
	}else{
		//获取已选仓库的option的value值
		keyByYes = $("#right option").map(function(){return $(this).val();}).get().join(",");
	}
	$.ajax({
		url:basePath+"/getWareHouse.json",
		type:"post",
		data:{"keyword":keyword,"keyByYes":keyByYes},
		dataType:"json",
		success:function(data){
			for (var i = 0; i < data.rows.length; i++) {
				//动态的给待选仓库的select中添加option节点
				$("#left").append('<option value='+data.rows[i].whCode+'>'+data.rows[i].whName+'</option>');
			}
		}
	});
}

/**
 * 	确认的方法
 */
function confirm(){
	//获取各个输入框的value值
	var shopCode = $("#add_modify_store_shopCode").textbox("getValue");
	var shopName = $("#add_modify_store_shopName").textbox("getValue");
	var contact = $("#add_modify_store_contact").textbox("getValue");
	var phoneNo = $("#add_modify_store_phoneNo").textbox("getValue");
	var address = $("#add_modify_store_address").textbox("getValue");
	//将各个输入框的值添加到一个对象中
	var obj = {"shopCode":shopCode,"shopName":shopName,"contact":contact,"phoneNo":phoneNo,"address":address};
	//获取已选仓库的option的value值
	var whCode = $("#right option").map(function(){return $(this).val();}).get().join(",");
	//如果是新增
	if(falg){
		//增加店铺
		$.ajax({
			url:basePath+"/addStore.json",
			type:"post",
			data:obj,
			dataType:"json",
			success:function(data){
				if(data.rows > 0){
					$.messager.alert("提示","新增成功!");
					$("#add_modify_store_Panel").dialog({closed:true})
				}
			}
		});
		//增加店铺所关联的仓库
		$.ajax({
			url:basePath+"/addStoreAndWare.json",
			type:"post",
			data:{"whCode":whCode,"shopCode":shopCode},
			dataType:"json",
			success:function(data){
				
			}
		});
		//调用加载数据的方法
		loading();
		//如果是修改
	}else{
		//修改店铺
		$.ajax({
			url:basePath+"/updStore.json",
			type:"post",
			data:obj,
			dataType:"json",
			success:function(data){
				if(data.rows > 0){
					$.messager.alert("提示","修改成功!");
					$("#add_modify_store_Panel").dialog({closed:true})
				}
			}
		});
		//删除与修改店铺有关的关联仓库数据
		$.ajax({
			url:basePath+"/removeStoreAndWare.json",
			type:"post",
			data:{"shopCode":shopCode},
			dataType:"json",
			success:function(data){
				//新增与修改店铺有关的关联仓库数据
				$.ajax({
					url:basePath+"/addStoreAndWare.json",
					type:"post",
					data:{"whCode":whCode,"shopCode":shopCode},
					dataType:"json",
					success:function(data){
						loading();
					}
				});
			}
		});
	}
}

/**
 * 	取消的方法
 */
function cancel(){
	//清空各个input框的值
	$("#add_modify_store_shopCode").textbox("setValue",'');
	$("#add_modify_store_shopName").textbox("setValue",'');
	$("#add_modify_store_contact").textbox("setValue",'');
	$("#add_modify_store_phoneNo").textbox("setValue",'');
	$("#add_modify_store_address").textbox("setValue",'');
	$("#selectWarehouseByYes").textbox("setValue",'');
	$("#selectWarehouseByNo").textbox("setValue",'');
	//删除待选仓库的所有option节点
	$("#left option").remove();
	//删除已选仓库的所有option节点
	$("#right option").remove();
	//关闭面板
	$("#add_modify_store_Panel").dialog({closed:true});
}

/**
 *  验证的方法
 */
function yanzheng(){
	$("#add_modify_store_shopCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/yanzheng.json','shopCode']"
	});
}

/**
 * 	新增加载已选仓库面板数据的方法
 */
function searchSelectWarehouseByYes(){
	$("#right option").attr("selected",false);
	$("#right option").css("background","");
	var keyword = $("#selectWarehouseByYes").textbox("getValue") ;
	var reg = new RegExp(keyWord);
	for(var i = 0 ; i <$("#right option").length ; i++){
		if($("#right option")[i].text.indexOf(keyword) != -1){
			$("#right option").eq(i).attr("selected",true);
			$("#right option").eq(i).css("background","blue");
		}
	}
}

/**
 * 	打开修改的面板
 */
function updStore(){
	//获取被选中数据的数据
	var rows = $("#storeTable").datagrid("getSelections");
	//判断被选中数据的条数
	if(rows.length > 1){
		$.messager.alert("提示","每次只能修改一条数据!");
	}else if(rows.length < 1){
		$.messager.alert("提示","请选择需要修改的数据!");
	}else{
		//设置全局变量为false
		falg = false ;
		//将编号input输入框设置为不可更改
		$("#add_modify_store_shopCode").textbox("disable");
		//获取修改数据的编号
		var shopCode = rows[0].shopCode ;
		//查询编号所对应的数据
		$.ajax({
			url:basePath+"/getStoreByShopCode.json",
			type:"post",
			data:{"shopCode":shopCode},
			dataType:"json",
			success:function(data){
				//给个input框设置值
				$("#add_modify_store_shopCode").textbox("setValue",data.result.shopCode);
				$("#add_modify_store_shopName").textbox("setValue",data.result.shopName);
				$("#add_modify_store_contact").textbox("setValue",data.result.contact);
				$("#add_modify_store_phoneNo").textbox("setValue",data.result.phoneNo);
				$("#add_modify_store_address").textbox("setValue",data.result.address);
			}
		});
		//根据编号获取已选仓库的数据
		$.ajax({
			url:basePath+"/getWareHouseByShopCode.json",
			type:"post",
			data:{"shopCode":shopCode},
			dataType:"json",
			success:function(data){
				//如果已选仓库有数据
				if(data.rows.length >0){
					//动态的给已选仓库添加option节点
					var whCode = "";
					for (var i = 0; i < data.rows.length; i++) {
						$("#right").append('<option value='+data.rows[i].whCode+'>'+data.rows[i].whName+'</option>');
						if(i == data.rows.length -1){
							whCode += data.rows[i].whCode
						}else{
							whCode += data.rows[i].whCode +","
						}
					}
					//根据编号查询待选仓库的数据
					$.ajax({
						url:basePath+"/getWareHouseNotInRight.json",
						type:"post",
						data:{"whCode":whCode},
						dataType:"json",
						success:function(data){
							//动态的给待选仓库添加节点
							for (var i = 0; i < data.rows.length; i++) {
								$("#left").append('<option value='+data.rows[i].whCode+'>'+data.rows[i].whName+'</option>');
							}
						}
					});
					//如果已选仓库没有数据
				}else{
					var keyword ="" ;
					var keyByYes ="" ;
					$.ajax({
						url:basePath+"/getWareHouse.json",
						type:"post",
						data:{"keyword":keyword,"keyByYes":keyByYes},
						dataType:"json",
						success:function(data){
							//动态的给待选仓库添加节点
							for (var i = 0; i < data.rows.length; i++) {
								$("#left").append('<option value='+data.rows[i].whCode+'>'+data.rows[i].whName+'</option>');
							}
						}
					});
				}
			}
		});
		//设置面板格式
		$("#add_modify_store_Panel").dialog({
			title:"修改",
			iconCls:'icon-man',
			width:400,
			height:460,
			modal:true ,
			buttons:'#buttons',
			onClose:cancel() ,
			required:true ,
			closed:false
		});
	}
}

/**
 * 	删除的方法
 */
function delStore(){
	//获取需要删除的数据的数据
	var rows = $("#storeTable").datagrid("getSelections");
	//如果没有选中数据
	if(rows.length == 0 ){
		$.messager.alert('警告','没有选择要删除的数据，请先进行选择！！');   
		return;
		//如果有选中数据
	}else{
		$.messager.confirm('提示', '您是否要删除选中的数据？', 
				function(r){
					if (r){
						//获取选中的数据的编号
						var shopCode = "";
						for (var i = 0; i < rows.length; i++) {
							if(i == rows.length - 1){
								shopCode += rows[i].shopCode ;
							}else{
								shopCode += rows[i].shopCode + "," ;
							}
						}
						//根据编号删除店铺数据
						$.ajax({
							url:basePath+"/delStore.json",
							type:"post",
							data:{"shopCode":shopCode},
							dataType:"json",
							success:function(data){
								
							}
						});
						//根据店铺编号删除与之有所关联的仓库数据
						$.ajax({
							url:basePath+"/removeStoreAndWare.json",
							type:"post",
							data:{"shopCode":shopCode},
							dataType:"json",
							success:function(data){
								if(data.rows > 0){
									$.messager.alert('信息','删除的数据成功！！');
									loading();
								}
							}
						})
					}
		});
	}
}
