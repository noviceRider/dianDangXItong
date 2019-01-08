//图片描述
var imgChoose = [];
//图片可选，必选
var choosable = [];
//修改图片的描述
var modifyImgChoose = [];
//验证
var yesOrNo = true;
/**
* 初始化页面
*/
$(function(){
	$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
	
	//初始化新增商品信息界面
	$("#addGoodsContainer").load("addListOfCommodities.html",null,function(){
		$("#addGoodsContainerPanel").dialog({
			title:"商品信息",
			closed:true,
			width:350,
			height:450
		});
		
		$("#addGoodsId").textbox({width:200,required:true});
		$("#addShopCode").combobox({width:200,required:true});
		$("#addInputDate").datebox({width:200,required:true,editable:false});
		$("#addCatCode").combobox({width:200,required:true,onChange:function(){
			//获取分类一级下拉框的值
			var value = $("#addCatCode").combobox('getValue');
			//调用分类二级下拉框方法
			classificationOfTheSecondary(value);
			//调用商品品牌分类下拉框方法
			commodityBrand(value);
			//调用查询商品属性以及自动添加节点方法
			formattingProperties(value);
			//调用商品图片方法
			commodityImages(value);
		}});
		$("#addSubCatCode").combobox({width:200,required:true,onChange:function(){
			//获取分类二级下拉框的值
			var value = $("#addSubCatCode").combobox('getValue');
			//调用分类三级下拉框方法
			classificationOfThree(value);
		}});
		$("#addDetailCatCode").combobox({width:200,required:true});
		$("#addGoodsName").textbox({width:200,required:true});
		$("#addBrandCode").combobox({width:200,required:true});
		$("#addFirstPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#addGoodsDesc").textbox({width:200,height:60,multiline:true,required:true});
	
		//商品信息中的下一页按钮
		$("#addGoodsNextPageBtn").linkbutton({
			text:"下一页",
			onClick:function(){
				//判断商品表单验证返回的是true还是false
				var validate = $("#addGoodsForm").form("validate");
				if(validate){
					//关闭商品信息界面
					$("#addGoodsContainerPanel").dialog('close');
					//打开商品属性界面
					$("#addProductAttributeContainerPanel").dialog('open');
				}
			}
		});
		
		//商品信息中的取消按钮
		$("#addGoodsCloseBtn").linkbutton({
			text:"取消",
			iconCls:'icon-cancel',
			onClick:function(){
				//调用新增商品界面的取消方法
				cancel();
			}
		});
		
		//初始化新增商品属性界面
		$("#addProductAttributeContainerPanel").dialog({
			title:"商品属性",
			closed:true,
			width:350,
			height:400
		});
		
		//商品属性中的上一页按钮
		$("#addProductAttributePreviousPageBtn").linkbutton({
			text:"上一页",
			onClick:function(){
				//关闭商品属性界面
				$("#addProductAttributeContainerPanel").dialog('close');
				//打开商品信息界面
				$("#addGoodsContainerPanel").dialog('open');
			}
		});
		
		shopVerify();
		//商品属性中的确定按钮
		$("#addProductAttributeNextPageBtn").linkbutton({
			text:"确定",
			onClick:function(){
				$("#addGoodsForm").form({
					url:basePath+"/addGoodsForm.json",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('警告','新增商品失败');
						}else{
							$.messager.confirm('信息','新增商品成功',function(r){
								//调用取消方法
								cancel();
								//刷新当前页面
								location.reload();
							});
							
						}
					}
				});
				var addGoodsForm = $("#addGoodsForm").serializeObject();
				//获取商品id
				var goodsId = $("#addGoodsId").textbox('getValue');
				//序列化商品单一属性和唯一属性表单
				var params = $("#addProductAttributeForm").serializeObject();
				//序列化商品多属性表单
				var multiattribute = $("#addMultiattribute").serializeObject();
				var img = $("#addImg").serializeObject();
				//创建一个数组，将多属性对象转为数组
				var multiattributeArr = [];
				for (var i in multiattribute) {
					multiattributeArr.push(multiattribute[i]); //属性
				}
				//转换成json字符串
				for(var i = 0;i < multiattributeArr.length;i++){
					multiattributeArr[i] = JSON.stringify(multiattributeArr[i]);
				}
				//创建一个数组用来装商品属性对象的键
				var keys = [];
				//创建一个数组用来装商品属性对象的值
				var values = [];
				//遍历商品单一属性和唯一属性对象的键和值,并放入数组中
				for(var key in params){
					keys.push(key);
					values.push(params[key]);
				}
				//遍历商品多属性对象的键和值,并放入数组中
				for(var key in multiattribute){
					keys.push(key);
				}
				for(var i = 0;i < multiattributeArr.length;i++){
					values.push(multiattributeArr[i]);
				}
				
				//新增商品图片
				//通过数组的长度判断是一张图片还是多张图片
				if(imgChoose.length == 1){
					//判断图片是不是要必选
					if(choosable[0] == '必选'){
						var filesObject = document.getElementById("file").files[0];
						//如果图片要必选
						if( $("#file").val() != null && $("#file").val() != ''){
							//创建formdate对象
							var form = new FormData();
							//添加文件对象
							form.append("upfile",document.getElementById("file").files[0]);
							//添加图片描述
							form.append("resDesc",imgChoose);
							//添加商品编号
							form.append("goodsId",goodsId);
							yesOrNo = true;
							//传递数据到后台
							$.ajax({
								url:basePath+"/addPicture.json",
								type:'post' ,
								dataType:'json',
								data:form ,
								async:false ,
								processData: false ,
								contentType: false 
							});
						}else{
							yesOrNo = false;
						}
					}else{
						if( $("#file").val() != null && $("#file").val() != ''){
							//创建formdate对象
							var form = new FormData();
							//添加文件对象
							form.append("upfile",$("#file").files[0]);
							//添加图片描述
							form.append("resDesc",imgChoose);
							//添加商品编号
							form.append("goodsId",goodsId);
							yesOrNo = true;
							//传递数据到后台
							$.ajax({
								url:basePath+"/addPicture.json",
								type:'post' ,
								dataType:'json',
								data:form ,
								async:false ,
								processData: false ,
								contentType: false 
							});
						}else{
							yesOrNo = true;
						}
					}
					
				}else if(imgChoose.length == 0){
					yesOrNo = true;
				}else{
					//循环传递图片到后台
					for(var a = 0;a < imgChoose.length;a++){
						//判断图片是不是要必选
						if(choosable[a] == '必选'){
							//如果图片要必选
							if( $("#file"+a+"").val() != null && $("#file"+a+"").val() != ''){
								//创建formdate对象
								var form = new FormData();
								//添加文件对象
								form.append("upfile",document.getElementById("file"+a+"").files[0]);
								//添加图片描述
								form.append("resDesc",imgChoose);
								//添加商品编号
								form.append("goodsId",goodsId);
								yesOrNo = true;
								//传递数据到后台
								if(yesOrNo){
									$.ajax({
										url:basePath+"/addPicture.json",
										type:'post' ,
										dataType:'json',
										data:form ,
										async:false ,
										processData: false ,
										contentType: false 
									});
								}
								
							}else{
								yesOrNo = false;
							}
						}else{
							if( $("#file"+a+"").val() != null && $("#file"+a+"").val() != ''){
								//创建formdate对象
								var form = new FormData();
								//添加文件对象
								form.append("upfile",document.getElementById("file"+a+"").files[0]);
								//添加图片描述
								form.append("resDesc",imgChoose);
								//添加商品编号
								form.append("goodsId",goodsId);
								//传递数据到后台
								$.ajax({
									url:basePath+"/addPicture.json",
									type:'post' ,
									dataType:'json',
									data:form ,
									async:false ,
									processData: false ,
									contentType: false 
								});
							}
						}
					}
				}
				
				//提交商品信息表单
				if(yesOrNo){	//通过验证判断能否提交
					$("#addGoodsForm").submit();
				}else{
					$.messager.confirm('警告','必选图片没有选择图片！请重新新增商品',function(r){
						//调用新增商品界面的取消方法
						cancel();
						//刷新当前网页
						location.reload();
					});
					
				}
				
				//新增商品属性
				if(yesOrNo){	//通过验证能否提交
					$.ajax({
						url:basePath+"/addProductAttributeForm.json",
						type:"post",
						data:{"attrCode":keys,"goodsId":goodsId,"attrValue":values},
						dataType:"json",
						success:function(data){
							if(data.rows == 0){
								$.messager.alert('警告','新增商品属性失败');
							}else{
								//刷新页面
								$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
							}
						}
					});
				}
			}
		});
	
		//商品属性中的取消按钮
		$("#addProductAttributeCloseBtn").linkbutton({
			text:"取消",
			iconCls:'icon-cancel',
			onClick:function(){
				//调用新增商品界面的取消方法
				cancel();
			}
		});
	});
	
	//初始化鉴定界面
	$("#authenticateContainer").load("authenticate.html",null,function(){
		//初始化鉴定商品信息界面
		$("#authenticatePanel").dialog({
			title:"商品信息",
			closed:true,
			width:350,
			height:450,
			buttons:'#sc_authenticate',
			modal:true
		});
		//初始化鉴定商品信息的格式
		$("#authenticategoodsId").textbox({width:200,disabled:true});
		$("#authenticateshopCode").textbox({width:200,disabled:true});
		$("#authenticateinputDate").textbox({width:200,disabled:true});
		$("#authenticatecatCode").textbox({width:200,disabled:true});
		$("#authenticatesubCatCode").textbox({width:200,disabled:true});
		$("#authenticatedetailCatCode").textbox({width:200,disabled:true});
		$("#authenticategoodsName").textbox({width:200,disabled:true});
		$("#authenticatebrandCode").textbox({width:200,disabled:true});
		$("#authenticatefirstPrice").textbox({width:200,disabled:true});
		$("#authenticategoodsDesc").textbox({width:200,disabled:true,height:60,multiline:true});
	
		//初始化鉴定界面商品信息页的下一页按钮
		$("#authenticateNextPageBtn").linkbutton({
			text:"下一页",
			onClick:function(){
				//关闭鉴定商品信息页
				$("#authenticatePanel").dialog('close');
				//打开鉴定商品属性页
				$("#authenticateProductAttributeContainerPanel").dialog('open');
			}
		});
		
		//初始化商品属性鉴定页
		$("#authenticateProductAttributeContainerPanel").dialog({
			title:"商品属性",
			closed:true,
			width:350,
			height:280,
			buttons:'#sc_authenticateAttribute',
			modal:true,
		});
		
		//初始化商品属性鉴定页的上一页按钮
		$("#authenticateAttributeBackBtn").linkbutton({
			text:"上一页",
			onClick:function(){
				//关闭鉴定商品属性页
				$("#authenticatePanel").dialog('close');
				//打开鉴定商品信息页
				$("#authenticatePanel").dialog('open');
			}
		});
		
		//初始化商品属性鉴定页的下一页按钮
		$("#authenticateAttributeNextPageBtn").linkbutton({
			text:"下一页",
			onClick:function(){
				//关闭鉴定商品属性页
				$("#authenticateProductAttributeContainerPanel").dialog('close');
				//打开鉴定商品鉴定页
				$("#goodsAuthenticatePanel").dialog('open');
			}
		});
	
		//初始化商品鉴定页
		$("#goodsAuthenticatePanel").dialog({
			title:"商品鉴定",
			closed:true,
			width:400,
			height:220,
			buttons:'#sc_goodsAuthenticate',
			modal:true,
			
		});
		
		//初始化商品鉴定页的格式
		$("#authenticateGoodsQuality").combobox({width:240,panelHeight:true,required:true,editable:false});
		$("#authenticateIdentifyDesc").textbox({width:240,height:60,multiline:true,required:true});
		
		//初始化商品鉴定页的上一页按钮
		$("#goodsAuthenticateBackBtn").linkbutton({
			text:"上一页",
			onClick:function(){
				//关闭商品鉴定页
				$("#goodsAuthenticatePanel").dialog('close');
				//打开商品属性页
				$("#authenticateProductAttributeContainerPanel").dialog('open');
			}
		});
		
		//初始化商品鉴定页的历史鉴定按钮
		$("#historicalAppraisalBtn").linkbutton({
			text:"历史鉴定",
			width:240,
			onClick:function(){
				//获取商品id
				var goodsId = $("#authenticategoodsIdHide").val();
				//查询商品的鉴定记录
				$.ajax({
					url:basePath+"/historicalAppraisal.json",
					data:{"goodsId":goodsId},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('信息','此商品未鉴定');
						}else{
							//清空历史鉴定页面
							$("#historicalRecordTable").find("tr").remove();
							$("#historicalRecordTable").append("<tr><th style='text-align: center'><font color='balck'>序号</font></th>"
									+ "<th style='text-align: center'><font color='balck'>真假</font></th>"
									+ "<th style='text-align: center'><font color='balck'>新旧程度</font></th>"
									+ "<th style='text-align: center'><font color='balck'>鉴定人</font></th>"
									+ "<th style='text-align: center'><font color='balck'>鉴定时间</font></th>"
									+ "<th style='text-align: center'><font color='balck'>备注</font></th></tr>");
							//动态添加节点
							for(var i = 0;i < data.rows.length;i++){
								$("#historicalRecordTable").append("<tr><th style='text-align: center'><font color='balck'>"+data.rows[i].identifyId+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].identifyResult+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].goodsQuality+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].createBy+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].createTime+"</font></th>" 
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].identifyDesc+"</font></th></tr>");
							}
							//打开鉴定历史页面
							$("#historicalRecordPanel").dialog('open');
						}	
					}
				})	
			}
		});
		
		//初始化商品鉴定页的鉴定为正品按钮
		$("#qualityGoodsBtn").linkbutton({
			text:"正品",
			onClick:function(){
				//获取商品id
				var goodsId = $("#authenticategoodsIdHide").val();
				//获取新旧程度
				var level = $("#authenticateGoodsQuality").textbox('getValue');
				//获取备注
				var remarks = $("#authenticateIdentifyDesc").textbox('getValue');
				//发送获取的数据到后台
				$.ajax({
					url:basePath+"/qualityGoods.json",
					data:{"goodsId":goodsId,"goodsQuality":level,"identifyDesc":remarks},
					dataType:"json",
					typr:"post",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('警告','鉴定失败');
						}else{
							$.messager.alert('信息','鉴定成功');
							//关闭商品鉴定页
							$("#goodsAuthenticatePanel").dialog('close');
							//清空商品属性页
							$("#authenticateProductAttributeTable").find("tr").remove();
							//清空商品鉴定页
							$("#goodsAuthenticateForm").form('reset');
							//刷新页面
							$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
						}
					}
				})
			}
		});
		
		//初始化商品鉴定页的鉴定为假货按钮
		$("#fakeBtn").linkbutton({
			text:"假货",
			onClick:function(){
				//获取商品id
				var goodsId = $("#authenticategoodsIdHide").val();
				//获取新旧程度
				var level = $("#authenticateGoodsQuality").textbox('getValue');
				//获取备注
				var remarks = $("#authenticateIdentifyDesc").textbox('getValue');
				//发送获取的数据到后台
				$.ajax({
					url:basePath+"/fake.json",
					data:{"goodsId":goodsId,"goodsQuality":level,"identifyDesc":remarks},
					dataType:"json",
					typr:"post",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('警告','鉴定失败');
						}else{
							$.messager.alert('信息','鉴定成功');
							//关闭商品鉴定页
							$("#goodsAuthenticatePanel").dialog('close');
							//清空商品属性页
							$("#authenticateProductAttributeTable").find("tr").remove();
							//清空商品鉴定页
							$("#goodsAuthenticateForm").form('reset');
							//刷新页面
							$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
						}
					}
				})
			}
		});
		
		//初始化商品鉴定页的资料不全按钮
		$("#insufficientInformationBtn").linkbutton({
			text:"资料不全",
			onClick:function(){
				//获取商品id
				var goodsId = $("#authenticategoodsIdHide").val();
				//获取新旧程度
				var level = $("#authenticateGoodsQuality").textbox('getValue');
				//获取备注
				var remarks = $("#authenticateIdentifyDesc").textbox('getValue');
				//发送获取的数据到后台
				$.ajax({
					url:basePath+"/insufficientInformation.json",
					data:{"goodsId":goodsId,"goodsQuality":level,"identifyDesc":remarks},
					dataType:"json",
					typr:"post",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('警告','鉴定失败');
						}else{
							$.messager.alert('信息','鉴定成功');
							//关闭商品鉴定页
							$("#goodsAuthenticatePanel").dialog('close');
							//清空商品属性页
							$("#authenticateProductAttributeTable").find("tr").remove();
							//清空商品鉴定页
							$("#goodsAuthenticateForm").form('reset');
							//刷新页面
							$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
						}
					}
				});
			}
		});
		
		//初始化商品鉴定页的取消按钮
		$("#insufficientInformationCancelBtn").linkbutton({
			text:"取消",
			onClick:function(){
				//关闭商品鉴定页
				$("#goodsAuthenticatePanel").dialog('close');
				//清空商品属性页
				$("#authenticateProductAttributeTable").find("tr").remove();
				//清空商品鉴定页
				$("#goodsAuthenticateForm").form('reset');
			}
		});
		
		
		//初始化鉴定历史页面
		$("#historicalRecordPanel").dialog({
			title:"历史鉴定",
			closed:true,
			width:700,
			height:200,
			modal:true
		})
		
		//初始化评估界面
		$("#goodsAssessmentPanel").dialog({
			title:"商品估价",
			closed:true,
			width:350,
			height:340,
			buttons:'#sc_assessment',
			modal:true
		});
		
		//把评估界面格式化为easyui格式
		$("#assessmentOfficialPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#assessmentValuationPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#assessmentPawnPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#assessmentRchasePrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#assessmentSellingPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#assessmentRentalPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#assessmentAppraiseDesc").textbox({width:200,required:true,height:60,multiline:true});
		
		//初始化商品估价页的上一页按钮
		$("#assessmentBackBtn").linkbutton({
			text:"上一页",
			onClick:function(){
				//关闭商品估价页
				$("#goodsAssessmentPanel").dialog('close');
				//打开评估界面的商品鉴定页
				$("#inAssessingPanel").dialog('open');
			}
		});
		
		//初始化商品估价页的评估记录按钮
		$("#assessmentRecordsBtn").linkbutton({
			text:"评估记录",
			onClick:function(){
				//获取商品ID
				var goodsId = $("#inAssessingGoodsIdHide").val();
				//后台查询估价历史记录
				$.ajax({
					url:basePath+"/assessmentRecords.json",
					data:{"goodsId":goodsId},
					dataType:"json",
					type:"post",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('信息','此商品未估价');
						}else{
							//清空历史估价页面
							$("#theHistoricalValueTable").find("tr").remove();
							$("#theHistoricalValueTable").append("<tr><th style='text-align: center'><font color='balck'>序号</font></th>"
									+ "<th style='text-align: center'><font color='balck'>官方价</font></th>" 
									+ "<th style='text-align: center'><font color='balck'>评估价</font></th>"
									+ "<th style='text-align: center'><font color='balck'>评估人</font></th>"
									+ "<th style='text-align: center'><font color='balck'>评估时间</font></th>"
									+ "<th style='text-align: center'><font color='balck'>备注</font></th>")
							//动态添加节点
							for(var i = 0;i < data.rows.length;i++){
								$("#theHistoricalValueTable").append("<tr><th style='text-align: center'><font color='balck'>"+data.rows[i].appraiseId+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].officialPrice+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].valuationPrice+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].createBy+"</font></th>"
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].createTime+"</font></th>" 
										+ "<th style='text-align: center'><font color='balck'>"+data.rows[i].appraiseDesc+"</font></th></tr>");
							}
							//打开商品评估页面
							$("#theHistoricalValuePanel").dialog('open');
						}	
					}
				});
			}
		});
		
		//初始化商品估价页的保存按钮
		$("#assessmentSave").linkbutton({
			text:"保存",
			onClick:function(){
				//获取商品Id
				var goodsId = $("#inAssessingGoodsIdHide").val();
				//初始化商品估价页的form表单
				$("#goodsAssessmentForm").form({
					url:basePath+"/goodsAssessmentForm.json",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('警告','评估失败');
						}else{
							$.messager.alert('信息','评估成功');
							//关闭页面
							$("#goodsAssessmentPanel").dialog('close');
							//清空表单
							$("#goodsAssessmentForm").form('reset');
							//刷新页面
							$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
						}
					}
				});
				//提交表单
				$("#goodsAssessmentForm").submit();
			}
		});
		
		//初始化商品估价页的取消按钮
		$("#assessmentCancel").linkbutton({
			text:"取消",
			onClick:function(){
				//关闭商品估价页
				$("#goodsAssessmentPanel").dialog('close');
				//清空商品估价页
				$("#goodsAssessmentForm").form('reset');
				//清空商品估价界面的商品鉴定页
				$("#inAssessingForm").form('reset');
			}
		});
		
		//初始化评估界面的商品鉴定页
		$("#inAssessingPanel").dialog({
			title:"商品鉴定",
			closed:true,
			width:350,
			height:300,
			buttons:'#sc_inAssessing',
			modal:true
		});
		
		//把评估界面的商品鉴定页初始化为easyui格式
		$("#inAssessingGoodsId").textbox({width:200,disabled:true});
		$("#inAssessingGoodsQuality").textbox({width:200,disabled:true});
		$("#inAssessingIdentifyResult").textbox({width:200,disabled:true});
		$("#inAssessingCreateBy").textbox({width:200,disabled:true});
		$("#inAssessingCreateTime").textbox({width:200,disabled:true});
		$("#inAssessingIdentifyDesc").textbox({width:200,disabled:true,height:60,multiline:true});
		
		//初始化评估界面的商品鉴定页的下一页按钮
		$("#inAssessingNextBtn").linkbutton({
			text:"下一页",
			onClick:function(){
				//关闭评估界面的商品鉴定页
				$("#inAssessingPanel").dialog('close');
				//打开商品估价页面
				$("#goodsAssessmentPanel").dialog('open');
			}
		});
		
		//初始化商品估价历史记录页面
		$("#theHistoricalValuePanel").dialog({
			title:"历史估价记录",
			closed:true,
			width:700,
			height:200,
			modal:true
		});
	
	});
	
	//商品修改
	$("#modifyGoodsContainer").load("modifyGoods.html",null,function(){
		//初始化修改商品信息页
		$("#modifyGoodsContainerPanel").dialog({
			title:"修改商品信息",
			closed:true,
			width:350,
			height:450,
			buttons:'#sc_modifyGoods',
			modal:true
		});
		
		//初始化为easyui格式
		$("#modifygoodsId").textbox({width:200,disabled:true});
		$("#modifyshopCode").combobox({width:200,required:true});
		$("#modifyinputDate").datebox({width:200,required:true,editable:false});
		$("#modifycatCode").combobox({width:200,required:true,onSelect:function(){
			$("#modifysubCatCode").combobox('reset');
			//获取分类一级下拉框的值
			var value = $("#modifycatCode").combobox('getValue');
			//调用修改分类二级下拉列表
			modifyClassificationOfTheSecondaryOne(value);
			//修改商品品牌下拉列表
			modifyCommodityBrandOne(value);
		}});
		$("#modifysubCatCode").combobox({width:200,required:true,onSelect:function(){
			//获取分类二级下拉框的值
			var value = $("#modifysubCatCode").combobox('getValue');
			//调用修改分类三级下拉列表
			modifyClassificationOfThreeOne(value);
			
		}});
		$("#modifydetailCatCode").combobox({width:200,required:true});
		$("#modifygoodsName").textbox({width:200,required:true});
		$("#modifybrandCode").combobox({width:200,required:true});
		$("#modifyfirstPrice").textbox({width:200,required:true,validType:'myvalid'});
		$("#modifygoodsDesc").textbox({width:200,height:60,multiline:true,required:true});
	
		//初始化修改商品信息界面的下一页按钮
		$("#modifyGoodsNextPageBtn").linkbutton({
			text:"下一页",
			onClick:function(){
				//判断是否通过验证
				var validate = $("#modifyGoodsForm").form("validate");
				if(validate){
					//关闭修改商品信息界面
					$("#modifyGoodsContainerPanel").dialog('close');
					//打开修改商品属性页面
					$("#modifyProductAttributeContainerPanel").dialog('open');
				}else{
					$.messager.alert('警告','请正确填写');
				}
			}
		});
		
		//修改界面的商品属性页
		$("#modifyProductAttributeContainerPanel").dialog({
			title:"修改商品信息",
			closed:true,
			width:350,
			height:450,
			buttons:'#sc_modifyAttribute',
			modal:true
		});
		
		//修改界面的商品属性页的上一页按钮
		$("#modifyAttributeBackBtn").linkbutton({
			text:"上一页",
			onClick:function(){
				//关闭修改界面的商品属性页
				$("#modifyProductAttributeContainerPanel").dialog('close');
				//打开修改商品信息页面
				$("#modifyGoodsContainerPanel").dialog('open');
			}
		});
		
		//修改界面的商品属性页的确定按钮
		$("#modifyAttributeOkBtn").linkbutton({
			text:"确定",
			onClick:function(){
				var modifyObj = $("#modifyGoodsForm").serializeObject();
				//修改商品信息表单
				$("#modifyGoodsForm").form({
					url:basePath+"/modifyGoodsForm.json",
					success:function(data){
						if(data.rows == 0){
							$.messager.alert('警告','修改商品失败');
						}else{
							//$.messager.alert('信息','修改商品成功');
							$.messager.confirm('信息','修改商品成功',function(r){
								//关闭修改商品信息页面
								$("#modifyProductAttributeContainerPanel").dialog('close');
								//刷新当前页面
								location.reload();
							});
						}
					}
				});
				//提交修改商品信息表单
				$("#modifyGoodsForm").submit();
				//将单属性序列化为对象
				var modifyObjOne = $("#modifyProductAttributeForm").serializeObject();
				//将多属性序列化为对象
				var modifyObjTwo = $("#modifyMultiattribute").serializeObject();
				//创建一个数组，将多属性对象转为数组
				var multiattributeArrary = [];
				for (var i in modifyObjTwo) {
					multiattributeArrary.push(modifyObjTwo[i]); //属性
				}
				//转换成json字符串
				for(var i = 0;i < multiattributeArrary.length;i++){
					multiattributeArrary[i] = JSON.stringify(multiattributeArrary[i]);
				}
				//创建一个数组用来装商品属性对象的键
				var keyOne = [];
				//创建一个数组用来装商品属性对象的值
				var valueOne = [];
				//遍历商品单一属性和唯一属性对象的键和值,并放入数组中
				for(var key in modifyObjOne){
					keyOne.push(key);
					valueOne.push(modifyObjOne[key]);
				}
				//遍历商品多属性对象的键和值,并放入数组中
				for(var key in modifyObjTwo){
					keyOne.push(key);
				}
				for(var i = 0;i < multiattributeArrary.length;i++){
					valueOne.push(multiattributeArrary[i]);
				}
				//获取商品的id
				var goodsId = $("#productListTable").datagrid('getSelections')[0].goodsId;
				//删除属性
				$.ajax({
					url:basePath+"/delNature.json",
					type:"post",
					data:{"goodsId":goodsId},
					dataType:"json",
					success:function(data){
						//提交修改的属性信息
						$.ajax({
							url:basePath+"/modifyProductAttributeForm.json",
							type:"post",
							data:{"attrCode":keyOne,"goodsId":goodsId,"attrValue":valueOne},
							dataType:"json",
							success:function(data){
								if(data.rows == 0){
									$.messager.alert('警告','修改商品属性失败');
								}else{
									//刷新页面
									$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
								}
							}
						});
					}
				});
				
				//提交修改图片
				for(var j = 0;j < modifyImgChoose.length;j++){
					//判断图片有没有修改
					if( $("#modifyFile"+j+"").val() != null && $("#modifyFile"+j+"").val() != '' ){
						var modifyFile = document.getElementById("modifyFile"+j+"").files[0];
						var resId = $("#modifyFile"+j+"").prop("name");
						//如果修改了就删除以前的图片
						$.ajax({
							url:basePath+"/delImgs.json",
							type:"post",
							data:{"resId":resId},
							dataType:"json",
							async:false ,
							success:function(data){
								//创建formdate对象
								var modifyForm = new FormData();
								//添加文件对象
								modifyForm.append("upfile",modifyFile);
								//添加文件
								modifyForm.append("resId",resId);
								//添加图片描述
								modifyForm.append("resDesc",modifyImgChoose);
								//添加商品编号
								modifyForm.append("goodsId",goodsId);
								//传递数据到后台
								$.ajax({
									url:basePath+"/modifyPicture.json",
									type:'post' ,
									dataType:'json',
									data:modifyForm ,
									async:false ,
									processData: false ,
									contentType: false,
									success:function(data){
										//刷新当前页面
										location.reload();
									}
								});
							}
						});
					}else{
					}
				}
			}
		});
		
		//修改界面的商品属性页的取消按钮
		$("#modifyAttributeSave").linkbutton({
			text:"取消",
			onClick:function(){
				//关闭修改商品信息页面
				$("#modifyProductAttributeContainerPanel").dialog('close');
				//刷新当前页面
				location.reload();
			}
		});
		
	});
})

