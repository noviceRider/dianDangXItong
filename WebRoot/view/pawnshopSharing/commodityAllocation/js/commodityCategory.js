/**
 * <p>title:广沣共享典当管理系统-productCat.js</p>
 * 
 * <p>Description:商品大类的js</p>
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

//初始化加载函数
$(function(){
	//初始化加载表格数据
	loading() ;
	
	//初始化load页面
	$("#add_modify_productCat_Container").load("add_modify_productCat_Container.html",null,function(){
		$("#add_modify_productCat_Panel").dialog({
			closed:true ,
			buttons:'#saveAndCancel',
			modal:true ,
			onClose:cancelProductCat
		});
		//初始化设置格式
		$("#add_modify_productCat_catCode").textbox({
			required:true,
			missingMessage:"分类编号不能为空",
			width:150
		})
		//初始化设置格式
		$("#add_modify_productCat_catName").textbox({
			required:true,
			missingMessage:"分类名称不能为空",
			width:150
		})
		//初始化设置格式
		$("#add_modify_productCat_file").filebox({
			buttonText: '选择文件', 
		    width:150,
		    buttonAlign: 'right',
		    onChange:function(){
		    	if($("#add_modify_productCat_file").filebox("getValue") != '' && $("#add_modify_productCat_file").filebox("getValue") != null){
			    	var fileInfo = $(this).filebox("getValue");
			    	//图片验证
					if(!(/^.+\.(jpg|jpeg|png)$/.test(fileInfo))){
						$.messager.alert("警告","请选择图片上传") ;
						$(this).filebox("setText","") ;
						return ;
					}
					var formData = new FormData();
					formData.append("upfile",$("input[type='file']")[0].files[0]);
					console.log($("input[type='file']")[0].files[0]);
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
							$("#add_modify_productCat_fileSrc").prop("src",basePath+"/previewPictures.json?resId="+resId+"&&resFile="+resFile);
						}
					})
		    	}
		    }
		})
		//初始化设置格式
		$("#add_modify_productCat_catDesc").textbox({
			required:true,
			missingMessage:"分类备注不能为空",
			width:150 ,
			height:50 ,
			multiline:true 
		})
		//初始化设置格式
		$("#saveProductCat").linkbutton({
			text:"确定",
			iconCls:'icon-save',
			onClick:function(){
				//调用确认方法
				saveProductCat();
			}
		})
		//初始化设置格式
		$("#cancelProductCat").linkbutton({
			text:"取消",
			iconCls:'icon-clear',
			onClick:function(){
				//调用取消方法
				cancelProductCat();
			}
		});
		//初始化设置格式
		$("#addTrNodes").linkbutton({
			text:"新增",
			onClick:function(){
				//动态的添加节点
				$("#authenticate").append("<tr><td><input type='text' name='picType'/></td>" +
		    			"<td>&nbsp;&nbsp;<input  name='flat'>" +
		    			"</td><td>&nbsp;&nbsp;<a name='linkBtn'>删除</a></td></tr>")
		    			//初始化设置格式
		    			$("a[name='linkBtn']").linkbutton({
		    				iconCls:'icon-remove',
		    				onClick:function(){
		    					/**
		    					 * 删除对应tr节点
		    					 */
		    					$(this).parent().parent().remove();
		    				}
		    			})
		    			//初始化设置格式
		    			$("input[name='picType']").textbox({width:120,required:true,missingMessage:'请输入定义图名称'});
		    			$("input[name='flat']").combobox({
		    				required:true,
		    				missingMessage:'选项不能为空',
		    				panelHeight:true,
		    				width:55 ,
		    				valueField: 'label',
		    				textField: 'value',
		    				data: [{
		    					label: '0',
		    					value: '可选'
		    				},{
		    					label: '1',
		    					value: '必选'
		    				}],
		    				onLoadSuccess:function(data){
		    					var array = $(this).combobox("getData");
		    					for(var item in array[0]){
		    						if(item == "label"){
		    							$(this).combobox("select",array[0][item]);
		    						}
		    					}
		    				}
		    			})
					 }
				});

	})
})

/**
 * 初始化加载数据的方法
 * @returns
 */
