function  loadCategoryLevel(pid,value,obj){
    if (value != null){
        $.ajax({
            type:"GET",//请求类型
            url:"/appCategory/list.json",//请求的url
            data:{pid:pid},//请求参数
            dataType:"json",//ajax接口（请求url）返回的数据类型
            success:function(data){//data：返回数据（json对象）
                obj.html("");
                var options = "<option value=\"\">--请选择--</option>";
                obj.append(options);
                $.each(data,function (index,item) {
                    if (value != null && value != undefined && item.id==value) {
                        options = "<option selected=\"selected\" value=\""+item.id+"\" >"+item.categoryName+"</option>";
                    }else {
                        options = "<option value=\""+item.id+"\">"+item.categoryName+"</option>";
                    }
                    obj.append(options);
                });

            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                alert("加载分类列表失败！");
            }
        });
    }
};



$(function () {

    var cl1 = $("#queryCategoryLevel1").attr("categoryLevel1");
    var cl2 = $("#queryCategoryLevel2").attr("categoryLevel2");
    var cl3 = $("#queryCategoryLevel3").attr("categoryLevel3");
    //动态加载一级分类列表
    loadCategoryLevel(0,cl1,$("#queryCategoryLevel1"));
    //动态加载二级分类列表
    loadCategoryLevel(cl1,cl2,$("#queryCategoryLevel2"));
    //动态加载三级分类列表
    loadCategoryLevel(cl2,cl3,$("#queryCategoryLevel3"));

    //异步请求一级分类
    /*$.ajax({
        type:"GET",//请求类型
        url:"/appCategory/list.json",//请求的url
        data:{pid:0},//请求参数
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data) {//data：返回数据（json对象）
            $("#queryCategoryLevel1").html("");
            var options = "<option value=\"\">--请选择--</option>";
            for(var i = 0; i < data.length; i++){
                if ($("#queryCategoryLevel1").attr("categoryLevel1") == data[i].id){
                    options += "<option selected value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                } else {
                    options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                }
            }
            $("#queryCategoryLevel1").html(options);
        }
    });*/

    //动态加载app所属状态列表
    $.ajax({
        type:"GET",//请求类型
        url:"/dataDictionary/list.json",//请求的url
        data:{tcode:"APP_STATUS"},//请求参数
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            $("#selectStatus").html("");
            var options = "<option value=\"\">--请选择--</option>";
            for(var i = 0; i < data.length; i++){
                if ($("#selectStatus").attr("status") == data[i].valueId){
                    options += "<option selected value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
                } else {
                    options += "<option value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
                }
            }
            $("#selectStatus").html(options);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            alert("加载app所属状态列表失败！");
        }
    });

    //动态加载所属平台列表
    $.ajax({
        type:"GET",//请求类型
        url:"/dataDictionary/list.json",//请求的url
        data:{tcode:"APP_FLATFORM"},//请求参数
        dataType:"json",//ajax接口（请求url）返回的数据类型
        success:function(data){//data：返回数据（json对象）
            $("#selectPlatform").html("");
            var options = "<option value=\"\">--请选择--</option>";
            for(var i = 0; i < data.length; i++){
                if ($("#selectPlatform").attr("platformId") == data[i].valueId) {
                    options += "<option selected value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
                }else {
                    options += "<option value=\""+data[i].valueId+"\">"+data[i].valueName+"</option>";
                }
            }
            $("#selectPlatform").html(options);
        },
        error:function(data){//当访问时候，404，500 等非200的错误状态码
            alert("加载平台列表失败！");
        }
    });
});
//当一级分类改变时动态加载二级分类
$("#queryCategoryLevel1").change(function(){
	var queryCategoryLevel1 = $("#queryCategoryLevel1").val();
	if(queryCategoryLevel1 != '' && queryCategoryLevel1 != null){
		$.ajax({
			type:"GET",//请求类型
			url:"/appCategory/list.json",//请求的url
			data:{pid:queryCategoryLevel1},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
                $("#queryCategoryLevel2").removeAttr("disabled");
				$("#queryCategoryLevel2").html("");
				var options = "<option value=\"\">--请选择--</option>";
				for(var i = 0; i < data.length; i++){
                    if ($("#queryCategoryLevel2").attr("categoryLevel2") == data[i].id){
                        options += "<option selected value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                    } else {
                        options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                    }
				}
				$("#queryCategoryLevel2").html(options);
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("加载二级分类失败！");
			}
		});
	}else{
		$("#queryCategoryLevel2").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#queryCategoryLevel2").html(options);
        $("#queryCategoryLevel2").attr("disabled","disabled");
	}
	$("#queryCategoryLevel3").html("");
	var options = "<option value=\"\">--请选择--</option>";
	$("#queryCategoryLevel3").html(options);
    $("#queryCategoryLevel3").attr("disabled","disabled");
});

