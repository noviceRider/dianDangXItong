$(function(){
	//加载数据网格
	$("#brandManagementTable").datagrid(Common.createDatagridOptionsParams(basePath, "/brandManagementSearch.json",135,null));
})

/**
* 搜索
*/
function queryBrandManagement(){
	//序列化模糊条件搜索
	var  params = $("#brandManagementSearch").serializeObject();
	//搜索
	$("#brandManagementTable").datagrid(Common.createDatagridOptionsParams(basePath, "/brandManagementSearch.json",135,params));
}

/**
* 打开新增品牌界面
*/
function addBrandManagement(){
	//调用品牌序号验证方法
	brandManagementValidation();
	//调用品牌名称验证方法
	addBrandNameVerification();
	//调用加载新增品牌界面中的待选商品大类的数据的方法
	loadProductCategories();
	$("#brandManagementContainer").dialog('open');
}

/**
 * 新增品牌界面中的待选商品大类搜索
 * @returns
 */
function searchProductCategory(){
	var keyword = $("#goodsToBeSelected").textbox("getValue") ;
	console.log(keyword);
	$("#left option").attr("selected",false);
	$("#left option").css("background","");
	for(var i = 0 ; i <$("#left option").length ; i++){
		if($("#left option")[i].text.indexOf(keyword) != -1){
			$("#left option").eq(i).attr("selected",true);
			$("#left option").eq(i).css("background","blue");
		}
	}
}

/**
 * 新增品牌界面中的适用商品大类搜索
 * @returns
 */
function applicableCommodities(){
	var keyWord = $("#addBrandManagementKeyWord").textbox('getValue');
	console.log(keyWord);
	$("#right option").attr("selected",false);
	$("#right option").css("background","");
	for(var i = 0 ; i <$("#right option").length ; i++){
		if($("#right option")[i].text.indexOf(keyWord) != -1){
			$("#right option").eq(i).attr("selected",true);
			$("#right option").eq(i).css("background","blue");
		}
	}
}

/**
 * 修改品牌界面中的待选商品大类搜索
 */
function modifySearchProductCategory(){
	var keyWord = $("#modifGoodsToBeSelected").textbox('getValue');
	console.log(keyWord);
	$("#modifyLeft option").attr("selected",false);
	$("#modifyLeft option").css("background","");
	for(var i = 0 ; i <$("#modifyLeft option").length ; i++){
		if($("#modifyLeft option")[i].text.indexOf(keyWord) != -1){
			$("#modifyLeft option").eq(i).attr("selected",true);
			$("#modifyLeft option").eq(i).css("background","blue");
		}
	}
}

/**
 * 修改品牌界面中的适用商品大类搜索
 */
function modifySearchApplicableCommodities(){
	var keyWord = $("#modifBrandManagementKeyWord").textbox('getValue');
	console.log(keyWord);
	$("#modifyRight option").attr("selected",false);
	$("#modifyRight option").css("background","");
	for(var i = 0 ; i <$("#modifyRight option").length ; i++){
		if($("#modifyRight option")[i].text.indexOf(keyWord) != -1){
			$("#modifyRight option").eq(i).attr("selected",true);
			$("#modifyRight option").eq(i).css("background","blue");
		}
	}
}

/**
* 加载新增品牌界面中的待选商品大类的数据
*/
function loadProductCategories(){
	$.ajax({
		url:basePath+"/loadProductCategories.json",
		type:"post",
		dataType:"json",
		success:function(data){
			var result = data.rows;
			for(var i = 0; i < data.rows.length; i++){
				$("#left").append('<option value='+result[i].catCode+'>' +result[i].catName+ '</option>')
			}
		}
	})
}

/**
 * 加载修改品牌界面中的待选商品大类的数据
 */
