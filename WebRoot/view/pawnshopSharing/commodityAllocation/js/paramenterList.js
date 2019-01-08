/**
 * <p>title:广沣共享典当管理系统-paramenterList.js</p>
 * 
 * <p>Description:商品属性的js</p>
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
	
	//调用初始化加载数据方法
	loading();
	
	//初始化load面板
	$("#add_modify_conf_Container").load("add_modify_conf_Container.html",null,function(){
		//初始化新增窗口
		$("#add_modify_conf_Panel").dialog({
			buttons:'#saveAndCancel',
			onClose:cancelConf ,
			modal:true ,
			width:360 ,
			closed:true
		});
		
		$("#add_modify_conf_attrCode").textbox({
			required:true,
			missingMessage:"属性编号不能为空",
			width:200
		})
		
		$("#add_modify_conf_attrName").textbox({
			required:true,
			missingMessage:"属性名称不能为空",
			width:200
		})
		
		$('input[type=radio][name=attrType]').change(function(){
			if(this.value == "01"){
				$("#authenticate tr").remove();
				/*$("#authenticate").append("<tr><td><input type='text' name='options'/></td>");
				$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});*/
			}else{
				$("#authenticate tr").remove();
				$("#authenticate").append("<tr><td><a id='addTrNodes'></a></td>");
				$("#addTrNodes").before("<tr><td><input type='text' name='options'/></td>");
				$("#addTrNodes").before("<tr><td><input type='text' name='options'/></td>");
				$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});
				$("#addTrNodes").linkbutton({
					 text:"新增",
					    onClick:function(){
					    	//动态的添加节点
					    	$("#addTrNodes").before("<tr><td><input type='text' name='options'/></td>" +
					    		"<td>&nbsp;&nbsp;<a name='linkBtn'>删除</a></td></tr>")
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
					    		$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});
							}
						});
			}
		})
		$("#saveConf").linkbutton({
			text:"确定",
			iconCls:'icon-save',
			onClick:function(){
				//调用确认方法
				saveConf();
			}
		})
		$("#cancelConf").linkbutton({
			text:"取消",
			iconCls:'icon-clear',
			onClick:function(){
				//调用取消方法
				cancelConf();
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
 * 初始化加载数据
 * @returns
 */
function loading(){
	$("#confTable").datagrid(Common.createDatagridOptionsParams(basePath, "/confTable.json", 135,{"groupCode":oneValues()}));
}

/**
 * 设置显示属性类型的方法
 */
function showAttrType(value){
	if (value == "01") {
		return "唯一属性";
	}else if(value == "02"){
		return "单选属性";
	}else{
		return "多选属性";
	}
}

/**
 * 点击返回按钮的方法
 * 返回商品属性组页面
 */
function backGroup(){
	window.location.href="commodityAttribute.html" ;
}

/**
 * 点击查询按钮的方法
 * @returns
 */
function queryConf(){
	var keyWord = $("#keyWord").textbox("getValue");
	//重新加载表格数据
	$("#confTable").datagrid("reload",{"groupCode":oneValues(),"keyWord":keyWord});
}

/**
 * 点击增加按钮的方法
 */
function addConf(){
	flag = true ;
	$("#attrCode").text("分类编号"+oneValues() +".");
	$("#add_modify_conf_attrCode").textbox("enable");
	$("#add_modify_conf_attrCode").textbox({onChange:yanzheng()});
	$("input:radio").eq(0).prop("checked",true);
/*	$("#authenticate").append("<tr><td><input type='text' name='options'/></td>");
	$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});*/
	$("#add_modify_conf_Panel").dialog({title:"新增",closed:false})
}

/**
 * 点击确认方法
 */
function saveConf(){
	var formData = $("#add_modify_conf_Form").serializeObject();
	var options = '';
	if(JSON.stringify(formData) != "{}" && JSON.stringify(formData) !='{"options":""}'){
		if($("input[type='radio']:checked").val() == "01"){
			options += formData.options ;
		}else{
			for (var i = 0; i < formData.options.length; i++) {
				if(i == formData.options.length -1){
					options += formData.options[i] ;
				}else{
					options += formData.options[i] + ',' ;
				}
			}
		}
	}else{
		$.messager.alert("信息","请添加可选值列表") ;
		return ;
	}
	var attrCode = oneValues() + "." + $("#add_modify_conf_attrCode").textbox("getValue");
	var attrCode1 = $("#add_modify_conf_attrCode").textbox("getValue");
	var attrName = $("#add_modify_conf_attrName").textbox("getValue");
	var attrType = $("input[type='radio']:checked").val();
	var obj = {"attrCode":attrCode,"attrName":attrName,"attrType":attrType,"options":options,"groupCode":oneValues()};
	var obj1 = {"attrCode":attrCode1,"attrName":attrName,"attrType":attrType,"options":options}
	
	if(attrCode1 == '' || attrCode1 == null){
		$.messager.alert("提示","编号不能为空");
		return ;
	}else if(attrName == '' || attrName == null){
		$.messager.alert("提示","名称不能为空");
		return ;
	}else if(attrType == '' || attrType == null){
		$.messager.alert("提示","请选择属性类型");
		return ;
	}else{
		if($("#add_modify_conf_Form").form("validate")){
			if(flag){
				//发送新增请求
				$.ajax({
					url:basePath+'/addConf.json',
					type:'post',
					data:obj ,
					dataType:'json',
					success:function(data){
						if(data.rows > 0){
							//调用取消
							cancelConf() ;
							//刷新页面
							loading();
							//提示
							$.messager.alert("信息","新增成功") ;
						}
					}
				})
			 }else{
				 //发送修改请求
				 $.ajax({
						url:basePath+'/updConf.json',
						type:'post',
						data:obj1 ,
						dataType:'json',
						success:function(data){
							if(data.rows > 0){
								//调用取消方法
								cancelConf() ;
								//刷新页面
								loading();
								//提示
								$.messager.alert("信息","修改成功") ;
							}
					}
				})
			}
		}
	}
 }
	 

/**
 * 点击取消的方法
 */
function cancelConf(){
	$("#add_modify_conf_attrCode").textbox("setValue","") ;
	$("#add_modify_conf_attrName").textbox("setValue","") ;
	$("input:radio").eq(0).prop("checked",true);
	$("#add_modify_conf_Panel").dialog({closed:true}) ;
	$("#authenticate tr").remove();
}

/**
 *  编号验证
 */
function yanzheng(){
	var attrCode = oneValues() + "." + $("#add_modify_conf_attrCode").textbox("getValue") ;
	$("#add_modify_conf_attrCode").textbox({
		required:true,
		missingMessage:"编号不能为空",
		invalidMessage:"编号已存在",
		validType:"remoteValid['"+basePath+"/yanzhengConfByAttrCode.json','attrCode', " + 
		"{logicId:'09.03.01', groupCode:'" + oneValues() + ".'}]"
	});
}

/**
 * 点击修改按钮的方法
 */
function updConf(){
	//获取表格中选中的数据
	var rows = $("#confTable").datagrid("getSelections");
	//如果选择了多条
	if(rows.length >1){
		$.messager.alert("信息","每次只能选择一条数据修改") ;
		//如果一条都没选
	}else if(rows.length == 0){
		$.messager.alert("信息","请选择一条数据进行修改") ;
		//如果只选择了一条
	}else{
		$("#attrCode").text("分类编号");
		//设置公共变量为false
		flag = false ;
		$("#add_modify_conf_attrCode").textbox("disable") ;
		var attrCode = $("#confTable").datagrid("getSelected").attrCode ;
		$.ajax({
			url:basePath+'/getConfByAttrCode.json',
			type:'post',
			data:{"attrCode":attrCode} ,
			dataType:'json',
			success:function(data){
				$("#add_modify_conf_attrCode").textbox("setValue",data.rows.attrCode) ;
				$("#add_modify_conf_attrName").textbox("setValue",data.rows.attrName) ;
				$("input:radio[value='"+data.rows.attrType+"']").prop("checked",true) ;
				if(data.rows.options != null){
					if(data.rows.attrType == "01"){
						$("#authenticate tr").remove();
						$("#authenticate").append("<tr><td><input type='text' id='options1' name='options'/></td>");
						$("#options1").textbox({width:200,required:true,missingMessage:'请输入属性值'});
						$("#options1").textbox("setValue",data.rows.options);
					}else{
						$("#authenticate tr").remove();
						var options = data.rows.options.split(",") ;
						$("#authenticate").append("<tr><td><a id='addTrNodes'></a></td>");
						$("#addTrNodes").linkbutton({
							 text:"新增",
							    onClick:function(){
							    	//动态的添加节点
							    	$("#addTrNodes").before("<tr><td><input type='text' name='options'/></td>" +
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
							    		$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});
									}
								});
						for(var i = 0 ; i < options.length ;i++){
					    	$("#addTrNodes").before("<tr><td><input type='text' name='options' id='options"+i+"'/></td>" +
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
					    		$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});
					    	//给输入框赋值
			    			$("#options"+i).textbox("setValue",options[i]) ;
						}
					}
				}else{
					$("#authenticate tr").remove();
					$("#authenticate").append("<tr><td><input type='text' name='options'/></td>");
					$("input[name='options']").textbox({width:200,required:true,missingMessage:'请输入属性值'});
				}
			}
		})
		$("#add_modify_conf_Panel").dialog({closed:false}) ;
	}
}

/**
 * 点击删除按钮的方法
 */
function delConf(){
	//获取表格中选中的数据
	var rows = $("#confTable").datagrid("getSelections");
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
					var attrCode = "";
					for (var i = 0; i < rows.length; i++) {
						if(i == rows.length - 1){
							attrCode += rows[i].attrCode ;
						}else{
							attrCode += rows[i].attrCode + "," ;
						}
					}
					//根据编号删除属性组
					$.ajax({
						url:basePath+"/delConf.json",
						type:"post",
						data:{"attrCode":attrCode},
						dataType:"json",
						success:function(data){
							if(data.rows > 0){
								//刷新页面
								loading();
								//提示
								$.messager.alert("信息","删除成功") ;
							}
						}
					});
				}
		})
	}
}