/**
* 搜索
*/
function queryProductList(){
	//序列化搜索表单
	var params = $("#productListSearch").serializeObject();
	$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,params));
}

/**
* 打开新增商品界面
*/
function addProductList(){
	//调用来源门店下拉列表
	sourceOfStores();
	//调用分类一级下拉列表
	classificationLevel();
	$("#addGoodsContainerPanel").dialog('open');
}

/**
 * 新增来源门店下拉列表
 */
function sourceOfStores(){
	$.ajax({
		url:basePath+'/sourceOfStores.json',//查询分类一级
		type:'post',
		dataType:'json',
		success:function(data){
			$("#addShopCode").combobox({
				required:true,
				editable:false,
			    valueField:'shopCode', 
			    textField:'shopName',
			    panelHeight:true,
			    data:data.rows 
			});
		}
	})
}

/**
 * 修改来源门店下拉列表
 */
function modifySourceOfStores(){
	$.ajax({
		url:basePath+'/sourceOfStores.json',//查询分类一级
		type:'post',
		dataType:'json',
		success:function(data){
			$("#modifyshopCode").combobox({
				required:true,
				editable:false,
			    valueField:'shopCode', 
			    textField:'shopName',
			    panelHeight:true,
			    data:data.rows
			});
		}
	})
}