function modifyLoadProductCategories(brandCode){
	$.ajax({
		url:basePath+"/modifyLoadProductCategories.json",
		type:"post",
		dataType:"json",
		data:{"brandCode":brandCode},
		success:function(data){
			var result = data.rows;
			for(var i = 0; i < data.rows.length; i++){
				$("#modifyLeft").append('<option value='+result[i].catCode+'>' +result[i].catName+ '</option>')
			}
		}
	})
}

/**
 * 加载修改品牌界面中的适用商品大类的数据
 */
function modifyApplicableCommodities(brandCode){
	$.ajax({
		url:basePath+"/modifyApplicableCommodities.json",
		type:"post",
		dataType:"json",
		data:{"brandCode":brandCode},
		success:function(data){
			var result = data.rows;
				for(var i = 0; i < data.rows.length; i++){
					if(result[i].catName != null){
						$("#modifyRight").append('<option value='+result[i].catCode+'>' +result[i].catName+ '</option>')
					}else{
						
					}
				}
		}
	})
}


/**
* 新增界面待选商品大类单个右移
*/
function moveRight(){
	$(".left option:selected").appendTo(".right");
	//取消右移后的被选中状态
	$("option:selected").prop("selected",false);
	$("#right option").css("background","");
}

/**
* 新增界面待选商品大类全部右移
*/
function moveAllRight(){
	//$(".left option").appendTo(".right");
	$("#right").append($("#left").children());
	$("#right option").css("background","");
}

/**
* 新增界面适用商品大类单个左移
*/
function moveLeft(){
	$(".right option:selected").appendTo(".left");
	//取消右移后的被选中状态
	$("option:selected").prop("selected",false);
	$("#left option").css("background","");
}

/**
* 新增界面适用商品大类全部左移
*/
function moveAllLeft(){	
	//$(".right option").append(".left");
	$("#left").append($("#right").children());
	$("#left option").css("background","");
}

/**
* 新增品牌界面的保存按钮
*/
function brandManagementSave(){
	//创建一个数组来装适用商品大类的值
	var arr = [];
	var option = $("#right option");
	for(var i = 0;i < option.length;i++){
		arr[i] = option[i].value;
	}
	//console.log(arr);
	var brandCode = $("#addBrandCode").textbox('getValue');
	var verify = $("#brandManagementForm").form('validate');
	if(verify){
		$("#brandManagementForm").form({
			url:basePath+"/brandManagementSave.json",
			success:function(data){
				if(data.rows == 0){
					$.messager.alert("警告","新增失败！");
					return;
				}else{
					$.messager.alert("信息","新增成功！");
				}
				//刷新页面
				$("#brandManagementTable").datagrid(Common.createDatagridOptionsParams(basePath, "/brandManagementSearch.json",135,null));	
				//清空新增界面
				$("#brandManagementForm").form("reset");
				/*
				 * 清空两个商品大类下拉框
				 */
				document.getElementById("left").options.length = 0;  
				document.getElementById("right").options.length = 0; 
			}
		})
		//提交新增品牌表单
		$("#brandManagementForm").submit();
		//提交适用商品大类信息和品牌编号
		$.ajax({
			url:basePath+"/brandClassification.json",
			data:{"arr":arr,"brandCode":brandCode},
			dataType:"json",
			type:"post",
			success:function(data){
				
			}	
		})
		//关闭新增品牌界面
		$("#brandManagementContainer").dialog('close');	
	}	
}

/**
* 新增品牌界面的关闭按钮
*/
function brandManagementClose(){
	$("#brandManagementContainer").dialog('close');
	//清空新增界面
	$("#brandManagementForm").form("reset");
	/*
	 * 清空两个商品大类下拉框
	 */
	document.getElementById("left").options.length = 0;  
	document.getElementById("right").options.length = 0;  
}