//当二级分类改变时动态加载三级分类
$("#queryCategoryLevel2").change(function(){
	var queryCategoryLevel2 = $("#queryCategoryLevel2").val();
	if(queryCategoryLevel2 != '' && queryCategoryLevel2 != null){
		$.ajax({
			type:"GET",//请求类型
			url:"/appCategory/list.json",//请求的url
			data:{pid:queryCategoryLevel2},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
                $("#queryCategoryLevel3").removeAttr("disabled");
				$("#queryCategoryLevel3").html("");
				var options = "<option value=\"\">--请选择--</option>";
				for(var i = 0; i < data.length; i++){
                    if ($("#queryCategoryLevel3").attr("categoryLevel3") == data[i].id){
                        options += "<option selected value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                    } else {
                        options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                    }
				}
				$("#queryCategoryLevel3").html(options);
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("加载三级分类失败！");
			}
		});
	}else{
		$("#queryCategoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#queryCategoryLevel3").html(options);
        $("#queryCategoryLevel3").attr("disabled","disabled");
	}
});

$(".addVersion").on("click",function(){
	var obj = $(this);
	window.location.href="appversionadd?id="+obj.attr("appinfoid");
});
$(".modifyVersion").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	var versionid = obj.attr("versionid");
	var appinfoid = obj.attr("appinfoid");
	if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
		if(versionid == null || versionid == ""){
			alert("该APP应用无版本信息，请先增加版本信息！");
		}else{
			window.location.href="appversionmodify?vid="+ versionid + "&aid="+ appinfoid;
		}
	}else{
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改其版本信息，只可进行【新增版本】操作！");
	}
});
$(".modifyAppInfo").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
		window.location.href="/dev/user/appinfomodify/"+ obj.attr("appinfoid");
	}else{
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改！");
	}
});

$(document).on("click",".saleSwichOpen,.saleSwichClose",function(){
	var obj = $(this);
	var appinfoid = obj.attr("appinfoid");
	var saleSwitch = obj.attr("saleSwitch");
	if("open" === saleSwitch){
		saleSwitchAjax(appinfoid,obj);
	}else if("close" === saleSwitch){
		if(confirm("你确定要下架您的APP应用【"+obj.attr("appsoftwarename")+"】吗？")){
			saleSwitchAjax(appinfoid,obj);
		}
	}
});

var saleSwitchAjax = function(appId,obj){
	$.ajax({
		type:"PUT",
		url:appId+"/sale.json",
		dataType:"json",
		success:function(data){
			/*
			 * resultMsg:success/failed
			 * errorCode:exception000001
			 * appId:appId
			 * errorCode:param000001
			 */
			if(data.errorCode === '0'){
				if(data.resultMsg === "success"){//操作成功
					if("open" === obj.attr("saleSwitch")){
						//alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【上架】操作成功");
						$("#appInfoStatus" + obj.attr("appinfoid")).html("已上架");
						obj.className="saleSwichClose";
						obj.html("下架");
						obj.attr("saleSwitch","close");
						$("#appInfoStatus" + obj.attr("appinfoid")).css({
							'background':'green',
							'color':'#fff',
							'padding':'3px',
							'border-radius':'3px'
						});
						$("#appInfoStatus" + obj.attr("appinfoid")).hide();
						$("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
					}else if("close" === obj.attr("saleSwitch")){
						//alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【下架】操作成功");
						$("#appInfoStatus" + obj.attr("appinfoid")).html("已下架");
						obj.className="saleSwichOpem";
						obj.html("上架");
						obj.attr("saleSwitch","open");
						$("#appInfoStatus" + obj.attr("appinfoid")).css({
							'background':'red',
							'color':'#fff',
							'padding':'3px',
							'border-radius':'3px'
						});
						$("#appInfoStatus" + obj.attr("appinfoid")).hide();
						$("#appInfoStatus" + obj.attr("appinfoid")).slideDown(300);
					}
				}else if(data.resultMsg === "failed"){//删除失败
					if("open" === obj.attr("saleSwitch")){
						alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【上架】操作失败");
					}else if("close" === obj.attr("saleSwitch")){
						alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【下架】操作失败");
					}
				}
			}else{
				if(data.errorCode === 'exception000001'){
					alert("对不起，系统出现异常，请联系IT管理员");
				}else if(data.errorCode === 'param000001'){
					alert("对不起，参数出现错误，您可能在进行非法操作");
				}
			}
		},
		error:function(data){
			if("open" === obj.attr("saleSwitch")){
				alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【上架】操作成功");
			}else if("close" === obj.attr("saleSwitch")){
				alert("恭喜您，【"+obj.attr("appsoftwarename")+"】的【下架】操作成功");
			}
		}
	});
};



$(".viewApp").on("click",function(){
	var obj = $(this);
	window.location.href="appview/"+ obj.attr("appinfoid");
});

$(".deleteApp").on("click",function(){
	var obj = $(this);
	if(confirm("你确定要删除APP应用【"+obj.attr("appsoftwarename")+"】及其所有的版本吗？")){
		$.ajax({
			type:"GET",
			url:"delapp.json",
			data:{id:obj.attr("appinfoid")},
			dataType:"json",
			success:function(data){
				if(data.delResult == "true"){//删除成功：移除删除行
					alert("删除成功");
					obj.parents("tr").remove();
				}else if(data.delResult == "false"){//删除失败
					alert("对不起，删除AAP应用【"+obj.attr("appsoftwarename")+"】失败");
				}else if(data.delResult == "notexist"){
					alert("对不起，APP应用【"+obj.attr("appsoftwarename")+"】不存在");
				}
			},
			error:function(data){
				alert("对不起，删除失败");
			}
		});
	}
});

	