/**
 * 修改来源门店下拉列表二
 */
function modifySourceOfStoresOne(store){
	$.ajax({
		url:basePath+'/sourceOfStores.json',//查询分类一级
		type:'post',
		dataType:'json',
		success:function(data){
			$("#modifyshopCode").combobox({
				required:true,
				editable:false,
			    valueField:'shopCode', 
			    textField:'shopName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
				  	   $("#modifyshopCode").combobox("setValue",store);
				  	   return data;
				  	}
			});
		}
	})
}

/**
* 新增分类一级下拉列表
*/
function classificationLevel(){
	$.ajax({
		url:basePath+'/classificationLevel.json',//查询分类一级
		type:'post',
		dataType:'json',
		success:function(data){
			$("#addCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows 
			});
		}
	})
}

/**
* 修改分类一级下拉列表
*/
function modifyClassificationLevel(){
	$.ajax({
		url:basePath+'/classificationLevel.json',//查询分类一级
		type:'post',
		dataType:'json',
		success:function(data){
			$("#modifycatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:'100px',
			    data:data.rows,
			});
		}
	})
}

/**
* 修改分类一级下拉列表二
*/
function modifyClassificationLevelOne(code){
	$.ajax({
		url:basePath+'/classificationLevel.json',//查询分类一级
		type:'post',
		dataType:'json',
		success:function(data){
			$("#modifycatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
				  	   $("#modifycatCode").combobox("setValue",code);
				  	   return data;
				  	}
			});
		}
	})
}

