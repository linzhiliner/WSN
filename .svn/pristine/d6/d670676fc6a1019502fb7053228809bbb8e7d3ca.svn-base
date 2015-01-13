<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="inc.jsp"></jsp:include>
<style type = "text/css">
	ul {
		list-style-type:none;
		padding:0;
		margin:0;
	}
	ul li {
		padding:0;
		margin:0;
	}
	ul li a {
		display:block;
		height:30px;
		line-height:30px;
		text-decoration:none;
		text-align:center;
		border-bottom:1px solid #ccc;
		color:black;
	}
	ul li a:hover {
		font-weight:bold;
		background:#ccc;
	}
	</style>
	<script>
		function addTab(title, url){
			if ($('#tt').tabs('exists', title)){
				$('#tt').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		};
		

		var changeTheme = function(themeName) {
			var $easyuiTheme = $('#easyuiTheme');
			var url = $easyuiTheme.attr('href');
			var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
			$easyuiTheme.attr('href', href);

			var $iframe = $('iframe');
			if ($iframe.length > 0) {
				for ( var i = 0; i < $iframe.length; i++) {
					var ifr = $iframe[i];
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				}
			}

			$.cookie('easyuiThemeName', themeName, {
				expires : 7
			});
		};

		
	</script>
</head>
<body>
<div id="cc" class="easyui-layout"  fit="true" style="width:100%;height:100%;">  
    <div data-options="region:'north',split:true" style="height:110px; background: url('../images/houtai_top.jpg') no-repeat;"> 
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_pfMenu" iconCls="icon-ok">更换皮肤</a> 

</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeTheme('default');">default</div>
	<div onclick="changeTheme('gray');">gray</div>
	<div onclick="changeTheme('black');">black</div>
	<div onclick="changeTheme('bootstrap');">bootstrap</div>
	<div onclick="changeTheme('metro');">metro</div>
	<div onclick="changeTheme('sunny');">sunny</div>
	<div onclick="changeTheme('pepper-grinder');">pepper-grinder</div>
	<div onclick="changeTheme('cupertino');">cupertino</div>
	<div onclick="changeTheme('dark-hive');">dark-hive</div>
</div>

    </div>  
    <div data-options="region:'west',title:'主控制界面',split:true" style="width:200px;">
    
    <div class="easyui-accordion" >
				<div title="基础信息管理" selected="true" >
					<ul>
						<li><a href="#" onclick="addTab('意见反馈','yijian.jsp')" >意见反馈</a> </li>
					<!-- 	<li><a href="#" onclick="addTab('商铺档案','shopList.html')" >商铺档案</a></li>  -->
					</ul>
					
				</div>
			
				<div title="其他管理">					 
				</div>
			</div>
    </div>  
    <div data-options="region:'south',split:true" style="height:50px;">
		<p style="text-align:center;padding:5px;margin:0">南京邮电大学 科技产业发展中心  版权所有</p>
		<p style="text-align:center;padding:5px;margin:0">技术支持:XXX@126.com</p>
	</div>
    <div data-options="region:'center'" class="easyui-tabs" id="tt" >
	<div title="Home">  
	</div>  
    </div>  
</div>  
</body>
</html>