/**
* 打开修改品牌界面
*/
function modifyBrandManagement(){
	//获取选中行的信息
	var params = $("#brandManagementTable").datagrid('getSelections');
	//console.log(params[0].brandCode);
	//调用加载修改品牌界面中的适用商品大类的数据的方法
	var brandCode = params[0].brandCode;
	modifyApplicableCommodities(brandCode);
	//调用加载修改品牌界面中的待选商品大类的数据的方法
	modifyLoadProductCategories(brandCode);
	//调用品牌名称验证方法
	var brandNameOne = params[0].brandName;
	modifyBrandNameVerification(brandNameOne);
	$.ajax({
		url:basePath+"/modifyBrandManagement.json",
		data:{"brandCode":params[0].brandCode},
		type:"post",
		dataType:"json",
		success:function(data){
			//console.log(data.rows);
			/*
			 * 填坑
			 */
			$("#modifyBrandCodeShow").textbox('setValue',data.rows[0].brandCode);
			$("#modifyBrandCode").val(data.rows[0].brandCode);
			$("#modifyBrandName").textbox('setValue',data.rows[0].brandName);
			$("#modifyFLetter").textbox('setValue',data.rows[0].fLetter);
			$("#modifySortNo").textbox('setValue',data.rows[0].sortNo);
		}
	})
	$("#modifyBrandManagementContainer").dialog('open');
}

/**
* 修改界面待选商品大类单个右移
*/
function modifyMoveRight(){
	$(".modifyLeft option:selected").appendTo(".modifyRight");
	//取消右移后的被选中状态
	$("option:selected").prop("selected",false);
	$("#modifyRight option").css("background","");
}

/**
* 修改界面待选商品大类全部右移
*/
function modifyMoveAllRight(){
	$("#modifyRight").append($("#modifyLeft").children());
	$("#modifyRight option").css("background","");
}

/**
* 修改界面适用商品大类单个左移
*/
function modifyMoveLeft(){
	$(".modifyRight option:selected").appendTo(".modifyLeft");
	//取消右移后的被选中状态
	$("option:selected").prop("selected",false);
	$("#modifyLeft option").css("background","");
}

/**
* 修改界面适用商品大类全部左移
*/
function modifyMoveAllLeft(){	
	$("#modifyLeft").append($("#modifyRight").children());
	$("#modifyLeft option").css("background","");
}

/**
 * 修改品牌界面的保存按钮
 */
function modifyBrandManagementSave(){
	//创建一个数组来装适用商品大类的值
	var arr = [];
	var option = $("#modifyRight option");
	for(var i = 0;i < option.length;i++){
		arr[i] = option[i].value;
	}
	//获取要修改品牌的编号
	var brandCode = $("#modifyBrandCodeShow").textbox('getValue');
	console.log(brandCode);
	var verify = $("#modifyBrandManagementForm").form('validate');
	if(verify){
		$("#modifyBrandManagementForm").form({
			url:basePath+"/modifyBrandManagementSave.json",
			success:function(data){
				if(data.rows == 0){
					$.messager.alert("警告","修改失败！");
					return;
				}else{
					$.messager.alert("信息","修改成功！");
				}
				//提交适用商品大类的值和品牌编号
				$.ajax({
					url:basePath+"/brandClassification.json",
					data:{"arr":arr,"brandCode":brandCode},
					dataType:"json",
					type:"post",
					success:function(data){
						
					}	
				})
				//刷新页面
				$("#brandManagementTable").datagrid(Common.createDatagridOptionsParams(basePath, "/brandManagementSearch.json",135,null));	
				//清空新增界面
				$("#modifyBrandManagementForm").form("reset");
				/*
				 * 清空两个商品大类下拉框
				 */
				document.getElementById("modifyLeft").options.length = 0;  
				document.getElementById("modifyRight").options.length = 0; 
			}
		})
		//提交修改品牌表单
		$("#modifyBrandManagementForm").submit();
		
		//关闭修改品牌界面
		$("#modifyBrandManagementContainer").dialog('close');	
	}
}

/**
 * 修改界面的关闭按钮
 */