/**
* 新增分类二级下拉列表
*/
function classificationOfTheSecondary(value){
	$.ajax({
		url:basePath+'/classificationOfTheSecondary.json',//查询分类二级
		type:'post',
		data:{"catCode":value},
		dataType:'json',
		success:function(data){
			$("#addSubCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows
			});
		}
	})
}

/**
* 修改分类二级下拉列表
*/
function modifyClassificationOfTheSecondaryOne(value){
	$.ajax({
		url:basePath+'/classificationOfTheSecondary.json',//查询分类二级
		type:'post',
		data:{"catCode":value},
		dataType:'json',
		success:function(data){
			$("#modifysubCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
			    	$("#modifysubCatCode").combobox('clear');
			    }
			});
		}
	});
}

/**
* 修改分类二级下拉列表二
*/
function modifyClassificationOfTheSecondary(valueTwo){
	$.ajax({
		url:basePath+'/modifyClassificationOfTheSecondaryTwo.json',//查询分类二级
		type:'post',
		data:{"catCode":valueTwo},
		dataType:'json',
		success:function(data){
			$("#modifysubCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
				  	   $("#modifysubCatCode").combobox("setValue",valueTwo);
				  	}
			});
		}
	})
}

/**
 * 新增分类三级下拉列表
 * @param value
 * @returns
 */
