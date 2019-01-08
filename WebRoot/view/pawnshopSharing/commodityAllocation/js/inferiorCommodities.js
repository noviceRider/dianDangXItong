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

//定义一个全局变量来判断是新增还是修改
var flag = false ;


$(function(){
	//初始化加载数据
	loading() ;
	
	//初始化load页面
	$("#add_modify_inferiorCommodities_Container").load("add_modify_inferiorCommodities_Container.html",null,function(){
		$("#add_modify_inferiorCommodities_Panel").dialog({
			closed:true ,
			modal:true ,
			buttons:'#saveAndCancel',
			onClose:cancelInferiorCommodities
		});
		
		//设置分类编号输入框的格式
		$("#add_modify_inferiorCommodities_catCode").textbox({
			required:true,
			missingMessage:"分类编号不能为空",
			width:150
		})
		//设置分类名称输入框的格式
		$("#add_modify_inferiorCommodities_catName").textbox({
			required:true,
			missingMessage:"分类名称不能为空",
			width:150
		})
		//设置分类单位输入框的格式
		$("#add_modify_inferiorCommodities_unit").textbox({
			required:true,
			missingMessage:"分类单位不能为空",
			width:150
		})
		//设置备注输入框的格式
		$("#add_modify_inferiorCommodities_catDesc").textbox({
			required:true,
			missingMessage:"备注不能为空",
			width:150 ,
			height:50 ,
			multiline:true 
		})
		//设置文件上传框
		$("#add_modify_inferiorCommodities_file").filebox({
			buttonText: '选择文件', 
		    width:150,
		    buttonAlign: 'right',
		    onChange:function(){
		    	if($("#add_modify_inferiorCommodities_file").filebox("getValue") != '' && $("#add_modify_inferiorCommodities_file").filebox("getValue") != null){
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
							$("#add_modify_inferiorCommodities_fileSrc").prop("src",basePath+"/previewPictures.json?resId="+resId+"&&resFile="+resFile);
						}
					})
		    	}
		    }
		})
		
		$("#saveInferiorCommodities").linkbutton({
			text:"确定",
			iconCls:'icon-save',
			onClick:function(){
				//调用确认方法
				saveInferiorCommodities();
			}
		})
		
		$("#cancelInferiorCommodities").linkbutton({
			text:"取消",
			iconCls:'icon-clear',
			onClick:function(){
				//调用取消方法
				cancelInferiorCommodities();
			}
		})
	})

})

/**
 * 获取上个页面传递的参数的方法
 * @returns
 */
function oneValues(){
	var result ;
	var url = window.location.search ;
	if(url.indexOf("?")!=-1){
		result = url.substr(url.indexOf("=")+1) ;
	}
	return result ; 
}

/**
 * 加载页面数据的方法
 * @returns
 */
function loading(){
	$("#inferiorCommoditiesTable").datagrid(Common.createDatagridOptionsParams(basePath, "/inferiorCommoditiesTable.json", 135,{"pCatCode":oneValues()}));
}

/**
 * 点击返回按钮的方法
 * 返回商品小类页面
 */
function backCommoditySubgroup(){
	window.location.href="commoditySubgroup.html" ;
}

/**
 * 设置显示效果的方法
 */
function showInferiorCommodities(value){
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
	if (value == "3") {// 有效
		return "三级";
	}
}

/**
 * 操作的方法
 */