function modifyBrandManagementClose(){
	//关闭修改品牌界面
	$("#modifyBrandManagementContainer").dialog('close');
	$("#modifyBrandManagementForm").form('reset');
	/*
	 * 清空两个商品大类下拉框
	 */
	document.getElementById("modifyLeft").options.length = 0;  
	document.getElementById("modifyRight").options.length = 0; 
}

/**
* 删除
*/
function delBrandManagement(){
	//获取选中行的信息
	var params = $("#brandManagementTable").datagrid('getSelections');
	/*
	 * 创建一个数组来装被选中的品牌编号
	 */
	var arr = [];
	for(var i = 0;i < params.length;i++){
		arr[i] = params[i].brandCode;
	}
	if(params.length == 0){
		$.messager.alert('警告','请选中再删除')
	}else{
		$.post(
			basePath+"/delBrandManagement.json",
			{"arr":arr},
			function(data){
				if(data.rows != 0){
					$.messager.alert('信息','删除成功!');
					//刷新页面
					$("#brandManagementTable").datagrid(Common.createDatagridOptionsParams(basePath, "/brandManagementSearch.json",135,null));	
				}else{
					$.messager.alert('警告','删除失败!');
				}
			},
			"json"
		)
	}
}

/**
 * 禁用，启用状态
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operation(value,row,index){
	if(row.isShow =='1'){
		return"<a href='#' onClick='forbidden(event)' value="+row.isShow+" id="+row.brandCode+" style='color:blue'>禁用</a>";
	}else{
		return"<a href='#' onClick='forbidden(event)' value="+row.isShow+" id="+row.brandCode+" style='color:blue'>启用</a>";
	} 
}

/**
* 改变状态
*/
function forbidden(event){
	//获取选中数据的状态值
	var isShow = $(event.target).attr("value");
	//获取选中数据的会员序号
	var brandCode = $(event.target).attr("id");
	var rows = $("#brandManagementTable").datagrid("getData").rows ;
	var index  ;
	for(var i = 0 ; i < rows.length ; i++){
		if(rows[i].brandCode == brandCode){
			index = i ;
		}
	}
	console.log(rows);
	console.log(index);
	if(isShow == '1'){
		isShow = '0';
	}else{
		isShow = '1';
	}
	$.ajax({
		url:basePath+"/forbiddenBrandManagement.json",
		type:"post",
		dataType:"json",
		data:{"isShow":isShow,"brandCode":brandCode},
		success:function(data){
			$('#brandManagementTable').datagrid('updateRow',{
				index: index,
				row: {
					isShow:isShow
				}
			});
		}
	})
}

/**
* 新增品牌编号非重复验证
*/
function brandManagementValidation(){
	$("#addBrandCode").textbox({
		required:true,
		missingMessage:"序号号不能为空",
		invalidMessage:"序号已存在",
		validType:"remoteValid['"+basePath+"/brandManagementValidation.json','brandCode']"
	});
}

/**
* 格式化数据显示
*/
function showOrNot(value,row,index){
	if(value == '1'){
		return "<img src='../../../css/themes/icons/ok.png'/>";
	}else{
		return "—";
	}
}

/**
* 修改界面品牌名称验证
*/
function modifyBrandNameVerification(brandNameOne){
	console.log(brandNameOne);
	$("#modifyBrandName").textbox({
		required:true,
		missingMessage:"品牌名称不能为空",
		invalidMessage:"品牌名称已存在",
		validType:"remoteValid['"+basePath+"/modifyBrandNameVerification.json','brandName',{'keyWord':'"+brandNameOne+"'}]"
	});
}

/**
* 增加界面品牌名称验证
*/
function addBrandNameVerification(){
	$("#addBrandName").textbox({
		required:true,
		missingMessage:"序号号不能为空",
		invalidMessage:"序号已存在",
		validType:"remoteValid['"+basePath+"/addBrandNameVerification.json','brandName']"
	});
}