function classificationOfThree(value){
	$.ajax({
		url:basePath+'/classificationOfThree.json',//查询分类三级
		type:'post',
		data:{"catCode":value},
		dataType:'json',
		success:function(data){
			$("#addDetailCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows 
			});
		}
	})
}

/**
 * 修改分类三级下拉列表
 * @param value
 * @returns
 */
function modifyClassificationOfThreeOne(value){
	$.ajax({
		url:basePath+'/classificationOfThree.json',//查询分类三级
		type:'post',
		data:{"catCode":value},
		dataType:'json',
		success:function(data){
			$("#modifydetailCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
			    	$("#modifydetailCatCode").combobox('clear');
			    }
			});
		}
	})
}

/**
 * 修改分类三级下拉列表二
 * @param value
 * @returns
 */
function modifyClassificationOfThree(valueThree){
	$.ajax({
		url:basePath+'/modifyClassificationOfTheSecondaryTwo.json',//查询分类三级
		type:'post',
		data:{"catCode":valueThree},
		dataType:'json',
		success:function(data){
			$("#modifydetailCatCode").combobox({
				required:true,
				editable:false,
			    valueField:'catCode', 
			    textField:'catName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
				  	   $("#modifydetailCatCode").combobox("setValue",valueThree);
				  	   return data;
				  	}
			});
		}
	})
}

/**
 * 新增商品品牌下拉列表
 */
function commodityBrand(value){
	$.ajax({
		url:basePath+'/commodityBrand.json',//查询商品品牌
		type:'post',
		data:{"catCode":value},
		dataType:'json',
		success:function(data){
			$("#addBrandCode").combobox({
				required:true,
				editable:false,
			    valueField:'brandCode', 
			    textField:'brandName',
			    panelHeight:true,
			    data:data.rows 
			});
		}
	})
}

/**
 * 修改商品品牌下拉列表
 */
function modifyCommodityBrandOne(value){
	$.ajax({
		url:basePath+'/commodityBrand.json',//查询商品品牌
		type:'post',
		data:{"catCode":value},
		dataType:'json',
		success:function(data){
			$("#modifybrandCode").combobox({
				required:true,
				editable:false,
			    valueField:'brandCode', 
			    textField:'brandName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
			    	$("#modifybrandCode").combobox('clear');
			    }
			});
		}
	})
}

/**
 * 修改商品品牌下拉列表二
 */
function modifyCommodityBrand(valueBrand){
	$.ajax({
		url:basePath+'/modifyCommodityBrand.json',//查询商品品牌
		type:'post',
		data:{"brandCode":valueBrand},
		dataType:'json',
		success:function(data){
			$("#modifybrandCode").combobox({
				required:true,
				editable:false,
			    valueField:'brandCode', 
			    textField:'brandName',
			    panelHeight:true,
			    data:data.rows,
			    onLoadSuccess:function(data){
				  	   $("#modifybrandCode").combobox("setValue",valueBrand);
				  	   return data;
				  	}
			});
		}
	})
}

/**
 * 查询商品属性以及自动添加节点
 * @returns
 */
function formattingProperties(value){
	$.ajax({
		url:basePath+"/formattingProperties.json",
		dataType:"json",
		type:"post",
		data:{"catCode":value},
		success:function(data){
			//设置变量获取属性名称值
			var value;
			//设置变量获取属性名称ID
			var id;
			$("#addProductAttributeTable").find("tr").remove();
			//自动添加节点
			for(var i = 0;i < data.rows.length;i++){
				if(data.rows[i].attrType == '01'){
					console.log(data.rows[i]);
					$("#addProductAttributeTable").append("<tr><td style='text-align: right;width: 65px;'>  "+data.rows[i].attrName+" </td>" 
							+ "<td ><input id='sole"+i+"' name="+data.rows[i].attrCode+" class='easyui-textbox' data-options='width:200'></td></tr>")
				}else if(data.rows[i].attrType == '03'){
					console.log(data.rows[i]);
					//将属性值按照“，”分割，装入数组中
					var dataOptions = data.rows[i].options+",";
					var wps = new Array();
					var start = 0;
					var pos = dataOptions.indexOf(',');
					while (pos > -1) {
						     wps.push(dataOptions.substr(start, pos - start));
						     start = pos + 1;
						     pos = dataOptions.indexOf(',', pos + 1);
						}
					$("#addMultiattributeTable").append("<tr><td style='text-align: right;width: 65px;'>  "+data.rows[i].attrName+"</td>"
							+ "<td><input id="+data.rows[i].attrName+" name="+data.rows[i].attrCode+" class='easyui-combobox' data-options='multiple:true,width:200'>")
					//获取属性名称值
					value = data.rows[i].attrCode;
					//获取属性名称ID
					id = data.rows[i].attrName;
					//调用商品属性可选值下拉列表方法
					propertyDrop(id,value);
				}else{
					console.log(data.rows[i]);
					$("#addProductAttributeTable").append("<tr><td style='text-align: right;width: 65px;'>  "+data.rows[i].attrName+" </td>" 
							+ "<td ><input id="+data.rows[i].attrName+" name="+data.rows[i].attrCode+" ></td></tr>");
					//格式化为easyui格式
					$("#"+ data.rows[i].attrName).combobox({width:200});
					//获取属性名称值
					value = data.rows[i].attrCode;
					//获取属性名称ID
					id = data.rows[i].attrName;
					//调用商品属性可选值下拉列表方法
					propertyDropDownList(id,value);
				}
			}
			$.parser.parse($("#addMultiattributeTable"));
			$.parser.parse($("#addProductAttributeTable"));
		}
	});
}