function caozuo(value,row,index){
	if(row.isShow=='1'){
		return '<a href=# onClick=conversion(event) value='+row.isShow+' id='+row.catCode+'>禁用</a>';
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
	var rows = $("#inferiorCommoditiesTable").datagrid("getData").rows ;
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
			$('#inferiorCommoditiesTable').datagrid('updateRow',{
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
 * @returns
 */
function queryInferiorCommodities(){
	var keyWord = $("#keyWord").textbox("getValue");
	//重新加载表格数据
	$("#inferiorCommoditiesTable").datagrid("reload",{"pCatCode":oneValues(),"keyWord":keyWord});
}

/**
 * 	点击增加按钮
 */
function addInferiorCommodities(){
	flag = true ;
	$("#catCode").text("分类编号"+oneValues() +".");
	$("#add_modify_inferiorCommodities_catCode").textbox("enable") ;
	$("#add_modify_inferiorCommodities_fileSrc").prop("src","../../resources/img/logo.png");
	$("#add_modify_inferiorCommodities_catCode").textbox({
		required:true ,
		onChange:yanzheng()
	})
	$("#add_modify_inferiorCommodities_Panel").dialog({
		title:"新增",
		width:300,
		closed:false
	})
}

/**
 *  验证的方法
 */
function yanzheng(){
	var catCode = oneValues() + "." + $("#add_modify_inferiorCommodities_catCode").textbox("getValue") ;
	$("#add_modify_inferiorCommodities_catCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/yanZhengSanJICatCode.json','catCode', " + 
		"{logicId:'09.03.01', pCatCode:'" + oneValues() + ".'}]"
	});
}


/**
 * 	点击确认的方法
 * @returns
 */
function saveInferiorCommodities(){
	var catCode = oneValues() + "." + $("#add_modify_inferiorCommodities_catCode").textbox("getValue") ;
	var catCode1 = $("#add_modify_inferiorCommodities_catCode").textbox("getValue") ;
	var catName = $("#add_modify_inferiorCommodities_catName").textbox("getValue") ;
	var pCatCode = oneValues();
	var catDesc = $("#add_modify_inferiorCommodities_catDesc").textbox("getValue") ;
	var unit = $("#add_modify_inferiorCommodities_unit").textbox("getValue") ;
	var obj = {"catCode":catCode,"catName":catName,"pCatCode":pCatCode,"catDesc":catDesc,"unit":unit};
	var obj1 = {"catCode":catCode1,"catName":catName,"pCatCode":pCatCode,"catDesc":catDesc,"unit":unit};
	if(catCode1 == '' || catCode1 == null){
		$.messager.alert("信息","编号不能为空") ;
		return ;
	}else if(catName == '' || catName == null){
		$.messager.alert("信息","名称不能为空") ;
		return ;
	}else if(catDesc == '' || catDesc == null){
		$.messager.alert("信息","描述不能为空") ;
		return ;
	}else if(unit == '' || unit == null){
		$.messager.alert("信息","单位不能为空") ;
		return ;
	}else{
		if(flag){
			$.ajax({
				url:basePath+'/addInferiorCommodities.json',
				type:'post',
				data:obj ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
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
			//调用取消方法
			cancelInferiorCommodities() ;
		}else{
			$.ajax({
				url:basePath+'/updInferiorCommodities.json',
				type:'post',
				data:obj1 ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
						//调用取消的方法
						cancelInferiorCommodities();
						//刷新页面
						loading();
						//提示
						$.messager.alert("信息","修改成功") ;
					}
				}
			})
			if($("#add_modify_inferiorCommodities_file").filebox("getValue") != '' && $("#add_modify_inferiorCommodities_file").filebox("getValue") != null){
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
							data:{"resId":resId,"catCode":catCode1},
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
function cancelInferiorCommodities(){
	$("#add_modify_inferiorCommodities_catCode").textbox("setValue","") ;
	$("#add_modify_inferiorCommodities_catName").textbox("setValue","") ;
	$("#add_modify_inferiorCommodities_catDesc").textbox("setValue","") ;
	$("#add_modify_inferiorCommodities_unit").textbox("setValue","") ;
	$("#add_modify_inferiorCommodities_file").filebox("setText","");
	$("#add_modify_inferiorCommodities_Panel").dialog({closed:true}) ;
}

/**
 * 	点击修改按钮的方法
 */
function updInferiorCommodities(){
	$("#add_modify_inferiorCommodities_fileSrc").attr("src","../../resources/img/logo.png");
	//获取表格中选中的数据
	var rows = $("#inferiorCommoditiesTable").datagrid("getSelections");
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
		$("#add_modify_inferiorCommodities_catCode").textbox("disable") ;
		var catCode = $("#inferiorCommoditiesTable").datagrid("getSelected").catCode ;
		var pCatCode = $("#inferiorCommoditiesTable").datagrid("getSelected").pCatCode ;
		//发送根据商品大类编号查询数据的请求
		$.ajax({
			url:basePath+'/getProductCatByCatCode.json',
			type:'post',
			data:{"catCode":catCode} ,
			dataType:'json',
			success:function(data){
				//给各个输入框赋值
				$("#add_modify_inferiorCommodities_catCode").textbox("setValue",data.rows.catCode);
				$("#add_modify_inferiorCommodities_catName").textbox("setValue",data.rows.catName);
				$("#add_modify_inferiorCommodities_catDesc").textbox("setValue",data.rows.catDesc);
				$("#add_modify_inferiorCommodities_unit").textbox("setValue",data.rows.unit);
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
					$("#add_modify_inferiorCommodities_fileSrc").attr("src",basePath+"/previewPictures.json?resId="+resId+"&&resFile="+resFile);
				}
			}
		})
		$("#catCode").text("分类编号");
		$("#add_modify_inferiorCommodities_Panel").dialog({closed:false}) ;
	}
}

/**
 * 删除的方法
 */
function delInferiorCommodities(){
	var rows = $("#inferiorCommoditiesTable").datagrid("getSelections");
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