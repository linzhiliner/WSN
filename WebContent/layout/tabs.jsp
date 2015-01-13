<%@ page language="java" import="java.net.URLEncoder" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	  function addTempTabs(data){
		  var ip=''+data.textContent;
		  var type=data.className;
		  console.info(type);
		  var t=$('#layout_center_tabs');
		  //var url='layout/center.jsp?ip='+encodeURI(encodeURI(ip));
		 // var con='<iframe scrolling="no" frameborder="0"  src="'+url+'" style="min-width:700px;height:400px;"></iframe>';
		  var options={
				  title:ip,
				  closable:false,
				  href:'layout/center3.jsp?ip='+encodeURI(encodeURI(ip))
				  //href:'layout/center3.jsp?type='+type+'&ip='+encodeURI(encodeURI(ip))
				  //content:con 
		  };
		  var tab=t.tabs('getSelected');
		  t.tabs('update',{
			  tab:tab,
			  options:options
		  });
	
	  }
	  
	  function addLightTabs(data){
		  var ip=''+data.textContent;
		  var type=data.className;
		  console.info(type);
		  var t=$('#layout_center_tabs');
		  //var url='layout/center.jsp?ip='+encodeURI(encodeURI(ip));
		 // var con='<iframe scrolling="no" frameborder="0"  src="'+url+'" style="min-width:700px;height:400px;"></iframe>';
		  var options={
				  title:ip,
				  closable:false,
				  href:'layout/center4.jsp?ip='+encodeURI(encodeURI(ip))
				  //href:'layout/center3.jsp?type='+type+'&ip='+encodeURI(encodeURI(ip))
				  //content:con 
		  };
		  var tab=t.tabs('getSelected');
		  t.tabs('update',{
			  tab:tab,
			  options:options
		  });
	
	  }
</script>
<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false" style="overflow: hidden;">
	<div title="首页"></div>
</div>