/**
 * 商品单一属性可选值下拉列表
 * @returns
 */
function propertyDropDownList(id,value){
	$.ajax({
		url:basePath+'/propertyDropDownList.json',//查询商品品牌
		type:'post',
		data:{"attrCode":value},
		dataType:'json',
		success:function(data){
			console.log(data);
			//获取属性编号
			var dataRow = data.rows[0].attrCode;
			//获取可选值
			var dataRows = data.rows[0].options + ",";
			//创建一个数组
			var wps = new Array();
			var start = 0;
			var pos = dataRows.indexOf(',');
			var data = []; 
			var data2 = [];    
			while (pos > -1) {
			     wps.push(dataRows.substr(start, pos - start));
			     start = pos + 1;
			     pos = dataRows.indexOf(',', pos + 1);
			}
			for(var i = 0;i < wps.length;i++){
				data[i] = [{"attrCode":wps[i],"options":wps[i]}];
				data2.push(data[i][0]);
			}
			$("#" + id).combobox({
				required:true,
				editable:false,
			    valueField:'attrCode', 
			    textField:'options',
			    panelHeight:true,
			    data:data2 
			});
		}
	})
}

/**
 * 修改商品单一属性可选值下拉列表
 * @returns
 */
function propertyDropDownListOne(id,value,multiattribute){
	$.ajax({
		url:basePath+'/propertyDropDownList.json',//查询商品品牌
		type:'post',
		data:{"attrCode":value},
		dataType:'json',
		success:function(data){
			//获取属性编号
			var dataRow = data.rows[0].attrCode;
			//获取可选值
			var dataRows = data.rows[0].options + ",";
			//创建一个数组
			var wps = new Array();
			var start = 0;
			var pos = dataRows.indexOf(',');
			var data = []; 
			var data2 = [];    
			while (pos > -1) {
			     wps.push(dataRows.substr(start, pos - start));
			     start = pos + 1;
			     pos = dataRows.indexOf(',', pos + 1);
			}
			for(var i = 0;i < wps.length;i++){
				data[i] = [{"attrCode":wps[i],"options":wps[i]}];
				data2.push(data[i][0]);
			}
			$("#" + id).combobox({
				required:true,
				editable:false,
			    valueField:'attrCode', 
			    textField:'options',
			    panelHeight:true,
			    data:data2,
			    loadFilter:function(data){
				  	   $("#" + id).combobox("setValue",multiattribute);
				  	   return data;
				  	}
			});
		}
	})
}

/**
 * 多选值
 */
function propertyDrop(id,value){
	$.ajax({
		url:basePath+'/propertyDropDownList.json',//查询商品品牌
		type:'post',
		data:{"attrCode":value},
		dataType:'json',
		success:function(data){
			//获取属性编号
			var dataRow = data.rows[0].attrCode;
			//获取可选值
			var dataRows = data.rows[0].options + ",";
			//创建一个数组
			var wps = new Array();
			var start = 0;
			var pos = dataRows.indexOf(',');
			var data = []; 
			var data2 = [];    
			while (pos > -1) {
			     wps.push(dataRows.substr(start, pos - start));
			     start = pos + 1;
			     pos = dataRows.indexOf(',', pos + 1);
			}
			for(var i = 0;i < wps.length;i++){
				data[i] = [{"attrCode":wps[i],"options":wps[i]}];
				data2.push(data[i][0]);
			}
			$("#" + id).combobox({
				required:true,
				editable:false,
			    valueField:'attrCode', 
			    textField:'options',
			    panelHeight:true,
			    data:data2 
			});
		}
	})
}

/**
 * 修改多选值
 */
function propertyDropOne(id,value,multiattributeOne){
	$.ajax({
		url:basePath+'/propertyDropDownList.json',//查询商品品牌
		type:'post',
		data:{"attrCode":value},
		dataType:'json',
		success:function(data){
			//获取属性编号
			var dataRow = data.rows[0].attrCode;
			//获取可选值
			var dataRows = data.rows[0].options + ",";
			//创建一个数组
			var wps = new Array();
			var start = 0;
			var pos = dataRows.indexOf(',');
			var data = []; 
			var data2 = [];    
			while (pos > -1) {
			     wps.push(dataRows.substr(start, pos - start));
			     start = pos + 1;
			     pos = dataRows.indexOf(',', pos + 1);
			}
			for(var i = 0;i < wps.length;i++){
				data[i] = [{"attrCode":wps[i],"options":wps[i]}];
				data2.push(data[i][0]);
			}
			$("#" + id).combobox({
				required:true,
				multiple:true,
			    valueField:'attrCode', 
			    textField:'options',
			    panelHeight:true,
			    data:data2,
			    loadFilter:function(data){
				  	   $("#" + id).combobox("setValue",multiattributeOne);
				  	   return data;
				  	}
			});
		}
	})
}

/**
 * 新增商品界面的取消
 * @returns
 */
function cancel(){
	//关闭新增商品信息界面
	$("#addGoodsContainerPanel").dialog('close');
	//清空新增商品信息界面
	$("#addGoodsForm").form('reset');
	//关闭新增商品属性和图片界面
	$("#addProductAttributeContainerPanel").dialog('close');
	//清空新增商品属性和图片界面
	$("#addProductAttributeForm").form('reset');
}

/**
* 商品图片
*/
function commodityImages(value){
	//删除table中的所有tr节点
	$("#addImgTable").find("tr").remove();
	$.ajax({
		url:basePath+"/commodityImages.json",
		type:"post",
		dataType:"json",
		data:{"catCode":value},
		success:function(data){
			//解析json字符串
			var data1 = JSON.parse(data.rows[0].evalPicDef);
			//获取鉴定图定义的长度
			var length = data1.length;
			//根据鉴定图定义的长度动态添加div
			for(var i = 0;i < length;i++){
				if(length == 1){
					//判断图片是可选还是必选
					if(data1[0].flat[i] == '0'){
						var choose = "可选";
					}else{
						var choose = "必选";
					}
					$("#addImgTable").append(
							"<tr>"
							+ "<td style='text-align: right;width: 65px;'>"+data1[0].picType+"("+choose+")" + "</td>"
							+ "<td>" + "<img src='../../../images/16pic_3505506_b.jpg' height='80px' width='120px' id='img' name='image' onclick='upImg()'/>"
							+ "<input name='upImg' id='file' type='file' onchange='imgInfo()' style='display:none' accept='image/png,image/gif,image/jpeg'/>"
							+ "</td>"
							+ "</tr>"
							);
					//将图片的描述放入数组
					imgChoose.push(data1[0].picType+"(" + choose +")");
					//将图片可选，必选放入数组
					choosable.push(choose);
				}else if(length == 0){
					
				}else{
					//判断图片是可选还是必选
					if(data1[length-1].flat[i] == '0'){
						var choose = "可选";
					}else{
						var choose = "必选";
					}
					$("#addImgTable").append(
							"<tr>"
							+ "<td style='text-align: right;width: 65px;'>"+data1[length-1].picType[i]+"("+choose+")" + "</td>"
							+ "<td>" + "<img src='../../../images/16pic_3505506_b.jpg' height='80px' width='120px' id='img"+i+"' name='img' onclick='upImgs(this,"+i+")'/ >"
							+ "<input name='upImg' id='file"+i+"' type='file' onchange='imgsInfo(this,"+i+")' style='display:none' accept='image/png,image/gif,image/jpeg'/>"
							+ "</td>"
							+ "</tr>"
					);
					//将图片的可选或必选放入数组
					imgChoose.push(data1[length-1].picType[i]+"(" + choose +")");
					//将图片可选，必选放入数组
					choosable.push(choose);
				}
			}
		}
	});
}

