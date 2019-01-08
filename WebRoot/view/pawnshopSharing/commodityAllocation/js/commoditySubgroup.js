/**
 * <p>title:广沣共享典当管理系统-commoditySubgroup.js</p>
 * 
 * <p>Description:商品小类的js</p>
 * 
 * <p>Copyright:Copyright hbrc(c) 2018</p>
 * 
 * <p>Company:重庆汇博人才</p>
 * 
 * @author 张浩
 * @version 1.0
 */

var flag = false ;

$(function(){
	//初始化加载数据
	loading();
	
	//初始化load页面
	$("#add_modify_commoditySubgroup_Container").load("add_modify_commoditySubgroup_Container.html",null,function(){
		$("#add_modify_commoditySubgroup_Panel").dialog({
			closed:true ,
			modal:true ,
			buttons:'#saveAndCancel',
			onClose:cancelCommoditySubgroup
		});
		
		//设置分类编号输入框的格式
		$("#add_modify_commoditySubgroup_catCode").textbox({
			required:true,
			missingMessage:"分类编号不能为空",
			width:150
		})
		//设置分类名称输入框的格式
		$("#add_modify_commoditySubgroup_catName").textbox({
			required:true,
			missingMessage:"分类名称不能为空",
			width:150
		})
		//发送请求，获取所有商品大类的数据
		$.ajax({
			url:basePath+'/getP_CatNameDropdown.json',
			type:'post',
			async:false,
			dataType:'json',
			success:function(data){
				//设置input框格式为easyUI下拉列表的格式
				$("#add_modify_commoditySubgroup_pCatName").combobox({
				    valueField:'catCode', 
				    textField:'catName',
				    panelHeight:true,
				    required:true ,
				    editable:false ,
				    width:150 ,
				    data:data.rows ,
				});
			}
		})
		//设置备注输入框的格式
		$("#add_modify_commoditySubgroup_catDesc").textbox({
			required:true,
			missingMessage:"备注不能为空",
			width:150 ,
			height:50 ,
			multiline:true 
		})
		//设置文件上传框
		$("#add_modify_commoditySubgroup_file").filebox({
			buttonText: '选择文件', 
		    width:150,
		    buttonAlign: 'right',
		    onChange:function(){
		    	if($("#add_modify_commoditySubgroup_file").filebox("getValue") != '' && $("#add_modify_commoditySubgroup_file").filebox("getValue") != null){
			    	var fileInfo = $(this).filebox("getValue");
			    	//图片验证
					if(!(/^.+\.(jpg|jpeg|png)$/.test(fileInfo))){
						$.messager.alert("警告","请选择图片上传") ;
						$(this).filebox("setText","") ;
						return ;
					}
					var formData = new FormData();
					formData.append("upfile",$("input[type='file']")[0].files[0]);
					//新增附件
					$.ajax({
						url:basePath+"/addHeadImg.json",
						method:"post" ,
						data:formData ,
						cache: false, 
						processData: false, 
						contentType: false, 
						success:function(data){
							var resId = data.resId ;
							var resFile = data.resFile ;
							//附件回显
							$("#add_modify_commoditySubgroup_fileSrc").prop("src",basePath+"/previewPictures.json?resId="+resId+"&&resFile="+resFile);
						}
					})
		    	}
		    }
		})
		
		$("#saveCommoditySubgroup").linkbutton({
			text:"确定",
			iconCls:'icon-save',
			onClick:function(){
				//调用确认方法
				saveCommoditySubgroup();
			}
		})
		
		$("#cancelCommoditySubgroup").linkbutton({
			text:"取消",
			iconCls:'icon-clear',
			onClick:function(){
				//调用取消方法
				cancelCommoditySubgroup();
			}
		})
	})
})
/**
 * 初始化加载数据的方法
 * @returns
 */
function loading(){
	$("#commoditySubgroupTable").datagrid(Common.createDatagridOptionsParams(basePath, "/commoditySubgroupTable.json", 135,null));
}

/**
 * 设置显示效果的方法
 */
function showCommoditySubgroup(value){
	if (value == "1") {// 有效
		return "是";
	}else{// 无效
		return "否";
	}
}

/**
 * 设置显示级别的方法
 */
function showCatLvl(value){
	if (value == "2") {// 有效
		return "二级";
	}
}

/**
 * 操作的方法
 */
