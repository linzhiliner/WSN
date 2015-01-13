<%@ page language="java"  import="java.net.URLDecoder" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String ip = request.getParameter("ip");
  //用于当前页面显示
  String ips=URLDecoder.decode(ip,"utf-8");
  //用于传递到后台
%>
<div class="easyui-layout" data-options="fit:true">
   <div data-options="region:'north',split:true,border:false" style="height:400px">
		  <script type="text/javascript" src="js/highcharts.js"></script>
		  <script type="text/javascript" src="js/exporting.js"></script>
		  <script type='text/javascript'>
		  $(document).ready(function () {
		      //先把图表的参数定义好。
		       var options = {
		           chart: {
		                renderTo: 'container', //图表放入的容器ID 
		                type: 'spline'  ,//图表类型 
		           }, 
		           title: {
		               text: '无线传感器数据图',
		               style:{
		            		color: '#3E576F',
		            		fontSize: '30px'
		               }
		               
		           },
		           subtitle: {
		               text: '南京邮电大学',
		               style:{
		           		color: '#3E576F',
		           		fontSize: '15px'
		              },
		               margin:50
		           },
		           xAxis: {
		               categories: []
		           },
		           yAxis: {
		               title: {
		                   text: 'Temperature (°C)'
		               }
		           },
		           tooltip: {
		               enabled: true,
		               formatter: function() {
		                   return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
		               }
		           },
		           plotOptions: {
		               line: {
		                   dataLabels: {
		                       enabled: true
		                   },
		                   enableMouseTracking: false
		               }
		           },
		           series: [{}]   
		       };
		       //下面是获取数据及绑定的方法
		       $.getJSON("http://localhost:8080/WSNServer/highcharts",{ipAdd:'<%=ips%>'}, function (data) {   //请求的地址和回掉函数。
		         //注意：后台的json或json数组格式一定要正确，否则进不了回调函数。
		            var obj=data.rows;
		            console.info(obj);
		            //时间
		            var timeArr=new Array();
		            var valArr=new Array();
		            //ip地址
		            var ipArr='<%=ips%>';
		          	for(var i=0;i<obj[0].length;i++){
		          		timeArr.push(obj[0][i]);
		          		valArr.push(parseFloat(obj[1][i]));
		           	}
		           	options.xAxis.categories=timeArr;
		           	options.series[0].data=valArr;
		        	options.series[0].name=ipArr;
		           	chart = new Highcharts.Chart(options); 
		       });       
		   });
		 </script>
         <div id="container" style="min-width:700px;height:400px"></div>
   </div>
   <div data-options="region:'west',split:true,border:false" style="width:450px"></div>
   <div data-options="region:'center',border:false" style="width:50px"></div>
   </div> 