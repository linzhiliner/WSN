<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WSN</title>
<jsp:include page="admin/html/inc.jsp"></jsp:include>
<script>
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
	<script type="text/javascript" src="js/jquery-easyui-1.3.4/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
	<link type="text/css" rel="stylesheet" href="js/jquery-easyui-1.3.4/themes/default/easyui.css"></link>
	<link type="text/css" rel="stylesheet" href="js/jquery-easyui-1.3.4/themes/metro/easyui.css"></link>
    <script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/exporting.js"></script>
    <style type="text/css">
      #main_weather {
      text-align:center;
      width:190px;
      }
    </style>
</head>
<body class="easyui-layout">
        <div data-options="region:'north'" style="height:110px; background: url('admin/images/houtai_top.jpg') no-repeat;">
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
        <div data-options="region:'south',split:true" style="height:50px;"></div>
        <div id='main_weather' data-options="region:'east',split:true" title="East">
          <div style="width:190px;height: 150px">
            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="200" height="65">
              <param name="movie" value="http://t.xidie.com/skin/2010-1020.swf?color=050402" />
              <param name="quality" value="high" />
              <param name="wmode" value="transparent" />
              <embed src="http://t.xidie.com/skin/2010-1020.swf?color=050402" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="200" height="65"  wmode="transparent"></embed>
            </object>
          </div>
          <div id="cc" class="easyui-calendar" style="width:190px;height:180px;"></div>
        </div>
        <div data-options="region:'center',iconCls:'icon-ok'" title="实时监控图">
           <jsp:include page="layout/tabs.jsp"></jsp:include>
        </div>        
        <div data-options="region:'west',split:true" title="传感器节点" style="width:280px;font-size:">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <div title="温度节点" style="padding:10px;">
                   <ul id="ul_temp">
                      <script>
                      $(document).ready(function () {
                    	  var tt=document.getElementById("ul_temp");
                    	  
                          $.getJSON("http://localhost:8080/WSN/sensor.action?dataType="+'temp'+"", function (data) {
                      //  	  console.info(data);
                          var obj=data.rows;
                          for(var i=0;i<obj.length;i++){                         	  
                        	  tt.innerHTML=tt.innerHTML+
                        	  "<li>节点：<a class='temp' href='javascript:void(0)' onclick='addTempTabs(this)'>"
                        		  +obj[i]+"</a></li><br/>";
                          }
                       });
                      });
                      </script>
                   </ul>
                </div>
                
                <div title="光照节点"  style="padding:10px;">
                  <ul id="ul_light">
                      <script>
                      $(document).ready(function () {
                    	  var tt=document.getElementById("ul_light");
                          $.getJSON("http://localhost:8080/WSN/sensor.action?dataType="+'light'+"", function (data) {
                          var obj=data.rows;
                          console.info(data);
                          for(var i=0;i<obj.length;i++){                         	  
                        	  tt.innerHTML=tt.innerHTML+
                        	  "<li>节点：<a class='light' href='javascript:void(0)' onclick='addLightTabs(this)'>"
                        		  +obj[i]+"</a></li><br/>";
                          }
                       });
                      });
                      </script>
                   </ul>
                </div>
                
                <div title="湿度节点" style="padding:10px;">
                   <ul id="ul_hum">
                      <script>
                      $(document).ready(function () {
                    	  var tt=document.getElementById("ul_hum");
                    	  
                          $.getJSON("http://localhost:8080/WSN/sensor.action?dataType="+'hum'+"", function (data) {
                      //  	  console.info(data);
                          var obj=data.rows;
                          for(var i=0;i<obj.length;i++){                         	  
                        	  tt.innerHTML=tt.innerHTML+
                        	  "<li>节点：<a class='hum' href='javascript:void(0)' onclick='addTempTabs(this)'>"
                        		  +obj[i]+"</a></li><br/>";
                          }
                       });
                      });
                      </script>
                   </ul>
                </div>
            </div>
        </div>
</body>

</html>