function caozuo(value,row,index){
	if(row.isShow=='1'){
		return '<a href=# onClick=conversion(event) value='+row.isShow+' id='+row.catCode+'>禁用</a> | <a href=# onClick=jump(event) id='+row.catCode+'>查看下级</a>';
	}else{
		return '<a href=# onClick=conversion(event) style=color:red value='+row.isShow+' id='+row.catCode+'>启用</a>';
	}
}

/**
 * 	禁用、启用状态方法
 */
function conversion(event){
	//获取所选中数据的ID和Value值
	var isShow = $(event.target).attr("value");
	var catCode = $(event.target).attr("id");
	var rows = $("#commoditySubgroupTable").datagrid("getData").rows ;
	var index  ;
	for(var i = 0 ; i < rows.length ; i++){
		if(rows[i].catCode == catCode){
			index = i ;
		}
	}
	//设置状态码
	if(isShow == "1"){
		isShow = "0" ;
	}else{
		isShow = "1" ;
	}
	$.ajax({
		url:basePath+"/conversion.json",
		type:"post",
		dataType:"json",
		data:{'catCode':catCode,'isShow':isShow},
		success:function(data){
			$('#commoditySubgroupTable').datagrid('updateRow',{
				index: index,
				row: {
					isShow:isShow
				}
			});
		}
	});
}

/**
 * 点击查询按钮的方法
 */
function queryCommoditySubgroup(){
	var keyWord = $("#keyWord").textbox("getValue");
	//重新加载表格数据
	$("#commoditySubgroupTable").datagrid("reload",{"keyWord":keyWord});
}

/**
 * 点击增加按钮的方法
 */
function addCommoditySubgroup(){
	
	flag = true ;
	$("#add_modify_commoditySubgroup_catCode").textbox("enable") ;
	$("#add_modify_commoditySubgroup_fileSrc").prop("src","../../resources/img/logo.png");
	$("#add_modify_commoditySubgroup_catCode").textbox({
		required:true ,
		onChange:yanzheng()
	})
	$("#add_modify_commoditySubgroup_Panel").dialog({
		title:"新增",
		width:300,
		closed:false
	})
}

/**
 *  验证的方法
 */
function yanzheng(){
	$("#add_modify_commoditySubgroup_catCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/yanZhengCatCode.json','catCode']"
	});
}

/**
 * 确认的方法
 */
function saveCommoditySubgroup(){
	var catCode = $("#add_modify_commoditySubgroup_catCode").textbox("getValue") ;
	var catName = $("#add_modify_commoditySubgroup_catName").textbox("getValue") ;
	var pCatCode = $("#add_modify_commoditySubgroup_pCatName").combobox("getValue") ;
	var catDesc = $("#add_modify_commoditySubgroup_catDesc").textbox("getValue") ;
	var obj = {"catCode":catCode,"catName":catName,"pCatCode":pCatCode,"catDesc":catDesc};
	if(catCode == '' || catCode == null){
		$.messager.alert("信息","编号不能为空") ;
		return ;
	}else if(catName == '' || catName == null){
		$.messager.alert("信息","名称不能为空") ;
		return ;
	}else if(pCatCode == '' || pCatCode == null){
		$.messager.alert("信息","上级分类不能为空") ;
		return ;
	}else if(catDesc == '' || catDesc == null){
		$.messager.alert("信息","描述不能为空") ;
		return ;
	}else{
		if(flag){
			$.ajax({
				url:basePath+'/addCommoditySubgroup.json',
				type:'post',
				data:obj ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
						//调用取消方法
						cancelCommoditySubgroup() ;
						//刷新页面
						loading();
						//提示
						$.messager.alert("信息","新增成功") ;
					}
				}
			})
			//发送获取附件的编号请求
			$.ajax({
				url:basePath+'/getPawnAttachCode.json',
				type:'post',
				dataType:'json',
				success:function(data){
					var resId = data.rows ;
					//发送新增附件与商品大类的关联数据的请求
					$.ajax({
						url:basePath+'/addPawnAttach.json',
						type:'post',
						data:{"resId":resId,"catCode":catCode},
						dataType:'json'
					})
				}
			})
		}else{
			$.ajax({
				url:basePath+'/updCommoditySubgroup.json',
				type:'post',
				data:obj ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
						//调用取消的方法
						cancelCommoditySubgroup();
						//刷新页面
						loading();
						//提示
						$.messager.alert("信息","修改成功") ;
					}
				}
			})
			//如果修改过附件
			if($("#add_modify_commoditySubgroup_file").filebox("getValue") != '' && $("#add_modify_commoditySubgroup_file").filebox("getValue") != null){
				//发送获取当前附件编号的请求
				$.ajax({
					url:basePath+'/getPawnAttachCode.json',
					type:'post',
					dataType:'json',
					success:function(data){
						var resId = data.rows ;
						//发送修改附件与商品大类的关联数据的请求
						$.ajax({
							url:basePath+'/updPawnAttachByCatCode.json',
							type:'post',
							data:{"resId":resId,"catCode":catCode},
							dataType:'json'
						})
					}
				})
			}
		}
	}
}