function loading(){
	$("#commodityTable").datagrid(Common.createDatagridOptionsParams(basePath, "/getCommodity.json", 135,null));
}

/**
 * 	点击查询按钮的方法
 * @returns
 */
function queryCommodity(){
	var keyWord = $("#keyWord").textbox("getValue");
	//重新加载表格数据
	$("#commodityTable").datagrid("reload",{"keyWord":keyWord});
}

/**
 * 	点击增加按钮的方法
 * @returns
 */
function addCommodity(){
	//设置全局变量为true
	flag = true ;
	$("#add_modify_productCat_catCode").textbox("enable");
	$("#add_modify_productCat_fileSrc").prop("src","../../resources/img/logo.png");
	$("#add_modify_productCat_catCode").textbox({
		required:true ,
		//调用表单验证的方法
		onChange:yanzheng()
	})
	//打开面板
	$("#add_modify_productCat_Panel").dialog({
		title:"新增",
		width:300,
		closed:false
	})
}

/**
 * 确定的方法
 * @returns
 */
function saveProductCat(){
	//获取各个输入框的值
	var formData = $("#add_modify_productCat_Form").serializeObject();
	var a = [];
	if(JSON.stringify(formData) != "{}"){
		for (var i = 0; i < formData.flat.length; i++) {
			if(i == formData.flat.length -1){
				a.push({"picType":formData.picType,"flat" :formData.flat});
			}else{
				a.push({"picType":formData.picType[i],"flat" :formData.flat[i]});
			}
		}
	}else{
		$.messager.alert("信息","请添加鉴定图定义") ;
		return ;
	}
	var catCode = $("#add_modify_productCat_catCode").textbox("getValue");
	var catName = $("#add_modify_productCat_catName").textbox("getValue");
	var catDesc = $("#add_modify_productCat_catDesc").textbox("getValue");
	//设置为JSON格式的字符串
	var evalPicDef = JSON.stringify(a);
	if(catCode ==''|| catCode == null ){
		$.messager.alert("信息","编号不能为空") ;
		return ;
	}else if(catName == '' || catName == null){
		$.messager.alert("信息","名称不能为空") ;
		return ;
	}else if(catDesc == '' || catDesc == null){
		$.messager.alert("信息","描述不能为空") ;
		return ;
	}else if(a.length == 0){
		$.messager.alert("信息","鉴定图定义不能为空") ;
		return ;
	}else if(evalPicDef == '[{"flat":"0"}]'){
		$.messager.alert("信息","请添加鉴定图名称") ;
		return ;
	}else{
	//如果是新增
		if(flag){
			var obj = {"catCode":catCode,"catName":catName,"catDesc":catDesc,"evalPicDef":evalPicDef} ;
			//发送新增请求
			$.ajax({
				url:basePath+'/addProductCat.json',
				type:'post',
				data:obj ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
						//调用取消的方法
						cancelProductCat() ;
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
		//如果是修改
		}else{
			var obj = {"catCode":catCode,"catName":catName,"catDesc":catDesc,"evalPicDef":evalPicDef} ;
			//发送修改商品大类的请求
			$.ajax({
				url:basePath+'/updProductCat.json',
				type:'post',
				data:obj ,
				dataType:'json',
				success:function(data){
					if(data.rows > 0){
						//关闭面板
						$("#add_modify_productCat_Panel").dialog({closed:true}) ;
						//调用取消的方法
						cancelProductCat();
						//刷新页面
						loading();
						//删除节点
						$("#authenticate tr").remove();
						//提示
						$.messager.alert("信息","修改成功") ;
					}
				}
			})
			//如果修改过附件
			if($("#add_modify_productCat_file").filebox("getValue") != '' && $("#add_modify_productCat_file").filebox("getValue") != null){
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
 * 取消的方法
 */
function cancelProductCat(){
	//将各个输入框清空
	$("#add_modify_productCat_catCode").textbox("setValue","");
	$("#add_modify_productCat_catName").textbox("setValue","");
	$("#add_modify_productCat_catDesc").textbox("setValue","");
	$("#add_modify_productCat_file").filebox("setText","");
	//删除节点
	$("#authenticate tr").remove();
	//关闭面板
	$("#add_modify_productCat_Panel").dialog({closed:true}) ;
}

/**
 * 点击修改的方法
 */
function updCommodity(){
	//获取表格中选中的数据
	var rows = $("#commodityTable").datagrid("getSelections");
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
		//让编号输入框不可更改
		$("#add_modify_productCat_catCode").textbox("disable");
		//获取商品大类的编号
		var catCode = rows[0].catCode ;
		//发送根据商品大类编号查询数据的请求
		$.ajax({
			url:basePath+'/getProductCatByCatCode.json',
			type:'post',
			data:{"catCode":catCode} ,
			dataType:'json',
			success:function(data){
				//给各个输入框赋值
				$("#add_modify_productCat_catCode").textbox("setValue",data.rows.catCode);
				$("#add_modify_productCat_catName").textbox("setValue",data.rows.catName);
				$("#add_modify_productCat_catDesc").textbox("setValue",data.rows.catDesc);
				//如果鉴定图定义数据不为空
				if(data.rows.evalPicDef != null){
					//将JSON格式的字符串解析成数组
					var evalPicDef = JSON.parse(data.rows.evalPicDef);
					//遍历数组
					for(var i = 0 ; i < evalPicDef.length ;i++){
						//动态的添加节点
						$("#authenticate").append("<tr><td><input type='text' id='p"+i+"' name='picType'/></td>" +
				    			"<td>&nbsp;&nbsp;<input name='flat' id='f"+i+"'>" +
				    			"</td><td>&nbsp;&nbsp;<a name='linkBtn'>删除</a></td></tr>")
				    			//设置超链接格式
				    			$("a[name='linkBtn']").linkbutton({
				    				iconCls:'icon-remove',
				    				onClick:function(){
				    					/**
				    					 * 删除对应tr节点
				    					 */
				    					$(this).parent().parent().remove();
				    				}
				    			})
				    			//设置输入框格式
				    			$("input[name='picType']").textbox({width:120,required:true,missingMessage:'请输入定义图名称'});
								//设置下拉选项框的格式
				    			$("input[name='flat']").combobox({
				    				required:true,
				    				panelHeight:true,
				    				width:55 ,
				    				valueField: 'label',
				    				textField: 'value',
				    				data: [{
				    					label: '0',
				    					value: '可选'
				    				},{
				    					label: '1',
				    					value: '必选'
				    				}]
	
				    			})
		    			if(evalPicDef.length == 1){
		    				$("#p"+i).textbox("setValue",evalPicDef[i].picType);
		    				$("#f"+i).combobox("setValue",evalPicDef[i].flat[i]);
		    			}else{
		    				if(i == evalPicDef.length - 1){
			    				$("#p"+i).textbox("setValue",evalPicDef[i].picType[i]);
			    				$("#f"+i).combobox("setValue",evalPicDef[i].flat[i]);
			    			}else{
			    				//给输入框赋值
			    				$("#p"+i).textbox("setValue",evalPicDef[i].picType) ;
			    				//给下拉选项框
			    				$("#f"+i).combobox("setValue",evalPicDef[i].flat) ;
			    			}
		    			}
					}
				}
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
					$("#add_modify_productCat_fileSrc").prop("src",basePath+"/previewPictures.json?resId="+resId+"&&resFile="+resFile);
				}else{
					$("#add_modify_productCat_fileSrc").prop("src","../../resources/img/logo.png");
				}
			}
		})
		
		$("#add_modify_productCat_Panel").dialog({
			title:"修改",
			width:300,
			closed:false
		})
	}
}

/**
 * 点击删除的方法
 */
function delCommodity(){
	var rows = $("#commodityTable").datagrid("getSelections");
	if(rows.length == 0){
		$.messager.alert("信息","请先选择需要删除的数据") ;
		return ;
	}else{
		$.messager.confirm('提示', '会删除该类下的所有子类数据,请谨慎选择。您是否要删除选中数据？',
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
 *  验证的方法
 */
function yanzheng(){
	$("#add_modify_productCat_catCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/yanZhengCatCode.json','catCode']"
	});
}