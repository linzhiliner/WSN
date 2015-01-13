<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="inc.jsp"></jsp:include>
<script>
var editRow;
var datagrid;
var url;
var rows;

$(function(){
	datagrid = $('#dg').datagrid({  
	    url:'../../listFaq.action',
	    fit:true,
	    fitColumns:false,
	    nowarp:false,
	    loadMsg:"加载中",
	    pagination:true,
	    pageSize:10,
	    pageList:[10,20,30,40,50],
	    columns:[[  
			{field:'id',title:'Id',width:200,checkbox:true},
			{field:'yijian',title:'意见',width:1000,editor:{
				type:'validatebox',	
				options:{
					required:true
				}}
			}
	    ]]  ,
	 
	    toolbar: [{
			iconCls: 'icon-remove',
			text:"   已完成",
			handler: function(){
				rows = datagrid.datagrid("getSelections");
				var ids =[];
				if(rows.length>0){
					
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);
					}
					$.ajax({
					type:"post",
					url:"。。/../deleteFaq.action",
					data:{ids:ids.join()},
					dataType:"json",
					success:function(data){
						$.messager.show({
							title:'提示',
							msg:"Bug已经修复",
							timeout:5000,
							showType:'slide'
						});
						datagrid.datagrid("load",{});
					}
					});	
				}else{
					$.messager.alert("提示",'尚未选择删除列','error');
				}
				}
		}],

	});
});

</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" >  
                     
            <div data-options="region:'center'" >
            <table id="dg" ></table> 
            </div>  
</div>  

<div id='menu' class='easyui-menu' style='width:120px;display:none;'>
<div onclick='append()' iconCls="icon-add">增加</div>
<!-- <div onclick='append()' iconCls="icon-remove">删除</div>   -->
<div onclick='edit()' iconCls="icon-edit">修改</div>
</div>
</body>
</html>