/**
 * 	取消的方法
 */
function cancelCommoditySubgroup(){
	$("#add_modify_commoditySubgroup_catCode").textbox("setValue","") ;
	$("#add_modify_commoditySubgroup_catName").textbox("setValue","") ;
	$("#add_modify_commoditySubgroup_pCatName").combobox("setValue","") ;
	$("#add_modify_commoditySubgroup_catDesc").textbox("setValue","") ;
	$("#add_modify_commoditySubgroup_file").filebox("setText","");
	$("#add_modify_commoditySubgroup_Panel").dialog({closed:true}) ;
}

/**
 * 	点击修改的方法
 */
function updCommoditySubgroup(){
	$("#add_modify_commoditySubgroup_fileSrc").attr("src","../../resources/img/logo.png");
	//获取表格中选中的数据
	var rows = $("#commoditySubgroupTable").datagrid("getSelections");
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
		$("#add_modify_commoditySubgroup_catCode").textbox("disable") ;
		var catCode = $("#commoditySubgroupTable").datagrid("getSelected").catCode ;
		var pCatCode = $("#commoditySubgroupTable").datagrid("getSelected").pCatCode ;
		//发送根据商品大类编号查询数据的请求
		$.ajax({
			url:basePath+'/getProductCatByCatCode.json',
			type:'post',
			data:{"catCode":catCode} ,
			dataType:'json',
			success:function(data){
				//给各个输入框赋值
				$("#add_modify_commoditySubgroup_catCode").textbox("setValue",data.rows.catCode);
				$("#add_modify_commoditySubgroup_catName").textbox("setValue",data.rows.catName);
				$("#add_modify_commoditySubgroup_catDesc").textbox("setValue",data.rows.catDesc);
				$("#add_modify_commoditySubgroup_pCatName").combobox("select",pCatCode) ;
			}
		})
		$.ajax({
			url:basePath+'/getPawnAttach.json',
			type:'post',
			data:{"catCode":catCode} ,
			dataType:'json',
			success:function(data){
				if(data.rows != null){
					var resFile = data.rows.resFile ;
					var resId =data.rows.resId ;
					$("#add_modify_commoditySubgroup_fileSrc").attr("src",basePath+"/previewPictures.json?resId="+resId+"&&resFile="+resFile);
				}
			}
		})
		
		$("#add_modify_commoditySubgroup_Panel").dialog({
			title:"修改",
			width:300,
			closed:false
		})
	}
}

/**
 * 删除的方法
 */
function delCommoditySubgroup(){
	var rows = $("#commoditySubgroupTable").datagrid("getSelections");
	if(rows.length == 0){
		$.messager.alert("信息","请先选择需要删除的数据") ;
		return ;
	}else{
		$.messager.confirm('提示', '您是否要删除选中数据？',
				function(r){
					if(r){
						var catCode = "";
						for (var i = 0; i < rows.length; i++) {
							if(i == rows.length - 1){
								catCode += rows[i].catCode ;
							}else{
								catCode += rows[i].catCode + "," ;
							}
						}
						//删除附件和商品大类相关联的请求
						$.ajax({
							url:basePath+"/removePawnAttachByCatCode.json",
							type:"post",
							data:{"catCode":catCode},
							dataType:"json"
						})
						//删除商品大类的请求
						$.ajax({
							url:basePath+"/removeProductCatByCatCode.json",
							type:"post",
							data:{"catCode":catCode},
							dataType:"json",
							success:function(data){
								if(data.rows > 0){
									loading();
									$.messager.alert("信息","删除成功") ;
								}
							}
						})
					}
				})
	}
}

/**
 * 点击查看下级的方法
 */
function jump(event){
	var catCode = $(event.target).attr("id");
	window.location.href="inferiorCommodities.html?valus="+catCode ;
}