/**
* 修改
*/
function modifyProductList(){
	//获取被选中商品的信息
	var rows = $("#productListTable").datagrid('getSelections');
	$.ajax({
		url:basePath+"/modifyProductList.json",
		data:{"goodsId":rows[0].goodsId},
		datatype:"json",
		type:"post",
		success:function(data){
		}
	})
}

/**
* 删除
*/
function delProductList(){
	//获取选中行的数据
	var rows = $("#productListTable").datagrid('getSelections');
	if(rows.length == 0){
		$.messager.alert('警告','请选择一条数据删除');
	}
	//创建一个数组用来装被选中行的数据的商品ID
	var arr = [];
	//循环被选中的数据，获取商品ID
	for(var i = 0;i < rows.length;i++){
		//把被选中的商品ID放入数组中
		arr.push(rows[i].goodsId);
	}
	$.ajax({
		url:basePath+"/delProductList.json",
		data:{"arr":arr},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.rows == 0){
				$.messager.alert('警告','删除失败');
			}else{
				$.messager.alert('信息','删除成功');
				//刷新页面
				$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
			}
		}
	})
}

/**
 * 触发type:file点击
 */
function upImg(){
	$("#file").trigger('click');
}

/**
 * 修改图片src(回显)
 */
function imgInfo(){
	var files = document.getElementById("file").files[0];
	objUrl = window.URL.createObjectURL(files);
	$("#img").attr('src',objUrl);
}

/**
 * 触发type:file点击
 */
function upImgs(img,i){
	$("#file"+i+"").trigger('click');
}

/**
 * 修改图片src(回显)
 */
function imgsInfo(file,i){
	var files = document.getElementById("file"+i+"").files[0];
	objUrl = window.URL.createObjectURL(files);
	$("#img"+i+"").attr('src',objUrl);
}

/**
* 提交按钮
*/
function submitProductList(){
	//获取选中行的信息
	var row = $("#productListTable").datagrid('getSelections');
	if(row.length == 0){
		$.messager.alert('警告','请选择一条数据提交');
	}else{
		//获取被选中的商品id
		var goodsId = row[0].goodsId;
		//判断商品的状态是否可以提交
		if(row[0].goodsStat == '待提交'){
			$.ajax({
				url:basePath+"/submitProductList.json",
				data:{"goodsId":goodsId},
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.rows == 0){
						$.messager.alert('警告','提交失败');
					}else{
						$.messager.alert('信息','提交成功');
						//刷新页面
						$("#productListTable").datagrid(Common.createDatagridOptionsParams(basePath, "/productListSearch.json",135,null));
					}
				}
			});
		}else{
			$.messager.alert('信息','此商品已提交');
		}
	}
}

/**
* 打开鉴定页面
*/
function authenticateProductList(){
	//获取被选中商品的信息
	var commodity = $("#productListTable").datagrid('getSelections');
	if(commodity.length == 0){
		$.messager.alert('警告','请选择一条数据鉴定！');
	}else{
		if(commodity[0].goodsStat == '待评估' || commodity[0].goodsStat == '待鉴定'){
			var a = commodity[0].catCode;
			//获取分类一级的值
			var str1 = a.slice(0,a.indexOf(">"));
			//获取被选中商品的ID
			var goodsId = commodity[0].goodsId;
			console.log(commodity);
			//回填
			$("#authenticategoodsIdHide").val(commodity[0].goodsId);
			$("#authenticategoodsId").textbox('setValue',commodity[0].goodsId);
			$("#authenticateshopCode").textbox('setValue',commodity[0].shopCode);
			$("#authenticateinputDate").textbox('setValue',commodity[0].inputDate);
			$("#authenticatecatCode").textbox('setValue',str1);
			$("#authenticatesubCatCode").textbox('setValue',commodity[0].subCatCode);
			$("#authenticatedetailCatCode").textbox('setValue',commodity[0].detailCatCode);
			$("#authenticategoodsName").textbox('setValue',commodity[0].goodsName);
			$("#authenticatebrandCode").textbox('setValue',commodity[0].brandCode);
			$("#authenticatefirstPrice").textbox('setValue',commodity[0].firstPrice);
			$("#authenticategoodsDesc").textbox('setValue',commodity[0].goodsDesc);
			//发送请求查询商品的基本信息
			$.ajax({
				url:basePath+"/authenticateProductList.json",
				data:{"goodsId":goodsId},
				dataType:"json",
				type:"post",
				success:function(data){
					//动态增加节点
					for(var i = 0;i < data.rows.length;i++){
						//判断是不是多属性
						if(data.rows[i].attrType == '03'){
							//将json字符串转为数组
							var multiattribute = JSON.parse( data.rows[i].attrValue );
							$("#authenticateProductAttributeTable").append("<tr><td style='text-align: right;width: 65px;'>"+data.rows[i].attrName+"</td>"
									+ "<td><input id='input"+i+"' name='name"+i+"' class='easyui-textbox' data-options='width:200,disabled:true'/></td></tr>");
							//解析成easyui格式
							$.parser.parse($("#authenticateProductAttributeTable"));
							$("#input"+i+"").textbox('setValue',multiattribute);
						}else{
							$("#authenticateProductAttributeTable").append("<tr><td style='text-align: right;width: 65px;'>"+data.rows[i].attrName+"</td>"
									+ "<td><input id='input"+i+"' name='name"+i+"' class='easyui-textbox' data-options='width:200,disabled:true'/></td></tr>");
							//解析成easyui格式
							$.parser.parse($("#authenticateProductAttributeTable"));
							$("#input"+i+"").textbox('setValue',data.rows[i].attrValue);
						}		
					}
					
				}
			});
			
			$.ajax({
				url:basePath+"/queryImgs.json",
				data:{"goodsId":goodsId},
				dataType:"json",
				type:"post",
				success:function(data){
					//获取图片的描述
					var authenticateResDesc = data.rows[0].resDesc;
					//创建一个数组
					var authenticateRes = [];
					//字符分割
					authenticateResDesc = authenticateResDesc.split(",");
					for(var i = 0;i < authenticateResDesc.length;i++){
						//将分割的字符放放进数组
						authenticateRes[i] = authenticateResDesc[i];
					}
					//动态添加图片节点
					for(var i = 0;i < authenticateRes.length;i++){
						$("#authenticateProductAttributeTable").append("<tr><td style='text-align: right;width: 65px;'>"+authenticateRes[i]+"</td>"
							+ "<td><img src='#' height='80px' width='120px' id='authenticateImg"+i+"' name='authenticateImg'/ >")
					}
					//回显图片
					for(var b = 0;b < data.rows.length; b++){
						var resId = data.rows[b].resId ;
						var resFile = data.rows[b].resFile;
						$("#authenticateProductAttributeTable img").eq(b).attr("src",basePath+"/previewPicturesByGoodsId.json?resId="+resId+"&&resFile="+resFile)
		 
					}
				}
			});
			//打开鉴定界面的商品信息页
			$("#authenticatePanel").dialog('open');
		}else{
			$.messager.alert('信息','此商品未提交或者已评估');
		}
	}
}

/**
* 评估
*/
function assessProductList(){
	//获取被选中商品的信息
	var shopInfo = $("#productListTable").datagrid('getSelections');
	if(shopInfo.length == 0){
		$.messager.alert('警告','请选择一条数据评估！');
	}else{
		//根据商品状态判断是否可以评估
		if(shopInfo[0].goodsStat == '待评估' || shopInfo[0].goodsStat == '已评估'){
			//发送数据到后台
			$.ajax({
				url:basePath+"/assessProductList.json",
				data:{"goodsId":shopInfo[0].goodsId},
				dataType:"json",
				type:"post",
				success:function(data){
					//回填
					$("#inAssessingGoodsIdHide").val(data.rows[0].goodsId);
					$("#inAssessingGoodsId").textbox('setValue',data.rows[0].goodsId);
					$("#inAssessingGoodsQuality").textbox('setValue',data.rows[0].goodsQuality);
					$("#inAssessingIdentifyResult").textbox('setValue',data.rows[0].identifyResult);
					$("#inAssessingCreateBy").textbox('setValue',data.rows[0].createBy);
					$("#inAssessingCreateTime").textbox('setValue',data.rows[0].createTime);
					$("#inAssessingIdentifyDesc").textbox('setValue',data.rows[0].identifyDesc);
					//打开商品评估界面的商品鉴定页
					$("#inAssessingPanel").dialog('open');
				}
			})
		}else{
			$.messager.alert('信息','此商品未鉴定');
		}
	}
}

/**
* 修改
*/
function modifyProductList(){
	//获取被选中商品的信息
	var shopInfo = $("#productListTable").datagrid('getSelections');
	if(shopInfo.length == 0){
		$.messager.alert('警告','请选择一条数据修改！');
	}else{
		//获取被选中商品的ID
		var goodsId = $("#productListTable").datagrid('getSelections')[0].goodsId;
		//回填
		$("#modifygoodsIdHideen").val(shopInfo[0].goodsId);
		$("#modifyGoodsStat").val(shopInfo[0].goodsStatName);
		$("#modifygoodsId").textbox('setValue',shopInfo[0].goodsId);
		$("#modifyinputDate").datebox('setValue',shopInfo[0].inputDate);
		$("#modifygoodsName").textbox('setValue',shopInfo[0].goodsName);
		$("#modifyfirstPrice").textbox('setValue',shopInfo[0].firstPrice);
		$("#modifygoodsDesc").textbox('setValue',shopInfo[0].goodsDesc);
		//获取被选中商品的来源门店
		var store = shopInfo[0].shopName;
		//获取被选中商品的一级分类
		var b = shopInfo[0].catCode;
		var classify = b.slice(0,b.indexOf(">"));
		var c = shopInfo[0].detailCatName;
		var d = c.slice(0,c.indexOf("."));
		//查询分类一级编号
		$.ajax({
			url:basePath+"/stairOne.json",
			data:{"catCode":d},
			dataType:"json",
			type:"post",
			success:function(data){
				var code = data.rows[0].catCode;
				//调用修改分类一级下拉列表二
				modifyClassificationLevelOne(code);
			}
		});
		//获取被选中商品的二级分类
		var valueTwo = shopInfo[0].subCatName;
		//获取被选中商品的三级分类
		var valueThree = shopInfo[0].detailCatName;
		//获取被选中商品的品牌
		//var brand = shopInfo[0].brandCode;
		var valueBrand = shopInfo[0].brandName;
		//调用修改来源门店下拉列表二
		modifySourceOfStoresOne(store);
		//调用修改分类二级下拉列表二
		modifyClassificationOfTheSecondary(valueTwo);
		//调用修改分类三级下拉列表二
		modifyClassificationOfThree(valueThree);
		//调用修改商品品牌下拉列表二
		modifyCommodityBrand(valueBrand);
		//打开修改商品信息页面
		$("#modifyGoodsContainerPanel").dialog('open');
		//查询商品属性
		$.ajax({
			url:basePath+"/authenticateProductList.json",
			data:{"goodsId":goodsId},
			dataType:"json",
			type:"post",
			success:function(data){
				//动态增加节点
				for(var i = 0;i < data.rows.length;i++){
					//判断是不是多属性
					if(data.rows[i].attrType == '03'){
						//将json字符串转为数组
						var multiattributeOne = JSON.parse( data.rows[i].attrValue );
						//将数组中的元素拼接成字符串
						//var multiattributeOne = a.join(",");
						$("#modifyMultiattributeTable").append("<tr><td style='text-align: right;width: 65px;'>"+data.rows[i].attrName+"</td>"
								+ "<td><input id='input"+i+"' name="+data.rows[i].attrCode+" class='easyui-combobox' data-options='width:200'/></td></tr>");
						//解析成easyui格式
						$.parser.parse($("#modifyMultiattributeTable"));
						var id = "input" + i;
						var value = data.rows[i].attrCode;
						//调用修改多选值
						propertyDropOne(id,value,multiattributeOne);
					}else{
						$("#modifyProductAttributeTable").append("<tr><td style='text-align: right;width: 65px;'>"+data.rows[i].attrName+"</td>"
								+ "<td><input id='input"+i+"' name="+data.rows[i].attrCode+" class='easyui-combobox' data-options='width:200'/></td></tr>");
						//解析成easyui格式
						$.parser.parse($("#modifyProductAttributeTable"));
						var value = data.rows[i].attrCode;
						var id = "input" + i;
						var multiattribute = data.rows[i].attrValue;
						//调用修改商品单一属性
						propertyDropDownListOne(id,value,multiattribute);
					}		
				}
			}
		});
		
		//查询商品图片
		$.ajax({
			url:basePath+"/queryImgs.json",
			data:{"goodsId":goodsId},
			dataType:"json",
			type:"post",
			success:function(data){
				console.log(data);
				//获取图片的描述
				var resDesc = data.rows[0].resDesc;
				//创建一个数组
				var res = [];
				//字符分割
				resDescs = resDesc.split(",");
				for(var i = 0;i < resDescs.length;i++){
					//将分割的字符放放进数组
					res[i] = resDescs[i];
				}
				//动态添加图片节点
				for(var i = 0;i < res.length;i++){
					console.log(data.rows[i].resId);
					$("#modifyImgTable").append("<tr><td style='text-align: right;width: 65px;'>"+res[i]+"</td>"
						+ "<td><img src='#' height='80px' width='120px' id='modifyImg"+i+"' name='modifyImg' onclick='modifyUpImgs(this,"+i+")'/ >"
						+ "<input name="+data.rows[i].resId+" id='modifyFile"+i+"' type='file' onchange='modifyImgsInfo(this,"+i+")' style='display:none' accept='image/png,image/gif,image/jpeg'/></td></tr>")
						//将图片描述放入数组中
						modifyImgChoose.push(res[i]);
						
				}
				//回显图片
				for(var b = 0;b < data.rows.length; b++){
					var resId = data.rows[b].resId ;
					var resFile = data.rows[b].resFile;
					$("#modifyImgTable img").eq(b).attr("src",basePath+"/previewPicturesByGoodsId.json?resId="+resId+"&&resFile="+resFile)
	 
				}
			}
		})
	}
}

/**
 * 触发type:file点击
 */
function modifyUpImgs(img,i){
	$("#modifyFile"+i+"").trigger('click');
}

/**
 * 修改图片src(回显)
 */
function modifyImgsInfo(file,i){
	var files = document.getElementById("modifyFile"+i+"").files[0];
	objUrl = window.URL.createObjectURL(files);
	$("#modifyImg"+i+"").attr('src',objUrl);
}

/**
* 新增商品编号验证
*/
function shopVerify(){
	$("#addGoodsId").textbox({
		required:true,
		missingMessage:"序号号不能为空",
		invalidMessage:"序号已存在",
		validType:"remoteValid['"+basePath+"/shopVerify.json','goodsId']"
	});
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